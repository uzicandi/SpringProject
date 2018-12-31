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

import com.oreilly.servlet.MultipartRequest;

public class KISItemDao {
	private static KISItemDao instance;

	private KISItemDao() {
	}

	public static KISItemDao getInstance() {
		if (instance == null) {
			instance = new KISItemDao();
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

	public List<ItemInfo> list(int startRow, int endRow) throws SQLException {
		List<ItemInfo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.*" + "from (select * from iteminfo order by name) a"
				+ ") where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemInfo item = new ItemInfo();
				item.setItemNo(rs.getInt(2));
				item.setCategoryNo(rs.getInt(3));
				item.setBrand(rs.getString(4));
				item.setName(rs.getString(5));
				item.setInfo(rs.getString(6));
				item.setPrice(rs.getInt(7));
				item.setRating(rs.getInt(8));
				item.setHits(rs.getInt(9));
				item.setLikeCnt(rs.getInt(10));
				item.setIsPublic(rs.getString(11));
				item.setMemberId(rs.getString(12));
				item.setRegDate(rs.getString(13));
				list.add(item);
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

	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from iteminfo";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			System.out.println("getTotalCnt --> " + result);
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

	// itemNo 최신번호 select
	public int selectItemNo() throws SQLException {
		Connection conn = null;
		String sql = "select fn_MaxNoForTable('ItemInfo') itemNo from dual";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int itemNum = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				itemNum = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("selectItemNo -> " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return itemNum;
	}

	public int insert(ItemInfo item) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql1 = "insert into iteminfo values(?,?,?,?,?,?,?,?,?,?,?,fn_DateToChar(sysdate))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, item.getItemNo());
			pstmt.setInt(2, item.getCategoryNo());
			pstmt.setString(3, item.getBrand());
			pstmt.setString(4, item.getName());
			pstmt.setString(5, item.getInfo());
			pstmt.setInt(6, item.getPrice());
			pstmt.setInt(7, (int) item.getRating());
			pstmt.setInt(8, item.getHits());
			pstmt.setInt(9, item.getLikeCnt());
			pstmt.setString(10, item.getIsPublic());
			pstmt.setString(11, item.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert -> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	// ingtlist(성분목록 테이블) 데이터 삽입
	public int mappingInsert(int itemNo, int ingredientNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result2 = 0;
		String sql = "insert into ingtlist values(?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2, ingredientNo);
			result2 = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("mappingInsert -> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result2;
	}

	public int mappingDelete(int itemNo) throws SQLException {
		Connection conn = null;
		int result = 0;
		String sql = "delete from ingtlist where itemNo=?";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
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

	public ItemInfo select(int itemNo) throws SQLException {
		ItemInfo item = new ItemInfo();
		Connection conn = null;
		String sql = "select * from iteminfo where itemNo=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				item.setItemNo(rs.getInt(1));
				item.setCategoryNo(rs.getInt(2));
				item.setBrand(rs.getString(3));
				item.setName(rs.getString(4));
				item.setInfo(rs.getString(5));
				item.setPrice(rs.getInt(6));
				item.setRating(rs.getInt(7));
				item.setHits(rs.getInt(8));
				item.setLikeCnt(rs.getInt(9));
				item.setIsPublic(rs.getString(10));
				item.setMemberId(rs.getString(11));
				item.setRegDate(rs.getString(12));
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
		return item;
	}

	public void hits(int itemNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update iteminfo set hits = hits+1 where itemNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("hits--> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public int delete(int itemNo) throws SQLException {
		Connection conn = null;
		int result = 0;
		String sql = "delete from iteminfo where itemNo=?";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
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

	public int update(ItemInfo item) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update iteminfo set categoryNo=?, brand=?, name=?, info=?, price=?, isPublic=?, memberId=? where itemNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item.getCategoryNo());
			pstmt.setString(2, item.getBrand());
			pstmt.setString(3, item.getName());
			pstmt.setString(4, item.getInfo());
			pstmt.setInt(5, item.getPrice());
			pstmt.setString(6, item.getIsPublic());
			pstmt.setString(7, item.getMemberId());
			pstmt.setInt(8, item.getItemNo());
			result = pstmt.executeUpdate();
			System.out.println("업데이트 result 값--> " + result);
		} catch (Exception e) {
			System.out.println("update --> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	// 대분류 카테고리 가져오기
	public List<Category> list() throws SQLException {
		List<Category> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from category where parent is null order by name";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category cate = new Category();
				cate.setCategoryNo(rs.getInt(1));
				cate.setName(rs.getString(2));
				cate.setParent(rs.getString(3));
				cate.setIsPublic(rs.getString(4));
				list.add(cate);
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

	// 
	public List<Category> list3(int categoryNo) throws SQLException {
		List<Category> list2 = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category cate2 = null;
		String sql = "select * from category where parent = (select parent from category where categoryNo=?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cate2 = new Category();
				cate2.setCategoryNo(rs.getInt(1));
				cate2.setName(rs.getString(2));
				cate2.setParent(rs.getString(3));
				cate2.setIsPublic(rs.getString(4));
				list2.add(cate2);

				cate2 = null;
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
		return list2;
	}
	
	// 소분류 카테고리 가져오기
	public List<Category> list2(int categoryNo) throws SQLException {
		List<Category> list2 = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category cate2 = null;
		String sql = "select * from category where parent = (select name from category where categoryNo=?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cate2 = new Category();
				cate2.setCategoryNo(rs.getInt(1));
				cate2.setName(rs.getString(2));
				cate2.setParent(rs.getString(3));
				cate2.setIsPublic(rs.getString(4));
				list2.add(cate2);
				cate2 = null;
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
		return list2;
	}

	// 제품수정화면에서 해당제품번호로 카테고리번호 select
	public Category cateSelect(int categoryNo) throws SQLException {
		Category cate = new Category();
		Connection conn = null;
		String sql = "select categoryNo, name, parent from category where categoryNo=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cate.setCategoryNo(rs.getInt(1));
				cate.setName(rs.getString(2));
				cate.setParent(rs.getString(3));
//				cate.setIspublic(rs.getString(4));
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
		return cate;
	}
	
	// 제품명 검색
		public List<ItemInfo> itemSearch(String name) throws SQLException {
			List<ItemInfo> itemSearch = new ArrayList<>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from iteminfo where name like ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemInfo itemInfo = new ItemInfo();
					itemInfo.setItemNo(rs.getInt(1));
					itemInfo.setCategoryNo(rs.getInt(2));
					itemInfo.setBrand(rs.getString(3));
					itemInfo.setName(rs.getString(4));
					itemInfo.setInfo(rs.getString(5));
					itemInfo.setPrice(rs.getInt(6));
					itemInfo.setRating(rs.getFloat(7));
					itemInfo.setHits(rs.getInt(8));
					itemInfo.setLikeCnt(rs.getInt(9));
					itemInfo.setIsPublic(rs.getString(10));
					itemInfo.setMemberId(rs.getString(11));
					itemInfo.setRegDate(rs.getString(12));
					itemSearch.add(itemInfo);
				}
			} catch (Exception e) {
				System.out.println("itemSearch --> " + e.getMessage());
			} finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			return itemSearch;
		}
		
		/**
		 * 	사진 관리 추가
		 * 
		 */
		public int writeIteminfo(MultipartRequest multi, ItemInfo item) throws SQLException {
			int result = 1;
			int mainNo = 0;
			
			try {
				int tmp = insert(item);
				if(tmp > 0) {
					mainNo = item.getItemNo();
				}

				if(mainNo > 0) {
			System.out.println("ItemInfo 정상 들록시");
					List<SaveFiles> list = FileProcess.getInstance().fileMoveToPart(multi, "ITEMINFO", mainNo, 0);
					if (list != null && list.size() > 0) {
						result = SaveFilesDao.getInstance().insertList(list);
					}
				} else {
					result = 0;
			System.out.println("임시저장파일 삭제...");
					FileProcess.getInstance().tempFileDelete(multi);
				}
			} catch (Exception e) {
				System.out.println("writeIteminfo error -> " + e.getMessage());
			}
			
			return result;
		}

		public ItemInfo readIteminfo(int itemNo) throws SQLException {
			ItemInfo item = null;
			
			try {
				hits(itemNo);
				
				item = select(itemNo);
				item.setSaveFileList(SaveFilesDao.getInstance().list(itemNo, "ITEMINFO"));
				
			} catch (Exception e) {
				System.out.println("readIteminfo error -> " + e.getMessage());
			}
			
			return item;
		}

		public int updateIteminfo(MultipartRequest multi, ItemInfo item, String[] savefiles, String phsyicalPath) {
			int result = 0;
			int mainNo = 0;
			
			try {
				result = update(item);
				if(result > 0) {
					mainNo = item.getItemNo();
				}
				
				if(mainNo > 0) {
		System.out.println("ItemInfo 정상 업데이트시");
					List<SaveFiles> saveFilesList = SaveFilesDao.getInstance().list(mainNo, "ITEMINFO");
					for(int i=0; i<savefiles.length; i++) {
						if (savefiles[i] != null && i < saveFilesList.size()) {
							List<SaveFiles> saveFile = saveFilesList.subList(i, i+1);
							result = FileProcess.getInstance().saveFileDelete(phsyicalPath, saveFile);
							if (result > 0) {
								result = SaveFilesDao.getInstance().deleteList(saveFile);
							}
						}
					}
					
					List<SaveFiles> list = FileProcess.getInstance().fileMoveToPart(multi, "ITEMINFO", mainNo, 0);
					if (list != null && list.size() > 0) {
						result = SaveFilesDao.getInstance().insertList(list);
					}
				}	else {
					result = 0;
		System.out.println("임시저장파일 삭제...");
					FileProcess.getInstance().tempFileDelete(multi);
				}
				
			} catch (Exception e) {
				System.out.println("updateIteminfo error -> " + e.getMessage());
			}
			
			return result;
		}
		
		public String deleteFile(int mainNo, String ref_Table, int filesNo, String phsyicalPath) throws SQLException {
			String fileName = null;
			
			try {
				List<SaveFiles> saveFile = SaveFilesDao.getInstance().list(mainNo, 0, ref_Table, filesNo);
				int result = FileProcess.getInstance().saveFileDelete(phsyicalPath, saveFile);
				if(result > 0) {
		System.out.println("Item 수정중, 저장파일 삭제 완료!!");
					fileName = saveFile.get(0).getSavedFileName();
					result = SaveFilesDao.getInstance().deleteList(saveFile);
					if(result > 0) {
		System.out.println("Item 수정중, 저장파일 DB 삭제 완료!!");
					} else {
						fileName = "DBerror2";
					}
				} else {
					fileName = "DBerror1";
				}
			} catch (Exception e) {
				System.out.println("deleteFile -> " + e.getMessage());
			}

			return fileName;
		}
		
		public int deleteIteminfo(int itemNo, String phsyicalPath) throws SQLException {
			int result = 1;
			try {
				List<Comments> comms = JJYItemReviewDao.getInstance().list2(itemNo, "ITEMINFO");
				result = JJYItemReviewDao.getInstance().deleteReviewAll(comms, phsyicalPath);

		System.out.println("리뷰 전체 삭제 완료");	
				List<SaveFiles> saveFilesList = SaveFilesDao.getInstance().list(itemNo, "ITEMINFO");
				result = FileProcess.getInstance().saveFileDelete(phsyicalPath, saveFilesList);

		System.out.println("ITEM 저장파일 삭제 완료");
				result = SaveFilesDao.getInstance().deleteList(saveFilesList);

		System.out.println("저장파일 DB삭제 완료");
				result = delete(itemNo);

			} catch (Exception e) {
				System.out.println("deleteIteminfo error -> " + e.getMessage());
			}

			return result;
		}

	// 좋아요 기능
	public int likeCnt(int itemNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update iteminfo set likecnt=likecnt+1 where itemNo=?";
		String sql1 = "select likeCnt from iteminfo where itemNo=?";
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, itemNo);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("likeCnt 실행...");
		} catch (Exception e) {
			System.out.println("likeCnt error -> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return result;
	}
}
