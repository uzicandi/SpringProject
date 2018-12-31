package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JJYCommentsDao {
	private static JJYCommentsDao instance;

	private JJYCommentsDao() {
	}

	public static JJYCommentsDao getInstance() {
		if (instance == null) {
			instance = new JJYCommentsDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

			System.out.println("DB 연결...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public Comments getCommentInfo(int mainNo, int subNo, String ref_Table1, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Comments comm = null;

		String sql = "select * from comments where mainno=? and subno=? and ref_Table=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setString(3, ref_Table1);
			rs = pstmt.executeQuery();
			
			int i = 0;
			if (rs.next()) {
				System.out.println("getCommentInfo.... " + i);
				comm = new Comments();
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("REF_TABLE"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));	
			}
			rs.close();
			pstmt.close();
			
			// 리뷰에 달린 댓글 개수 구하기
			sql = "select count(*) from comments where mainno=? and ref_table=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subNo);
			pstmt.setString(2, ref_Table);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				comm.setCommentCount(rs.getLong(1));
			}

		} catch (Exception e) {
			System.out.println("selectItem error -> " + e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return comm;
	}
	
	public Comments selectComment(int mainNo, int subNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Comments comm = null;
		String sql = "select * from comments where mainno=? and subno=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comm = new Comments();
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("REF_TABLE"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));	
			}
			System.out.println("selectComment 진행...");
		} catch (Exception e) {
			System.out.println("selectComment error -> " + e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return comm;
	}

	public List<Comments> selectComments(int itemNo, String ref_Table, int commPageSize) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comments> comms = null;
		Comments comm = null;
		ResultSetMetaData rsmeta = null;
		int colCnt = 0;
		
		String sql = "select * from ( select * from comments where mainno=? and ref_Table=? order by subno desc ) comments where rownum between 1 and ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
//			System.out.println("itemNo->"+itemNo);
//			System.out.println("commPageSize->"+commPageSize);
			pstmt.setInt(1, itemNo);
			pstmt.setString(2, ref_Table);
			pstmt.setInt(3, commPageSize);
			rs = pstmt.executeQuery();
			
			rsmeta = rs.getMetaData();
			colCnt = rsmeta.getColumnCount();
			for (int i=1; i<= colCnt; i++) {                                           
				 String colName = rsmeta.getColumnName(i);    // Get column name
				 String colType = rsmeta.getColumnTypeName(i);
				                                        // Get column data type
				 System.out.println("Column = " + colName + 
				  " is data type " + colType);
			                                        // Print the column value
			}
			
			System.out.println("sql->"+sql);
			comms = new ArrayList<>();
			int i=0;
			while(rs.next()) {
				System.out.println("--------------while 진행중..." + i++);
				comm = new Comments();	
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("REF_TABLE"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));	
				
//				System.out.println("comm -> " + comm);
				comms.add(comm);
				comm = null;
			}
			
			System.out.println("select comms -> " + comms);
		} catch (Exception e) {
			System.out.println("selectComments error -> " + e.getMessage());
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		
		return comms;
	}

	public synchronized HashMap<String, Object> insertComment(String memberId, String commemtContent, int mainNo,
			String ref_Table, String isPublic) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Comments> comms = null;
		HashMap<String, Object> hm = null;
		int result = 0;

		String sql = "INSERT INTO comments (mainno,subno,ref_table,ispublic,content,memberid,regdate) "
					+ "VALUES (?,fn_MaxNoForTable('Comments',?, ?), ?, ?, ?, ?, FN_DATETOCHAR(sysdate))";

		try {
			System.out.println("query 실행 준비...");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table);
			pstmt.setInt(3, mainNo);
			pstmt.setString(4, ref_Table);
			pstmt.setString(5, isPublic);
			pstmt.setString(6, commemtContent);
			pstmt.setString(7, memberId);
			System.out.println("sql -> " + sql);
			result = pstmt.executeUpdate();

			System.out.println("query 실행 완료.... result : " + result);

			pstmt.close();
			conn.close();

			comms = new ArrayList<>();
			System.out.println("selectComments...");
			comms = selectComments(mainNo, ref_Table, 10);
			System.out.println("..End!");
			hm = new HashMap<>();
			hm.put("result", result);
			hm.put("comments", comms);

		} catch (Exception e) {
			System.out.println("insertComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return hm;
	}
	
	public int updateComments(int mainNo, int subNo, String content) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update comments set content=?, regdate=FN_DATETOCHAR(sysdate) where mainno=? and subno=?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, mainNo);
			pstmt.setInt(3, subNo);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("updateComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return result;
	}

	public int deleteComments(int mainNo, int subNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from comments where mainno=? and subno=?"; 
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return result;
	}

	public Board getBoardInfo(int boardNo, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;

		String sql = "select * from board where boardno=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			int i = 0;
			if (rs.next()) {
				System.out.println("getBoardInfo.... " + i);
				board = new Board();
				board.setBoardNo(rs.getInt("boardNo"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setMemberId(rs.getString("memberId"));
				board.setRegDate(rs.getString("regDate"));
			}
			rs.close();
			pstmt.close();
			
			// 리뷰에 달린 댓글 개수 구하기
			sql = "select count(*) from comments where mainno=? and ref_table=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, ref_Table);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board.setCommentCount(rs.getLong(1));
			}

		} catch (Exception e) {
			System.out.println("selectItem error -> " + e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return board;
	}
	
	public synchronized HashMap<String, Object> insertBoardComment(String memberId, String commemtContent, int mainNo,
			String isPublic) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Comments> comms = null;
		HashMap<String, Object> hm = null;
		int result = 0;
		String ref_Table = "BOARD";
		

		String sql = "INSERT INTO comments (mainno,subno,ref_table,ispublic,content,memberid,regdate) "
					+ "VALUES (?,fn_MaxNoForTable('Comments',?, ?), ?, ?, ?, ?, FN_DATETOCHAR(sysdate))";

		try {
			System.out.println("query 실행 준비...");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table);
			pstmt.setInt(3, mainNo);
			pstmt.setString(4, ref_Table);
			pstmt.setString(5, isPublic);
			pstmt.setString(6, commemtContent);
			pstmt.setString(7, memberId);
			System.out.println("sql -> " + sql);
			result = pstmt.executeUpdate();

			System.out.println("query 실행 완료.... result : " + result);

			pstmt.close();
			conn.close();

			comms = new ArrayList<>();
			System.out.println("selectComments...");
			comms = selectComments(mainNo, ref_Table, 10);
			System.out.println("..End!");
			hm = new HashMap<>();
			hm.put("result", result);
			hm.put("comments", comms);

		} catch (Exception e) {
			System.out.println("insertComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return hm;
	}

	public List<Comments> selectBoardComments(int mainNo, int commPageSize) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comments> comms = null;
		Comments comm = null;
		ResultSetMetaData rsmeta = null;
		int colCnt = 0;
		String ref_Table = "BOARD";
		
		System.out.println(mainNo + " " + ref_Table + " " + commPageSize);
		
		String sql = "select * from ( select * from comments where mainno=? and ref_Table=? order by subno desc ) comments where rownum between 1 and ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
//			System.out.println("itemNo->"+itemNo);
//			System.out.println("commPageSize->"+commPageSize);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table);
			pstmt.setInt(3, commPageSize);
			rs = pstmt.executeQuery();
			
			rsmeta = rs.getMetaData();
			colCnt = rsmeta.getColumnCount();
			for (int i=1; i<= colCnt; i++) {                                           
				 String colName = rsmeta.getColumnName(i);    // Get column name
				 String colType = rsmeta.getColumnTypeName(i);
				                                        // Get column data type
				 System.out.println("Column = " + colName + " is data type " + colType);
			                                        // Print the column value
			}
			
			System.out.println("sql->"+sql);
			comms = new ArrayList<>();
			int i=0;
			System.out.println(rs);
			System.out.println("true false 확인 -> " + rs.next());
			while(rs.next()) {
				System.out.println("--------------while 진행중..." + i++);
				comm = new Comments();	
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("ref_table"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));	
				
//				System.out.println("comm -> " + comm);
				comms.add(comm);
				comm = null;
			}
			
			System.out.println("select comms -> " + comms);
		} catch (Exception e) {
			System.out.println("selectComments error -> " + e.getMessage());
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return comms;
	}
}
