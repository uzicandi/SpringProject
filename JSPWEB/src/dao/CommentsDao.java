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

public class CommentsDao {
	private static CommentsDao instance;
	private CommentsDao() {}
	public static CommentsDao getInstance() {
		if (instance == null) { instance = new CommentsDao(); }
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

//	public int getTotalCnt(String memberId, String memberGrade, String boardCategory) throws SQLException {
//		Connection conn = null;
//		int result = 0;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select count(*) cnt from board where boardCategory = ? "
//				+ " and ((IsPublic = '1' or ? = '0') or (IsPublic = '0' and Memberid = ?))";
//		
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, boardCategory);
//			pstmt.setString(2, memberGrade);
//			pstmt.setString(3, memberId);
//			rs = pstmt.executeQuery();
//			if(rs.next())
//				result = rs.getInt(1); 
//				
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if(rs != null) rs.close();
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		return result;	
//	}
//	
//	

	public CommentsDisplay select(String loginUserId, String loginUserGrade, int mainNo, int subNo, String ref_Table) throws SQLException {
		Connection conn = null;
		CommentsDisplay commentsDisplay = new CommentsDisplay();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = 
				"select a.*, m.nickName, m.grade from ( "
			+ 	"select MainNo, SubNo, Ref_Table, IsPublic, Hits, Ref, Content, MemberId, fn_CharToDate(RegDate) as RegDate "
			+ 	"from comments " 
			+ 	"where MainNo = ? "
			+ 	"and SubNo = ? " 
			+	"and Ref_Table = ? " 
			+ 	" ) a "
			+ 	"join Member m on a.MemberId = m.MemberId "
			+	"order by MainNo, SubNo, Ref_Tabl, REF " 
				;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setString(3, ref_Table.toUpperCase());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				commentsDisplay.setMainNo(rs.getInt("MainNo"));
				commentsDisplay.setSubNo(rs.getInt("SubNo"));
				commentsDisplay.setRef_Table(rs.getString("Ref_Table"));
				commentsDisplay.setIsPublic(rs.getString("IsPublic"));
				commentsDisplay.setHits(rs.getInt("Hits"));
				commentsDisplay.setRef(rs.getInt("Ref"));
				commentsDisplay.setContent(rs.getString("Content"));
			}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return commentsDisplay;	
	}
	
	public List<CommentsDisplay> list(String loginUserId, String loginUserGrade, int mainNo, String ref_Table) throws SQLException {
		Connection conn = null;
		List<CommentsDisplay> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = 
				"select a.*, m.nickName, m.grade from ( "
			+ 	"select MainNo, SubNo, Ref_Table, IsPublic, Hits, Ref, Content, MemberId, fn_CharToDate(RegDate) as RegDate "
			+ 	"from comments " 
			+ 	"where MainNo = ? "
//			+ 	"and SubNo = ? " 
			+	"and Ref_Table = ? " 
			+ 	" ) a "
			+ 	"join Member m on a.MemberId = m.MemberId "
			+	"order by MainNo, SubNo, Ref_Table, REF " 
				;
		
		try {
			// 임시작성
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table.toUpperCase());
			rs = pstmt.executeQuery();
//System.out.println(rs.getFetchSize());
//System.out.println(sql);
//System.out.println(mainNo);
//System.out.println(ref_Table);
			while(rs.next()) {
//System.out.println("11111");
				CommentsDisplay commentsDisplay = new CommentsDisplay();	// DTO
				commentsDisplay.setMainNo(rs.getInt("MainNo"));
				commentsDisplay.setSubNo(rs.getInt("SubNo"));
				commentsDisplay.setRef_Table(rs.getString("Ref_Table"));
				commentsDisplay.setIsPublic(rs.getString("IsPublic"));
				commentsDisplay.setHits(rs.getInt("Hits"));
				commentsDisplay.setRef(rs.getInt("Ref"));
				commentsDisplay.setContent(rs.getString("Content"));
				list.add(commentsDisplay);
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
//
//	public int insert(Board board) throws SQLException {
//		int result = 0;
//		ResultSet rs = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
////		String sql = "insert into board values(fn_MaxNoForTable('Board'), ?, ?, ?, ?, ?, ?, ?, fn_DateToChar(sysdate))";
//		String sql = "insert into board values(?, ?, ?, ?, ?, ?, ?, ?, fn_DateToChar(sysdate))";
//
//		try {
//			// key인 num 1씩 증가, mysql auto_increment 또는 오라클 sequence
//			// sequence를 사용 : values(시퀀스명 (board_seq).nextvalue, ?, ?, ?, ...)
//			int boardNo = UtilityDao.getInstance().getMaxNoForTable("Board");	// fileUpload 때문에 사용
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardNo);
//			pstmt.setString(2, board.getBoardCategory());
//			pstmt.setString(3, board.getTitle());
//			pstmt.setString(4, board.getContent());
//			pstmt.setString(5, board.getViewGrade());
//			pstmt.setString(6, board.getIsPublic());
//			pstmt.setInt(7, board.getHits());
//			pstmt.setString(8, board.getMemberId());
//			result = pstmt.executeUpdate();
//			if(result > 0 ) result = boardNo;	// 글번호 return
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		return result;
//	}
//	
//	public void readCount(int boardNo) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql = "update board "
//				+ "set hits = hits + 1 "
//				+ "where boardNo = ?";
//
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardNo);
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//	}
//	
//	public BoardDisplay select(int boardNo) throws SQLException {
//		Connection conn = null;
//		BoardDisplay boardDisplay = new BoardDisplay();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = 
//			"select a.*, m.nickName, m.grade from ( "
//			+ "select "
//			+ " BoardNo, BoardCategory, Title, Content, ViewGrade, IsPublic, Hits, MemberId, fn_CharToDate(RegDate) as RegDate"
//			+ " from board where BoardNo = ? ) a "
//			+ "join Member m on a.MemberId = m.MemberId";
//		
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardNo);
//			rs = pstmt.executeQuery();
//			if(rs.next())
//			{
//				boardDisplay.setBoardNo(rs.getInt("boardNo"));
//				boardDisplay.setBoardCategory(rs.getString("boardCategory"));
//				boardDisplay.setTitle(rs.getString("title"));
//				boardDisplay.setContent(rs.getString("content"));
//				boardDisplay.setViewGrade(rs.getString("viewGrade"));
//				boardDisplay.setIsPublic(rs.getString("isPublic"));
//				boardDisplay.setHits(rs.getInt("hits"));
//				boardDisplay.setMemberId(rs.getString("memberId"));
//				boardDisplay.setRegDate(rs.getString("regDate"));
//				boardDisplay.setNickName(rs.getString("nickName"));
//				boardDisplay.setGrade(rs.getString("grade"));
//			}
//				
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if(rs != null) rs.close();
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		return boardDisplay;	
//	}
//	
//	public int update(Board board) throws SQLException {
//		int result = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql = "update board "
//				+ "set boardCategory = ?"
//				+ ", title = ?"
//				+ ", content = ?"
//				+ ", viewGrade = ?"
//				+ ", isPublic = ?"
//				+ "where boardNo = ?";
//
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, board.getBoardCategory());
//			pstmt.setString(2, board.getTitle());
//			pstmt.setString(3, board.getContent());
//			pstmt.setString(4, board.getViewGrade());
//			pstmt.setString(5, board.getIsPublic());
//			pstmt.setInt(6, board.getBoardNo());
//			result = pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		return result;
//	}
//	
//	public int delete(int boardNo)  throws SQLException {
//		int result = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "delete from board  where boardNo = ?";
//
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardNo);
//			result = pstmt.executeUpdate();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			result = -1;
//		} finally {
//			if(rs != null) rs.close();
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		return result;
//	}
	
}
