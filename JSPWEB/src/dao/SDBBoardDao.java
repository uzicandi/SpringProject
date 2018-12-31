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

public class SDBBoardDao {
	private static SDBBoardDao instance;

	private SDBBoardDao() {

	}

	public static SDBBoardDao getInstance() {
		if (instance == null) {
			instance = new SDBBoardDao();
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

	/*public int getTotalCnt(String boardCategory) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;

		String sql = "select count(*) from board where boardCategory = ?";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("getTotalCnt sql 수행");
				tot = rs.getInt(1);
			} else {
				System.out.println("getTotalCnt sql 비수행");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return tot;

	}*/
	
	public int getTotalCnt(String boardCategory) throws SQLException{
		int result = 0;
		Connection conn = null;
		String sql = "select count(*) from board where boardCategory = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardCategory);
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

	public Board select(int boardNo) throws SQLException {

		Connection conn = null;
		String sql = "select * from board where boardNo=" + boardNo;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = new Board();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board.setBoardNo(rs.getInt("boardNo"));
				board.setBoardCategory(rs.getString("boardCategory"));
				board.setMemberId(rs.getString("memberId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setRegDate(rs.getString("regDate"));
			}
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
		return board;
	}

	public List<Board> list(int startRow, int endRow, String boardCategory) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		String sql = "select * from (select rownum rn, a.* from (select * from board where boardCategory = ? order by boardno desc) a) where rn between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardCategory);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("boardNo"));
				board.setMemberId(rs.getString("memberId"));
				board.setTitle(rs.getString("title"));
				board.setHits(rs.getInt("hits"));
				board.setContent(rs.getString("content"));
				board.setBoardCategory(rs.getString("boardCategory"));
				board.setRegDate(rs.getString("regDate"));
				list.add(board);
			}
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
		return list;
	}

	public int insert(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select nvl(max(boardNo),0) from board";
		String sql = "insert into board values(?,?,?,?,?,?,?,?,to_char(sysdate, 'YYYYMMDDHHMISS'))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			// key인 num 1씩 증가, mysql auto_increment 또는 oracle sequence
			// sequence를 사용 : values(시퀀스명(board_seq).nextval,?,?...)
			int number = rs.getInt(1) + 1;
			System.out.println("number -> " + number);
			rs.close();
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getBoardCategory());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, "1");
			pstmt.setString(6, board.getIsPublic());
			pstmt.setInt(7, board.getHits());
			pstmt.setString(8, board.getMemberId());
			result = pstmt.executeUpdate();
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

	public void hits(int boardNo) throws SQLException {
		Connection conn = null;
		String sql = "update board set hits = hits+1 where boardNo = ?";
		PreparedStatement pstmt = null;
	//	Board board = new Board();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public int update(Board board) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
	//	ResultSet rs = null;
		String sql = "update board set title = ?, content = ? where boardNo = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
			result = pstmt.executeUpdate();
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

	public int delete(int boardNo) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from board where boardNo=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}else
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
	
	public List<Board> mainWebzineList() throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		String sql = "select * from (select * from board where boardcategory='웹진' order by boardNo desc) where rownum <= 2";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("boardNo"));
				board.setMemberId(rs.getString("memberId"));
				board.setTitle(rs.getString("title"));
				board.setHits(rs.getInt("hits"));
				board.setContent(rs.getString("content"));
				board.setBoardCategory(rs.getString("boardCategory"));
				board.setRegDate(rs.getString("regDate"));
				list.add(board);
			}
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
		return list;
	}
}
