package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class KJWMemberDao {
	private static KJWMemberDao instance;

	private KJWMemberDao() {
	}

	// 2.
	public static KJWMemberDao getInstance() {

		if (instance == null) {
			instance = new KJWMemberDao();
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

	/*public int check(String id, String passwd) throws SQLException {
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "select passwd from member where memberId =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(passwd))
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
	}*/
	
	public Member check(String memberId, String passwd) throws SQLException {
		System.out.println("check->start ");
		Connection conn = null;
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = "select passwd, grade, nickname from member where memberId=?";
		Member member = new Member(); //member DTO
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("id->"+memberId+" /passwd->"+passwd);
			if (rs.next()) {
				if (rs.getString(1).equals(passwd)) {
					result = 1;
					member.setMemberId(memberId);
					member.setPasswd(passwd);
					member.setGrade(rs.getString("grade"));
					member.setNickName(rs.getString("nickname"));
				}
				else					
					result = 0; // 비번틀림
			} else
				result = -1;  // 아디틀림
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		member.setResult(result);

		System.out.println("result-> " + result);
		
		return member;
	}

	/*public String searchId(String name, String phone) throws SQLException {
		Connection conn = null;
		String result = "";
		PreparedStatement pstmt = null;
		String sql = "select memberId from member where name=? and phone=? ";
		System.out.println("name -> " + name + "\nphone -> " + phone);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			System.out.println("select sql -> " + sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("memberId");
				System.out.println("searchId result -> " + result);
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
	}*/
	
	public String searchIdPw(String x, String question, String answer, String gubun) throws SQLException {
		Connection conn = null;
		String result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select memberid from member where email=? and question=? and answer=?";
		String sql2 = "select passwd from member where memberid=? and question=? and answer=?";
		try {
			conn = getConnection();
			if(gubun.equals("1")) pstmt = conn.prepareStatement(sql1);
			else pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, x);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
		
/*	public Member select(String memberId) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;	// sql 질의에 의해 생성된 테이블을 저장하는 객체	
		String sql = "select * from member where memberId =?";
		Member member = new Member(); //member DTO
		try {
			conn = getConnection(); //연결
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); // resultset
			if(rs.next()) { //id=PK 이기 때문에 while문 아니고 if문으로 (sql에서 확인가능)
				// ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상
				
				member.setMemberId(rs.getString("memberId"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setJoinDate(rs.getString("joinDate"));

			}
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally {
			// 사용순서와 반대로 close함
			
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return member;
	}*/
	
	public Member select(String memberId) throws SQLException {
		Member member = new Member(); //member DTO
		Connection conn = null;
		String sql = "select * from member where memberId = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memberId); // id를 넣어주기 위해서
			rs = pstmt.executeQuery(); // resultset
			if(rs.next()) { //id=PK 이기 때문에 while문 아니고 if문으로 (sql에서 확인가능)
					member.setMemberId(rs.getString(1));
					member.setPasswd(rs.getString(2));
					member.setName(rs.getString(4));
					member.setAddress(rs.getString(13));
					member.setPhone(rs.getString(12));
					member.setJoinDate(rs.getString(16));  
					
					System.out.println("select sql -> " + sql);
					System.out.println("select passwd result -> " + member.getPasswd());
					System.out.println("select name result -> " + member.getName());
					System.out.println("select address result -> " + member.getAddress());
			}
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return member;
	}
	
	
	public int update(Member member) throws SQLException {
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set passwd=?, name=?, address=?, phone=?, joinDate=? where memberId=? ";
		
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getJoinDate());
			pstmt.setString(6, member.getMemberId());	
			
			result = pstmt.executeUpdate();
			
			System.out.println("DAO update sql -> " + sql);
			System.out.println("DAO update result->" + result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	

}
