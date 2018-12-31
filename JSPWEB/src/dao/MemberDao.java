package dao;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class MemberDao {
	private static MemberDao instance;

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
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

	
	public Member select(String id) throws SQLException {
		Member member = new Member();
		Connection conn = null;
		String sql = "select * from member where memberid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setMemberId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setName(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setPhone(rs.getString(5));
				member.setRegDate(rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return member;
	}

	public int insert(Member member) throws SQLException {
		Connection conn = null; 
		int result = 0;
														// grade default = 3
		String sql = "insert into member values(?,?,?,?,?,?,?,?,3,?,?,?,?,?,?,fn_datetochar(sysdate),fn_datetochar(sysdate))";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection(); // 기 연결된 DBCP이용
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId()); 
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getNickName());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getBirth());
			pstmt.setString(6, member.getSex());
			pstmt.setString(7, member.getSkinType());
			pstmt.setString(8, member.getSkinComplex());
			pstmt.setString(9, member.getEmail());
			pstmt.setString(10, member.getIsEmail());
			pstmt.setString(11, member.getAddress());
			pstmt.setString(12, member.getPhone());
			pstmt.setString(13, member.getQuestion());
			pstmt.setString(14, member.getAnswer());

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

	public int update(Member member) throws SQLException {
		Connection conn = null;
		int result = 0;
		String sql = "update member set passwd=?, name=?, address=?, phone=?" 
					+" where memberid=?";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection(); // 기 연결된 DBCP이용
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getMemberId());
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

	public List<Member> list() throws SQLException {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		String sql = "select * from member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Member member = new Member();
					member.setMemberId(rs.getString(1));
					member.setPasswd(rs.getString(2));
					member.setName(rs.getString(3));
					member.setAddress(rs.getString(4));
					member.setPhone(rs.getString(5));
					member.setRegDate(rs.getString(6));
					list.add(member);
				} while (rs.next());
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
	
	public int confirm(String id) throws SQLException{
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where memberid=?";
		try {
			conn = getConnection(); 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = 1;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} 
		return result;
	}
	
	public int check(String id, String passwd) throws SQLException {
		int result = 0;
		Connection conn = null;
		String sql = "select passwd from member where memberid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPasswd = rs.getString(1);
				if (dbPasswd.equals(passwd))
					result = 1;  // 아이디 일치, 비번 일치
				else
					result = 0;  // 아이디 일치, 비번 불일치
			} else
				result = -1;  // 아이디 불일치
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
	
	public int delete(String id) throws SQLException{
		Connection conn = null;
		int result = 0;
//		result = check(id, passwd);
//		if(result != 1) return result;  // 1이면 체크를통한 검증완료..  0, -1이면 실패이니 돌아감
		PreparedStatement pstmt = null;
		String sql = "delete from member where memberid=?";
		try {
			conn = getConnection(); 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();  // 삭제되면 1, 실패하면 0 
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} 
		return result;
	}
	
	public String getNick(String id) throws SQLException {
		Connection conn = null;
		String result = "";
		PreparedStatement pstmt = null;
		String sql = "select nickname from member where memberId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("nickname");
			} 
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
	
	public String searchId(String email, String question, String answer) throws SQLException {
		Connection conn = null;
		String result = "";
		PreparedStatement pstmt = null;
		String sql = "select memberid from member where email=? and question=? and answer=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("memberid");
			} else
				result = "";
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

	public String searchPw(Member member) throws SQLException {
		Connection conn = null;
		String result = null;
		PreparedStatement pstmt = null;
		String sql = "select passwd from member where memberId=? and question=? and answer=? ";
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getQuestion());
			pstmt.setString(3, member.getAnswer());
			
			System.out.println("select sql -> " + sql);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("passwd");
				
				System.out.println("searchPw result -> " + result);
			} else
				result = null;
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