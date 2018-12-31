package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SaveFilesDao {
	private static SaveFilesDao instance;
	private SaveFilesDao() {}
	public static SaveFilesDao getInstance() {
		if (instance == null) { instance = new SaveFilesDao(); }
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

	public int insert(SaveFiles saveFile) throws SQLException {
		List<SaveFiles> list = new ArrayList<>();
		list.add(saveFile);
		return this.insertList(list);
	}

	public int insertList(List<SaveFiles> list) throws SQLException {
		int result = 0;
//		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into saveFiles values(?, ?, ?, fn_MaxNoForTable('SaveFiles', ?, ?, ?), ?, ?, ?, ?, ?, fn_DateToChar(sysdate))";

		try {
			// key인 num 1씩 증가, mysql auto_increment 또는 오라클 sequence
			// sequence를 사용 : values(시퀀스명 (board_seq).nextvalue, ?, ?, ?, ...)
			// int boardNo = UtilityDao.getInstance().getMaxNoForTable("SaveFiles", saveFiles.getRef_Table(), saveFiles.getMainNo(), saveFiles.getSubNo());
			conn = getConnection();
			for (SaveFiles saveFile : list) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, saveFile.getMainNo());
				pstmt.setInt(2, saveFile.getSubNo());
				pstmt.setString(3, saveFile.getRef_Table());
				pstmt.setString(4, saveFile.getRef_Table());
				pstmt.setInt(5, saveFile.getMainNo());
				pstmt.setInt(6, saveFile.getSubNo());
				pstmt.setString(7, saveFile.getOriginFileName());
				pstmt.setString(8, saveFile.getSavedFileName());
				pstmt.setString(9, saveFile.getFileExtend());
				pstmt.setString(10, saveFile.getFilePath());
				pstmt.setLong(11, saveFile.getFileSize());
				result += pstmt.executeUpdate();
System.out.println("sql->"+sql);
			}
System.out.println("SaveFiles:End->"+result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	

	// Not Used
//	public int updateList(List<SaveFiles> list) throws SQLException {
//		int result = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql = 
//				"update saveFiles "
//			+ 	"set originFileName = ? "
//			+ 	", savedFileName = ? "
//			+ 	", fileExtend = ? "
//			+ 	", filePath = ? "
//			+ 	", fileSize = ? "
//			+ 	", regDate = fn_DateToChar(sysdate) "
//			+ 	"where mainNo = ? "
//			+ 	"and subNo = ? "
//			+ 	"and ref_Table = ? "
//			+ 	"and filesNo = ? ";
//		try {
//			conn = getConnection();
//			for (SaveFiles saveFile : list) {
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, saveFile.getOriginFileName());
//				pstmt.setString(2, saveFile.getSavedFileName());
//				pstmt.setString(3, saveFile.getFileExtend());
//				pstmt.setString(4, saveFile.getFilePath());
//				pstmt.setLong(5, saveFile.getFileSize());
//				pstmt.setInt(6, saveFile.getMainNo());
//				pstmt.setInt(7, saveFile.getSubNo());
//				pstmt.setString(8, saveFile.getRef_Table().toUpperCase());
//				pstmt.setInt(9, saveFile.getFilesNo());
//				result += pstmt.executeUpdate();
//System.out.println("SaveFiles:result->"+result);
//			}
//System.out.println("SaveFiles:End->"+result);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		return result;
//	}


	public int delete(SaveFiles saveFile) throws SQLException {
		List<SaveFiles> list = new ArrayList<>();
		list.add(saveFile);
		return this.deleteList(list);
	}
	
	public int deleteList(List<SaveFiles> list) throws SQLException {
		int result = 0;
//		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = 
					"delete from saveFiles "
				+ 	"where mainNo = ? "
				+ 	"and ref_Table = ? "
				+ 	"and (subNo = ? or ? = 0) "
				+ 	"and (filesNo = ? or ? = 0) ";

		try {
			conn = getConnection();
			for (SaveFiles saveFile : list) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, saveFile.getMainNo());
				pstmt.setString(2, saveFile.getRef_Table().toUpperCase());
				pstmt.setInt(3, saveFile.getSubNo());
				pstmt.setInt(4, saveFile.getSubNo());
				pstmt.setInt(5, saveFile.getFilesNo());
				pstmt.setInt(6, saveFile.getFilesNo());
				result += pstmt.executeUpdate();
			}
System.out.println("SaveFiles:delete->"+result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	

	public List<SaveFiles> list(int mainNo, String ref_Table) throws SQLException {
		return this.list(mainNo, 0, ref_Table, 0);
	}
	public List<SaveFiles> list(int mainNo, int subNo, String ref_Table) throws SQLException {
		return this.list(mainNo, subNo, ref_Table, 0);
	}
	public List<SaveFiles> list(int mainNo, int subNo, String ref_Table, int filesNo) throws SQLException {
		Connection conn = null;
		List<SaveFiles> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = 
				"select MainNo, SubNo, Ref_Table, FilesNo, OriginFileName, SavedFileName, FileExtend, FilePath, FileSize, fn_CharToDate(RegDate) as RegDate "
		+		"from SaveFiles "
		+		"where MainNo = ? "
		+ 		"and (SubNo = ? or ? = 0) "
		+ 		"and Ref_Table = ? " 
		+ 		"and (filesNo = ? or ? = 0) "
		+		"order by MainNo, SubNo ";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setInt(3, subNo);
			pstmt.setString(4, ref_Table.toUpperCase());
			pstmt.setInt(5, filesNo);
			pstmt.setInt(6, filesNo);
			rs = pstmt.executeQuery();
//System.out.println(sql);
//System.out.println(mainNo+"||"+subNo+"||"+ref_Table);

			while(rs.next()) {
				SaveFiles saveFile = new SaveFiles(
							rs.getInt("mainNo")
						,	rs.getInt("subNo")
						,	rs.getString("ref_Table")
						,	rs.getInt("filesNo")
						,	rs.getString("originFileName")
						,	rs.getString("savedFileName")
						,	rs.getString("fileExtend")
						,	rs.getString("filePath")
						,	rs.getInt("fileSize")
						,	rs.getString("regDate")
					);	// DTO
				list.add(saveFile);
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
	
	public String list(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select mainno from savefiles where mainno=(select seq from memberfiles where memberid=?)";
		String result = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getString(1);
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
	
	public List<SaveFiles> list(int mainNo, String ref_Table, int fileCount) throws SQLException {
		Connection conn = null;
		List<SaveFiles> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = 
				"select MainNo, SubNo, Ref_Table, FilesNo, OriginFileName, SavedFileName, FileExtend, FilePath, FileSize, fn_CharToDate(RegDate) as RegDate "
		+		"from SaveFiles "
		+		"where MainNo = ? "
		+ 		"and Ref_Table = ? " 
		+		"order by MainNo, SubNo ";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table.toUpperCase());
			rs = pstmt.executeQuery();
//System.out.println(sql);
//System.out.println(mainNo+"||"+subNo+"||"+ref_Table);

			while(rs.next()) {
				SaveFiles saveFile = new SaveFiles(
							rs.getInt("mainNo")
						,	rs.getInt("subNo")
						,	rs.getString("ref_Table")
						,	rs.getInt("filesNo")
						,	rs.getString("originFileName")
						,	rs.getString("savedFileName")
						,	rs.getString("fileExtend")
						,	rs.getString("filePath")
						,	rs.getInt("fileSize")
						,	rs.getString("regDate")
					);	// DTO
				list.add(saveFile);
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
	
	public SaveFiles webList(int mainNo, String ref_Table) throws SQLException {
		Connection conn = null;
		SaveFiles saveFile = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = 
				"select MainNo, SubNo, Ref_Table, FilesNo, OriginFileName, SavedFileName, FileExtend, FilePath, FileSize, fn_CharToDate(RegDate) as RegDate "
		+		"from SaveFiles "
		+		"where MainNo = ? "
		+ 		"and Ref_Table = ? " 
		+		"order by MainNo, SubNo ";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table.toUpperCase());
			rs = pstmt.executeQuery();
//System.out.println(sql);
//System.out.println(mainNo+"||"+subNo+"||"+ref_Table);

			if(rs.next()) {
				saveFile = new SaveFiles(
							rs.getInt("mainNo")
						,	rs.getInt("subNo")
						,	rs.getString("ref_Table")
						,	rs.getInt("filesNo")
						,	rs.getString("originFileName")
						,	rs.getString("savedFileName")
						,	rs.getString("fileExtend")
						,	rs.getString("filePath")
						,	rs.getInt("fileSize")
						,	rs.getString("regDate")
					);	// DTO
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return saveFile;
	}
	
	public SaveFiles memPic(int mainNo) throws SQLException{
		SaveFiles savefile = new SaveFiles();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select filepath, savedfilename from savefiles where mainno=? and subno=0 and ref_table='MEMBER'";
		String result = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				savefile.setFilePath(rs.getString(1));
				savefile.setSavedFileName(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return savefile;
	}
	
	public int getCnt(int mainNo) throws SQLException {
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from savefiles where mainno=? and subno=0 and ref_table='BOARD'";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = rs.getInt(1); 
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;	
	}
	
	public int getMain(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select seq from memberfiles where memberid=?";
		int mainNo = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mainNo=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return mainNo;
	}
	
	public SaveFiles getPic(int mainNo) throws SQLException{
		SaveFiles savefile = new SaveFiles();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select filepath, savedfilename from savefiles where mainno=? and subno=0 and ref_table='MEMBER'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				savefile.setFilePath(rs.getString(1));
				savefile.setSavedFileName(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return savefile;
	}
	
}
