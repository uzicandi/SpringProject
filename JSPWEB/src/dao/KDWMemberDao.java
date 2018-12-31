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






public class KDWMemberDao {

	private static KDWMemberDao instance; //자기 타입으러 인스턴스를 선언해놧다
	private KDWMemberDao() {  } //생성자를 만들어 놧다
	//이게 싱글톤이다 db용량 줄이는데 참 좋다.
	public static KDWMemberDao getInstance() {
		if (instance == null) { // 인스턴스가 널일때만 사용하겟다.
			instance = new KDWMemberDao();}
		return instance;
		}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
					//ds안에 context만든 내용 들어감
					ctx.lookup("java:comp/env/jdbc/OracleDB"); 
					//env까지는 환경 작업이다.
			conn = ds.getConnection();//이게 jdbc역활을 함
			System.out.println("DB연결 ...");
		} catch (Exception e) {System.out.println(e.getMessage());}
		return conn;
	}

	
	public int check(String id, String passwd) throws SQLException {
		int result  = 0;  				Connection conn = null;
		String sql  = "select passwd from member2 where id=?"; 
		PreparedStatement pstmt = null; ResultSet rs = null;
		try { 
			conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPasswd = rs.getString(1);
				if (dbPasswd.equals(passwd)) result = 1;
				else result = 0;
			} else   result = -1;
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	public List<Member> list(int startRow, int endRow) throws SQLException {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		
		String sql ="select * from (select rownum rn , a.*"
				+ "from (select *From member order by name desc) a)"
				+ "Where rn Between ? and ?";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,startRow);
		pstmt.setInt(2,endRow);
		rs= pstmt.executeQuery();
		
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("memberId")); 
				member.setPasswd(rs.getString("passwd"));
				member.setNickName(rs.getString("nickName"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setSex(rs.getString("sex"));
				member.setSkinType(rs.getString("skinType"));
				member.setSkinComplex(rs.getString("skinComplex"));
				member.setGrade(rs.getString("grade"));
				member.setEmail(rs.getString("email"));
				member.setIsEmail(rs.getString("isEmail"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
				member.setJoinDate(rs.getString("joinDate"));
				member.setRegDate(rs.getString("regDate"));
				
				list.add(member);
		
			}
		}catch(Exception e) {System.out.println(e.getMessage());
	}finally {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
	return list;
		
	}
	
	public List<Member> mList(String memberId) throws SQLException {
		List<Member> mList = new ArrayList<Member>();
		Connection conn = null;
		
		String sql ="select * from member where memberId = ?";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,memberId);
		
		rs= pstmt.executeQuery();
		
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("memberId")); 
				member.setPasswd(rs.getString("passwd"));
				member.setNickName(rs.getString("nickName"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setSex(rs.getString("sex"));
				member.setSkinType(rs.getString("skinType"));
				member.setSkinComplex(rs.getString("skinComplex"));
				member.setGrade(rs.getString("grade"));
				member.setEmail(rs.getString("email"));
				member.setIsEmail(rs.getString("isEmail"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
				member.setJoinDate(rs.getString("joinDate"));
				member.setRegDate(rs.getString("regDate"));
				
				mList.add(member);
		
			}
		}catch(Exception e) {System.out.println(e.getMessage());
	}finally {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
	return mList;
		
	}
	
	public List<Member> search3(String search , int index) throws SQLException {
		List<Member> searchList = new ArrayList<Member>();
		Connection conn = null;
		String sql ="";		
		if(index==0){
		    sql = "select * from member where memberId like ? order by name asc";
		   }else if(index==1){
		    sql = "select * from member where name like ? order by name asc";
		   }else{
		    sql = "select * from member where address like ? order by name asc";
		   }
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		System.out.println("search3 sql->" +sql );
	

		pstmt.setString(1,search);
		
		rs= pstmt.executeQuery();
		
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("memberId")); 
				member.setPasswd(rs.getString("passwd"));
				member.setNickName(rs.getString("nickName"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setSex(rs.getString("sex"));
				member.setSkinType(rs.getString("skinType"));
				member.setSkinComplex(rs.getString("skinComplex"));
				member.setGrade(rs.getString("grade"));
				member.setEmail(rs.getString("email"));
				member.setIsEmail(rs.getString("isEmail"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
				member.setJoinDate(rs.getString("joinDate"));
				member.setRegDate(rs.getString("regDate"));
				
				searchList.add(member);
		
			}
		}catch(Exception e) {System.out.println(e.getMessage());
	}finally {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
	return searchList;
		
	}
	
	
	
	public Member select(String grade) throws SQLException {
		Member member = new Member();
		Connection conn = null;
		String sql ="select * from member where grade =? ";
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try { conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,grade);
		rs= pstmt.executeQuery();
		if(rs.next()) {
			
				member.setGrade(rs.getString(1));
				  
		}
			
		
		}catch(Exception e) {System.out.println(e.getMessage());
	}finally {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
		return member;
		
	}
	
//	public Member select(String grade) throws SQLException {
//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection conn = null;
//		String sql ="select * from member where grade =?";
//		Member member = new Member();
//		
//		try { conn = getConnection();
//		stmt = conn.prepareStatement(sql);
//		rs= stmt.executeQuery(sql);
//		if(rs.next()) {
//			
//				member.setGrade(rs.getString("grade"));
//				
//				  
//		}
//			
//		
//		}catch(Exception e) {System.out.println(e.getMessage());
//	}finally {
//		if(rs != null) rs.close();
//		if(stmt != null) stmt.close();
//		if(conn != null) conn.close();
//	}
//		return member;
//		
//	}
	
	
	public int update(Member member) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql ="update member set   grade=? where memberid = ?";
		
		
		System.out.println("sql->"+sql);
		try { conn = getConnection();
			pstmt = conn.prepareStatement(sql);
		
			System.out.println("member.getGrade()->"+member.getGrade());
			System.out.println("member.getMemberId()->"+member.getMemberId());

			
			pstmt.setString(1, member.getGrade());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();	
			
		}catch(Exception e) {
			System.out.println("Exception->"+e.getMessage());
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
		
	}
	
	
	public int confirm(String id) throws SQLException {
		int result =1;
		Connection conn = null;
		String sql ="select * from member2 where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt -> "+pstmt.toString());
			pstmt.setString(1,id);
			System.out.println("id2 -> "+pstmt.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) result = 1;
			else result = 0;
		
		}catch(Exception e) {System.out.println(e.getMessage());
	}finally {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
		return result;
		
	}
	
	
	public int delete(String id, String passwd) throws SQLException {
		
		int result =0;
		Connection conn = null;
		System.out.println("id->" +id);
		System.out.println("passwd->" +passwd);
		result = check(id,passwd);
		if(result != 1) return result;
		String sql ="delete from member2 where id=?";
		System.out.println("passwd->" +passwd);
		PreparedStatement pstmt = null;
		
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			System.out.println("result->" +result);
		
		}catch(Exception e) {System.out.println(e.getMessage());
	}finally {
		
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
		return result;
		
	}
	
	
	public int getTotalCnt()  throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		
		String sql = "select count(*) from member";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) tot = rs.getInt(1);
			System.out.println("tot->"+tot);
		}catch(Exception e) { 
			System.out.println(e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		System.out.println("tot->"+tot);
		return tot; 
}
	
	
	public int getTotalCnt2( String search,int index,String find)  throws SQLException {
		
		Connection conn = null;
//		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		
		if (find.equals("아이디")) {
			index = 0;
		} else if (find.equals("이름")) {
			index = 1;
		} else {
			index = 2;
		}
		
		String sql ="";		
		if(index==0){
		    sql = "select count(*) from member where memberId like ? order by name asc";
		   }else if(index==1){
		    sql = "select count(*) from member where name like ? order by name asc";
		   }else{
		    sql = "select count(*) from member where address like ? order by name asc";
		   }		
		search = '%'+ search + '%';
		
		System.out.println("getTotalCnt2 sql->" +sql );
		System.out.println("getTotalCnt2 search->" +search );
		
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
//		   stmt = conn.createStatement();
			pstmt.setString(1,  search);
			rs = pstmt.executeQuery();
//			stmt = getString(1,search);
			if(rs.next()) tot = rs.getInt(1);
	
			System.out.println("getTotalCnt2 tot->" +tot );
			
		}catch(Exception e) { 
			System.out.println(e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return tot; 
  }
	
}
