package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ClipDao {
	private static ClipDao instance;

	private ClipDao() {
	}

	public static ClipDao getInstance() {
		if (instance == null) {
			instance = new ClipDao();
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
	
	// Service -> DAO (6단계)
		public int getTotalCnt(String id) throws SQLException{
			int result = 0;
			Connection conn = null;
			String sql = "select count(*) from comments a, (select mainno from clip where memberid=?) b where a.mainno=b.mainno and a.subno != 0 and ref_table = 'ITEMINFO'";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1);
				}
			} catch (Exception e) { 
				System.out.println(e.getMessage());
			} finally {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			return result;
		} 
		
		public int getTotalCnt2(String id) throws SQLException{
			int result = 0;
			Connection conn = null;
			String sql = "select count(*) from board a, (select mainno from clip where memberid=? and subno=0) b where a.boardno = b.mainno";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1);
				}
			} catch (Exception e) { 
				System.out.println("Exception"+e.getMessage());
			} finally {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			return result;
		} 
		
		public int check(String memberId, int boardNo, String title) throws SQLException {
			Connection conn = null;
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = "select subno from clip where memberId =? and title =?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				pstmt.setString(2, title);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println(rs.getInt(1) + " " + boardNo);
					if (rs.getInt(1) == boardNo)
						result = 1;
					else
						result = 0;
				} else
					result = -1;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			return result;
		}

	
	public Clip select(String id) throws SQLException {
		Clip clip = new Clip();
		Connection conn = null;
		String sql = "select * from clip where memberid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				clip.setMemberId(rs.getString(1));
				clip.setMainNo(rs.getInt(2));
				clip.setSubNo(rs.getInt(3));
				clip.setTitle(rs.getString(4));
				clip.setRegDate(rs.getString(5));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return clip;
	}
	
	


public List<Clip> list(String id, int startRow, int endRow) throws SQLException {
	List<Clip> list = new ArrayList<Clip>();
	Connection conn = null;	 
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	String sql = "select * from (select rownum rn, a.* from (select mainNo, subNo, title, regDate from clip where memberId=? and subno != 0 order by regDate) a) "
				+ "where rn between ? and ?";
	try {
//		private String memberId;
//		private int mainNo;
//		private int subNo;
//		private String title;
//		private String regDate;
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, endRow);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Clip clip = new Clip();
			clip.setMainNo(rs.getInt("mainNo"));
			clip.setSubNo(rs.getInt("subNo"));
			clip.setTitle(rs.getString("title"));
			clip.setRegDate(rs.getString("regDate"));
			list.add(clip);
		}
	} catch(Exception e) {	System.out.println(e.getMessage()); 
	} finally {
		if (rs !=null) rs.close();
		if (pstmt != null) pstmt.close();
		if (conn !=null) conn.close();
	}
	return list;
}

public List<Clip> list2(String id, int startRow, int endRow) throws SQLException {
	List<Clip> list = new ArrayList<Clip>();
	Connection conn = null;	 
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	String sql = "select * from (select rownum rn, a.boardno, a.title, a.memberid, b.regdate from board a, (select mainno, regdate from clip where memberid=? and subno=0) b where a.boardno=b.mainno order by b.regdate desc) where rn between ? and ?";
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, endRow);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Clip clip = new Clip();
			clip.setMainNo(rs.getInt(2));
			clip.setTitle(rs.getString(3));
			clip.setMemberId(rs.getString(4));
			clip.setRegDate(rs.getString(5));
			list.add(clip);
		}
	} catch(Exception e) {	System.out.println(e.getMessage()); 
	} finally {
		if (rs !=null) rs.close();
		if (pstmt != null) pstmt.close();
		if (conn !=null) conn.close();
	}
	return list;
}

public int delete(String id, int main[], int sub[]) throws SQLException {
	Connection conn = null;
	String sql = "delete from clip where memberid = ? and mainno = ? and subno = ?";
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		for(int i=0; i<main.length; i++) {
			pstmt.setInt(2, main[i]);
			pstmt.setInt(3, sub[i]);
			rs = pstmt.executeQuery();
		}
		if(rs.next()) result = 1;
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		if (rs != null) rs.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
	return result;
}

public int delete2(String id, int main[]) throws SQLException {
	Connection conn = null;
	String sql = "delete from clip where memberid = ? and mainno = ? and subno = 0";
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		for(int i=0; i<main.length; i++) {
			pstmt.setInt(2, main[i]);
			rs = pstmt.executeQuery();
		}
		if(rs.next()) result = 1;
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		if (rs != null) rs.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
	return result;
}
	
	public int delete3(String id, int boardNo) throws SQLException {
		Connection conn = null;
		String sql = "delete from clip where memberid = ? and subno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) result = 2;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	public int insert(String memberId, int boardNo, String title) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select * from clip where memberid=? and mainno=? and subno=0";
		String sql = "insert into clip values(?, ?, 0, ?, fn_DateToChar(sysdate))";
		try {
			// int mainNo = UtilityDao.getInstance().getMaxNoForTable("Clip");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, boardNo);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				pstmt.close();
				rs.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				pstmt.setInt(2, boardNo);
				pstmt.setString(3, title);
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("insert clip-> " + e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	public int insert(String memberId, int mainNo, int subNo, String title) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select * from clip where memberid=? and mainno=? and subno=? ";
		String sql = "insert into clip values(?, ?, ?, ?, fn_DateToChar(sysdate))";
		try {
			// int mainNo = UtilityDao.getInstance().getMaxNoForTable("Clip");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, mainNo);
			pstmt.setInt(3, subNo);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				pstmt.close();
				rs.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				pstmt.setInt(2, mainNo);
				pstmt.setInt(3, subNo);
				pstmt.setString(4, title);
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("insert clip-> " + e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
}
