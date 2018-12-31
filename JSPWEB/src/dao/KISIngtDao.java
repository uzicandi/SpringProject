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

public class KISIngtDao {
	private static KISIngtDao instance;

	private KISIngtDao() {
	}

	public static KISIngtDao getInstance() {
		if (instance == null) {
			instance = new KISIngtDao();
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
		int result = 0;
		String sql = "select count(*) from ingredient";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("result--------------------> " + result);
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
	
	public int getSearchCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from ingredient where name=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("result--------------------> " + result);
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
	// 검색시 페이지 구함
	public int getSearchCnt(String keyWord) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from ingredient where name like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("result--------------------> " + result);
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
	
	public List<Ingredient> IngtList(int startRow, int endRow) throws SQLException {
		List<Ingredient> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.*" + "from (select * from ingredient order by name) a"
				+ ") where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ingredient ingt = new Ingredient();
				ingt.setIngredientNo(rs.getInt(2));
				ingt.setName(rs.getString(3));
				ingt.setGrade(rs.getString(4));
				ingt.setDanger20(rs.getString(5));
				ingt.setDangerAllergy(rs.getString(6));
				ingt.setSpecialyType(rs.getString(7));
				ingt.setFunctional(rs.getString(8));
				list.add(ingt);
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
	
	
	// 검색 결과 내 재검색
	public List<Ingredient> IngtList2(int startRow, int endRow, String keyWord) throws SQLException {
		List<Ingredient> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select * from (select rownum rn, a.*" + "from (select * from ingredient order by name) a"
//				+ ") where rn between ? and ?";
		String sql = "select * from (select rownum rn, a.*" + "from (select * from ingredient  where name like ? order by name) a"
				+ ") where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setString(1, "%" + keyWord.trim() + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ingredient ingt = new Ingredient();
				ingt.setIngredientNo(rs.getInt(2));
				ingt.setName(rs.getString(3));
				ingt.setGrade(rs.getString(4));
				ingt.setDanger20(rs.getString(5));
				ingt.setDangerAllergy(rs.getString(6));
				ingt.setSpecialyType(rs.getString(7));
				ingt.setFunctional(rs.getString(8));
				list.add(ingt);
			}
		} catch (Exception e) {
			System.out.println("IngtList2-->" + e.getMessage());
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
	
	
	
	public Ingredient select(int ingredientNo) throws SQLException {
		Ingredient ingt = new Ingredient();
		Connection conn = null;
		String sql = "select * from ingredient where ingredientNo=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ingredientNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ingt.setIngredientNo(rs.getInt(1));
				ingt.setName(rs.getString(2));
				ingt.setGrade(rs.getString(3));
				ingt.setDanger20(rs.getString(4));
				ingt.setDangerAllergy(rs.getString(5));
				ingt.setSpecialyType(rs.getString(6));
				ingt.setFunctional(rs.getString(7));
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
		return ingt;
	}

	public int update(Ingredient ingt) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update ingredient set name=?, grade=?, danger20=?, dangerAllergy=?, specialyType=?, functional=? where ingredientNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ingt.getName());
			pstmt.setString(2, ingt.getGrade());
			pstmt.setString(3, ingt.getDanger20());
			pstmt.setString(4, ingt.getDangerAllergy());
			pstmt.setString(5, ingt.getSpecialyType());
			pstmt.setString(6, ingt.getFunctional());
			pstmt.setInt(7, ingt.getIngredientNo());
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

	public int insert(Ingredient ingt) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "insert into ingredient values(fn_MaxNoForTable('Ingredient'),?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ingt.getName());
			pstmt.setString(2, ingt.getGrade());
			pstmt.setString(3, ingt.getDanger20());
			pstmt.setString(4, ingt.getDangerAllergy());
			pstmt.setString(5, ingt.getSpecialyType());
			pstmt.setString(6, ingt.getFunctional());
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

	public int delete(int ingredientNo) throws SQLException {
		Connection conn = null;
		int result = 0;
		String sql = "delete from ingredient where ingredientNo=?";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ingredientNo);
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
	
	//한페이지에 성분 나열
			public List<Ingredient> IngtList() throws SQLException {
				List<Ingredient> list = new ArrayList<>();
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "select * from ingredient order by ingredientNo";
//				String sql = "SELECT * FROM (SELECT ROWNUM rn, A.* FROM (SELECT * FROM ingredient ORDER BY NAME) A)";
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Ingredient ingt = new Ingredient();
						ingt.setIngredientNo(rs.getInt(1));
						ingt.setName(rs.getString(2));
						ingt.setGrade(rs.getString(3));
						ingt.setDanger20(rs.getString(4));
						ingt.setDangerAllergy(rs.getString(5));
						ingt.setSpecialyType(rs.getString(6));
						ingt.setFunctional(rs.getString(7));
						list.add(ingt);
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