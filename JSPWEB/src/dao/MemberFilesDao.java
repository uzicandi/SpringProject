package dao;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class MemberFilesDao {
	private static MemberFilesDao instance;

	private MemberFilesDao() {
	}

	public static MemberFilesDao getInstance() {
		if (instance == null) {
			instance = new MemberFilesDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int getMainNo(String id) throws SQLException {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "select seq from memberfiles where memberid=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
				System.out.println("mainNo result -> "+result);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}

	public int insert(MemberFiles memberFiles) throws SQLException {
		int result = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql1 = "delete from SaveFiles where mainno="+getMainNo(memberFiles.getMemberId())+" and subno=0 and ref_table='MEMBER'";
		String sql2 = "delete from MemberFiles where memberid=?";		
		String sql = "insert into MemberFiles values(?, ?)";
		try {
			// key인 num 1씩 증가, mysql auto_increment 또는 오라클 sequence
			// sequence를 사용 : values(시퀀스명 (board_seq).nextvalue, ?, ?, ?, ...)
			int seqNo = UtilityDao.getInstance().getMaxNoForTable("MemberFiles");	// fileUpload 때문에 사용
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memberFiles.getMemberId());
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seqNo);
			pstmt.setString(2, memberFiles.getMemberId());
			result = pstmt.executeUpdate();
			if(result > 0 ) result = seqNo;	// 매핑번호 return
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}

}