package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UtilityDao {

	private static UtilityDao instance;
	private UtilityDao() {}
	public static UtilityDao getInstance() {
		if (instance == null) { instance = new UtilityDao(); }
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return conn;		
	}
	/*
	 권한체크값 = authorityCheck(Mode[읽기:R, 수정삭제:W], MemberId, TableName, Ref_Table, MainNo, SubNo, SmallNo); // 맞게 수정 필요
	*/
	public int authorityCheck(String mode, String memberId, String tableName, String ref_Table, int mainNo, int subNo, int smallNo)  throws SQLException {
		int result = 0;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
//		String sql = "{call AuthorityCheck(?,?,?,?,?,?,?)}";
		String sql = "{call AuthorityCheck(?, ?, ?, ?, ?, ?, ?, ?)}";
//		CREATE OR REPLACE PROCEDURE AuthorityCheck(
//	    		v_Mode IN VARCHAR
//	    	,	v_MemberId IN VARCHAR
//			,   v_TableName IN VARCHAR
//			,   v_Ref_Table IN VARCHAR := 'Board'
//			,   v_MainNo IN NUMBER := 0
//			,   v_SubNo IN NUMBER := 0
//			,   v_SmallNo IN NUMBER := 0
//			,   v_AuthorityCheck OUT CHAR
//			)
//		EXECUTE AuthorityCheck(MemberId, TableName, Ref_Table, MainNo, SubNo, SmallNo, AuthorityCheck);
		
		try {
			conn = getConnection();
			cs = conn.prepareCall(sql);
			cs.setString(1, mode);
			cs.setString(2, memberId);
			cs.setString(3, tableName);
			cs.setString(4, ref_Table);
			cs.setInt(5, mainNo);
			cs.setInt(6, subNo);
			cs.setInt(7, smallNo);
			cs.registerOutParameter(8, java.sql.Types.CHAR);
//			cs.registerOutParameter(8, java.sql.Types.VARCHAR);
			cs.executeQuery();
			result = Integer.parseInt(cs.getString(8));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(cs != null) cs.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	
	public String getMemberGrade(String memberId) throws SQLException {
		String result = "";
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "{? = call fn_MemberIdToGrade(?)}";
System.out.println("memberId->"+memberId);
		try {
			conn = getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, java.sql.Types.CHAR);
			cs.setString(2, memberId);
			cs.executeQuery();
			result = cs.getString(1);
System.out.println("fn_MemberIdToGrade->"+result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(cs != null) cs.close();
			if(conn != null) conn.close();
		}
		return result;
	}

	public int getMaxNoForTable(String tableName) throws SQLException {
		String sql = "{? = call fn_MaxNoForTable(?)}";

		return getMaxNoForTable(tableName, null, -1, -1, sql);
	}

	
	public int getMaxNoForTable(String tableName, String referenceTable, int mainNo) throws SQLException {
		String sql = "{? = call fn_MaxNoForTable(?, ?, ?)}";

		return getMaxNoForTable(tableName, referenceTable, mainNo, -1, sql);
	}
	
	public int getMaxNoForTable(String tableName, String referenceTable, int mainNo, int subNo) throws SQLException {
		String sql = "{? = call fn_MaxNoForTable(?, ?, ?, ?)}";

		return getMaxNoForTable(tableName, referenceTable, mainNo, subNo, sql);
	}
	
	public int getMaxNoForTable(String tableName, String referenceTable, int mainNo, int subNo, String sql) throws SQLException {
		int result = 0;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
//		String sql = "{? = call fn_MaxNoForTable(?)}";

		try {
			conn = getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setString(2, tableName);
			if(referenceTable != null) cs.setString(3, referenceTable);
			if(mainNo != -1) cs.setInt(4, mainNo);
			if(subNo != -1) cs.setInt(5, subNo);
			cs.executeQuery();
			result = cs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(cs != null) cs.close();
			if(conn != null) conn.close();
		}
		return result;
	}

	public List<Common> commonCodeList(String commonCode) throws SQLException {
		return commonCodeList(commonCode, null, null);
	}

	public List<Common> commonCodeList(String commonCode, String filterItem, String[] filterValue) throws SQLException {
		Connection conn = null;
		List<Common> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		if(filterValue != null && filterValue[0].trim().length() > 0) {
			if(filterItem.equals("CODENAME")) sb.append("and codeName not in (");
			else sb.append("and codeValue not in (");

			for (String str: filterValue) {
				sb.append(String.format("'%s',",str));
			}
			sb.replace(sb.length()-1, sb.length(), ") ");
		}
//System.out.println(sb.toString());		
		String sql = 
				"select trim(codeName) as codeName, trim(codeValue) as codeValue from COMMON " 
			  + "where commonCode = ? "
			  + sb.toString() 
			  + "order by seq";
//System.out.println(sql);		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commonCode);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String codeName = rs.getString("codeName");
				String codeValue = rs.getString("codeValue");
				Common common = new Common();	// DTO
				common.setCodeName(codeName);
				common.setCodeValue(codeValue);
				list.add(common);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return list;
	}
	
	public String getBindList(String commonCode, String type, String objectId, String defaultValue) throws SQLException {
		List<Common> nameValueList = this.commonCodeList(commonCode);
		StringBuilder sb = new StringBuilder();

		switch (type) {
		case "SELECT" :
			sb.append(String.format("<!--공통기능에서 자동 추가  Start-->\r\n<select id=\"%s\">\r\n", objectId));
			for (Common common : nameValueList) {
				sb.append(String.format("<option value=\"%s\" %s>%s</option>\r\n", 
						common.getCodeValue(), (common.getCodeValue().equals(defaultValue) ? "selected" : ""), common.getCodeName()));
			}
			sb.append(String.format("</select>\r\n<!--공통기능에서 자동 추가  End-->\r\n"));
			break;
			default :
				break;
		}

		return sb.toString();
	}

}
