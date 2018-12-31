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

public class SDBItemDao {
	private static SDBItemDao instance;
	
	private SDBItemDao() {
		
	}
	
	public static SDBItemDao getInstance() {
		if (instance == null) {
			instance = new SDBItemDao();
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
	
	public List<ItemInfo> listBrand() throws SQLException {
		List<ItemInfo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.* from (select distinct(brand) from iteminfo) a)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemInfo item = new ItemInfo();
				item.setBrand(rs.getString(2));
				list.add(item);
			}
		} catch (Exception e) {
			System.out.println("디에이오 리스트 익셉션");
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
	
	public List<ItemInfo> list() throws SQLException {
		List<ItemInfo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select itemno, name, rating, price from iteminfo where rating is not null order by rating desc) where rownum <= 8";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemInfo item = new ItemInfo();
				item.setItemNo(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setRating(rs.getFloat(3));
				item.setPrice(rs.getInt(4));
				list.add(item);
			}
		} catch (Exception e) {
			System.out.println("유저 메인 리스트 디에이오 익셉션");
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
	
	public List<ItemInfo> list1(String brand) throws SQLException {
		List<ItemInfo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select itemno, name, price, filename from iteminfo a, (select mainno, filepath, filepath||'/'||savedfilename filename from savefiles where ref_table='ITEMINFO' and filesno=1) b where a.itemno=b.mainno and brand=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemInfo item = new ItemInfo();
				item.setItemNo(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setPrice(rs.getInt(3));
				item.setFile(rs.getString(4));
				list.add(item);
			}
		} catch (Exception e) {
			System.out.println("디에이오 리스트1 익셉션");
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

	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from (select count(*) from iteminfo group by brand)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public int getTotalCnt1(String brand) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from iteminfo where brand=? and info is not null";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			System.out.println("getTotalCnt1 --> " + result);
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

}
