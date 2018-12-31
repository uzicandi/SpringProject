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

public class NYSCateDao {
	private static NYSCateDao instance;

	private NYSCateDao() {
	}

	public static NYSCateDao getInstance() {
		if (instance == null) {
			instance = new NYSCateDao();
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
	
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from category";
		//String sql = "INSERT INTO CATEGORY VALUES(fn_maxnofortable('category'), '립스틱', '', 1)";
		int result = 0;

		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(sql);
			//result = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("mnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
				result = rs.getInt(1);
			}
			System.out.println("total result --> " + result);
		} catch (Exception e) {
			System.out.println("TotalCnt error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}
		return result;
	}
	
	
	public List<Category> list(int startRow, int endRow) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		List<Category> al = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT ROWNUM rn, A.* FROM (SELECT * FROM category ORDER BY parent, ispublic desc, name) A ) where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category cate = new Category();

				cate.setCategoryNo(rs.getInt(2));
				cate.setName(rs.getString(3));
				cate.setParent(rs.getString(4));
				cate.setIsPublic(rs.getString(5));
				
				al.add(cate);
			}
		} catch (Exception e) {
			System.out.println("list error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}
		return al;
	}
	
	
	
	public Category select(int categoryNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category cate = new Category();
		String sql = "select * from category where categoryNo=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			System.out.println(sql); 
			rs = pstmt.executeQuery();
			System.out.println("categoryNo => " + categoryNo);
			
			if (rs.next()) {
				System.out.println("first => " + String.valueOf(rs.getInt(1)));
				
				cate.setCategoryNo(rs.getInt(1)); 
				cate.setName(rs.getString(2)); 
				cate.setParent(rs.getString(3)); 
				cate.setIsPublic(rs.getString(4)); 
			}	
			System.out.println("얍 -> " + cate.getCategoryNo());		
		} catch (Exception e) {
			System.out.println("select error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}
		return cate;
	}
	
	
	public List<Category> selectMC() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Category> ca = new ArrayList<>();
		String sql = "select * from category where parent is null order by name";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			/*pstmt.setInt(1, categoryNo);*/
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category cate = new Category();
				
				cate.setCategoryNo(rs.getInt(1)); 
				cate.setName(rs.getString(2)); 
				cate.setParent(rs.getString(3)); 
				cate.setIsPublic(rs.getString(4)); 
				
				ca.add(cate);
			}			
		} catch (Exception e) {
			System.out.println("select error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}
		return ca;
	}
	
	public List<Category> selectChildCate(String parent) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Category> ca = new ArrayList<>();
		String sql = "select * from category where parent=? order by name";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, parent);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category cate = new Category();
				
				cate.setCategoryNo(rs.getInt(1)); 
				cate.setName(rs.getString(2)); 
				cate.setParent(rs.getString(3)); 
				cate.setIsPublic(rs.getString(4)); 
				
				ca.add(cate);
			}			
		} catch (Exception e) {
			System.out.println("select error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}
		return ca;
	}
	

	
	
	public int confirm(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 1;
		String sql = "SELECT * FROM category WHERE name=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			System.out.println("confirm error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}
		return result;
	}
	
	
	
	public int cateInsert(Category category) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		/*int categoryNo = category.getCategoryNo();*/
		String sql = "insert into category values(fn_maxnofortable('category'),?,?,?)";
		System.out.println(category.getParent().toString());
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			/*pstmt.setInt(1, categoryNo);*/
			pstmt.setString(1, category.getName());
			pstmt.setString(2, category.getParent());
			pstmt.setString(3, category.getIsPublic());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insert error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}		
		return result;
	}
	
	
	
	public int cateParentInsert(Category cate) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into category values (fn_maxnofortable('category'),?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate.getName());
			pstmt.setString(2, "");
			pstmt.setString(3, cate.getIsPublic());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insert error -> " + e.getMessage());
		} finally {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}		
		return result;
	}
	
	
	
	public int update(Category cate) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update category set name=?, parent=?, isPublic=? where categoryNo=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setString(1, cate.getName());
			pstmt.setString(2, cate.getParent());
			pstmt.setString(3, cate.getIsPublic());
			pstmt.setInt(4, cate.getCategoryNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("update error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}	
		return result;
	}

	
	public int delete(int categoryNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
//		Category cate = new Category();
		String sql = "delete from category where categoryNo=?";	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			result = pstmt.executeUpdate();
//			System.out.println(sql);
//			for(int i=0; i<val.length; i++) {
//				pstmt.setInt(1, Integer.parseInt(val[i]));				
//				result = pstmt.executeUpdate();
//				
//			}			
		} catch (Exception e) {
			System.out.println("update error -> " + e.getMessage());
		} finally {
//			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		}	
		return result;
	}

	public int deleteCate(int categoryNo) throws SQLException {
		int result = 1;
		
		Category cate = select(categoryNo);
		if(cate.getParent() == null) {
			List<Category> cateChildList = selectChildCate(cate.getName());
			for(Category cateChild : cateChildList) {
				result = delete(cateChild.getCategoryNo());
			}
		}
		result = delete(categoryNo);
		
		return result;
	}
	
	

	
	
	
	
}


