package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SDBCommentsDao {

	private static SDBCommentsDao instance;

	private SDBCommentsDao() {

	}

	public static SDBCommentsDao getInstance() {
		if (instance == null) {
			instance = new SDBCommentsDao();
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
	
	public int getTotalCnt(String id, String table) throws SQLException{
		int result = 0;
		Connection conn = null;
		String sql = "select count(*) from comments where memberid=? and ref_table=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, table);
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
	
	public int insert(Comments comments, int boardNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "insert into comments values(?,?,?,?,?,?,?,?,?,?,to_char(sysdate, 'YYYYMMDDHHMISS'))";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			int number = rs.getInt(1) + 1;
			System.out.println("number -> " + number);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, number);
			pstmt.setString(3, "board");
			pstmt.setString(4, "1");
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setString(7, comments.getContent());
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setString(10, comments.getMemberId());
			rs = pstmt.executeQuery();
			rs.next();
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return result;
	}
	
	public List<Comments> list(String id, int startRow, int endRow, String table) throws SQLException {
		List<Comments> list = new ArrayList<Comments>();
		Connection conn = null;	 
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		System.out.println(id+" "+startRow+" "+endRow);
		//String sql = "select * from (select rownum rn, a.* from (select mainNo, subNo, ref_Table, content, memberid, regDate from comments where memberId=? and ref_table=? order by regDate) a) where rn between ? and ?";
		String sql = "select * from (select rownum rn, a.*, b.* from (select mainNo, subNo, ref_Table, content, memberid, regDate from comments where memberId=? and ref_table=? order by regDate) a, (select name from iteminfo where itemno = (select mainno from comments where memberid=? and ref_table=?)) b) where rn between ? and ?";
		
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, table);
			pstmt.setString(3, id);
			pstmt.setString(4, table);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comments comments = new Comments();
				comments.setMainNo(rs.getInt("mainNo"));
				comments.setSubNo(rs.getInt("subNo"));
				comments.setRef_Table(rs.getString("ref_Table"));
				comments.setContent(rs.getString("content"));
				comments.setMemberId(rs.getString("memberId"));
				comments.setRegDate(rs.getString("regDate"));
				comments.setItemName(rs.getString("name"));
				list.add(comments);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public List<Comments> listComms(String id, int startRow, int endRow) throws SQLException {
		List<Comments> list = new ArrayList<Comments>();
		Connection conn = null;	 
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		System.out.println(id+" "+startRow+" "+endRow);
		String sql = "select * from (select rownum rn, a.* from (select mainNo, subNo, ref_Table, content, memberid, regDate from comments where memberId=? order by regDate) a) where rn between ? and ?";
		
		
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comments comments = new Comments();
				comments.setMainNo(rs.getInt("mainNo"));
				comments.setSubNo(rs.getInt("subNo"));
				comments.setRef_Table(rs.getString("ref_Table"));
				comments.setContent(rs.getString("content"));
				comments.setMemberId(rs.getString("memberId"));
				comments.setRegDate(rs.getString("regDate"));
				list.add(comments);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	
	public int delete(int mainNo, int subNo, String ref_Table) throws SQLException {
		Connection conn = null;
		int result = 0;
		String sql = "delete from comments where mainNo=? and subNo=? and ref_Table=?";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setString(3, ref_Table);
			result = pstmt.executeUpdate();
			System.out.println("댓글 딜리트 리절트 : " + result);
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
}
