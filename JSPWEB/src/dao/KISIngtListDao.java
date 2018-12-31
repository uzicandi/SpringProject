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

public class KISIngtListDao {
	private static KISIngtListDao instance;

	private KISIngtListDao() {
	}

	public static KISIngtListDao getInstance() {
		if (instance == null) {
			instance = new KISIngtListDao();
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
	// 팝업에서 체크한 성분번호의 이름 select해서 화면에 보여줌
	public List<Ingredient> list(int itemNo) throws SQLException {
		List<Ingredient> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ingt.ingredientNo, ingt.name from ingtlist list, ingredient ingt where ingt.ingredientNo = list.ingredientNo and itemno=?";
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ingredient ingt = new Ingredient();
				ingt.setIngredientNo(rs.getInt(1));
				ingt.setName(rs.getString(2));
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
	// 매핑테이블에 저장된 성분번호의 이름 select
	public String select(int ingredientNo) throws SQLException {
		Connection conn = null;
		String sql = "select name from ingredient where ingredientNo=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ingredientNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
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
		return name;
	}
	
	public List<IngtList> mappingList(int itemNo) throws SQLException {
		List<IngtList> mappList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ingtlist where itemno=(select itemno from iteminfo where itemno=?)";
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IngtList ingtlist = new IngtList();
				ingtlist.setItemNo(rs.getInt(1));
				ingtlist.setIngredientNo(rs.getInt(2));
				mappList.add(ingtlist);
				
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
		return mappList;
	}
}