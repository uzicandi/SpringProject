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

public class JJYItemReviewDao {
	private static JJYItemReviewDao instance;

	private JJYItemReviewDao() {
	}

	public static JJYItemReviewDao getInstance() {
		if (instance == null) {
			instance = new JJYItemReviewDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

			System.out.println("DB 연결...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int getTotalCommentCnt(int mainNo, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from comments where mainno=? and ref_table=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setString(2, ref_Table);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			System.out.println("getTotalCnt --> " + result);
		} catch (Exception e) {
			System.out.println("getTotalCommentCnt error -> " + e.getMessage());
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

	public Comments selectComment(int mainNo, int subNo, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Comments comm = null;
		String sql = "select * from comments where mainno=? and subno=? and ref_table=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setString(3, ref_Table);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				comm = new Comments();
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("REF_TABLE"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setContent2(rs.getString("CONTENT2"));
				comm.setContent3(rs.getString("CONTENT3"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));
			}
			System.out.println("selectComment 진행...");
		} catch (Exception e) {
			System.out.println("selectComment error -> " + e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return comm;
	}

	public List<Comments> list(int itemNo, String ref_Table, int startRow, int endRow) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.* from (select * from comments where mainno=? and ref_table=? order by subno desc) a) where rn between ? and ?";

		Comments comm = null;
		List<Comments> comms = new ArrayList<>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			pstmt.setString(2, ref_Table);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comm = new Comments();
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("REF_TABLE"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setContent2(rs.getString("CONTENT2"));
				comm.setContent3(rs.getString("CONTENT3"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));

				comms.add(comm);
				comm = null;
			}
			System.out.println("ReviewList 실행...");
		} catch (Exception e) {
			System.out.println("ReviewList error -> " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return comms;
	}
	
	// comment 전체 다 찾기
	public List<Comments> list2(int itemNo, String ref_Table) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.* from (select * from comments where mainno=? and ref_table=? order by subno desc) a)";

		Comments comm = null;
		List<Comments> comms = new ArrayList<>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			pstmt.setString(2, ref_Table);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comm = new Comments();
				comm.setMainNo(rs.getInt("MAINNO"));
				comm.setSubNo(rs.getInt("SUBNO"));
				comm.setRef_Table(rs.getString("REF_TABLE"));
				comm.setIsPublic(rs.getString("ISPUBLIC"));
				comm.setHits(rs.getInt("HITS"));
				comm.setRef(rs.getInt("REF"));
				comm.setRe_step(rs.getInt("RE_STEP"));
				comm.setRe_level(rs.getInt("RE_LEVEL"));
				comm.setContent(rs.getString("CONTENT"));
				comm.setContent2(rs.getString("CONTENT2"));
				comm.setContent3(rs.getString("CONTENT3"));
				comm.setRating(rs.getInt("RATING"));
				comm.setLikeCnt(rs.getInt("LIKECNT"));
				comm.setMemberId(rs.getString("MEMBERID"));
				comm.setRegDate(rs.getString("REGDATE"));

				comms.add(comm);
				comm = null;
			}
			System.out.println("ReviewList2 실행...");
		} catch (Exception e) {
			System.out.println("ReviewList error -> " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return comms;
	}

	public void reviewHits(int mainNo, int subNo, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update comments set hits = hits+1 where mainNo=? and subno=? and ref_table=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setString(3, ref_Table);
			pstmt.executeUpdate();

			System.out.println("reviewHits 실행...");
		} catch (Exception e) {
			System.out.println("reviewHits error -> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public int likeCnt(int mainNo, int reviewNo, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update comments set likecnt=likecnt+1 where mainNo=? and subno=? and ref_table=?";
		String sql1 = "select likecnt from comments where mainNo=? and subno=? and ref_table=?";
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, reviewNo);
			pstmt.setString(3, ref_Table);
			pstmt.executeUpdate();

			pstmt.close();

			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, reviewNo);
			pstmt.setString(3, ref_Table);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			System.out.println("likeCnt 실행...");
		} catch (Exception e) {
			System.out.println("reviewHits error -> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return result;
	}

	public int writeComment(Comments comm) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into comments (mainno, subno, ref_table, ispublic, re_level, content, content2, content3, rating, memberid, regdate) "
				+ "	values (?, ?, ?, ?, 1, ?, ?, ?, ?, ?, fn_datetochar(sysdate))";
		int result = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comm.getMainNo());
			pstmt.setInt(2, comm.getSubNo());
			pstmt.setString(3, comm.getRef_Table());
			pstmt.setString(4, comm.getIsPublic());
			pstmt.setString(5, comm.getContent());
			pstmt.setString(6, comm.getContent2());
			pstmt.setString(7, comm.getContent3());
			pstmt.setInt(8, comm.getRating());
			pstmt.setString(9, comm.getMemberId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("writeComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return result;
	}

	public int updateComments(Comments comm) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update comments set content=?, content2=?, content3=?, isPublic=?, rating=?, regdate=FN_DATETOCHAR(sysdate) where mainno=? and subno=? and ref_Table=?";
		int result = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comm.getContent());
			pstmt.setString(2, comm.getContent2());
			pstmt.setString(3, comm.getContent3());
			pstmt.setString(4, comm.getIsPublic());
			pstmt.setInt(5, comm.getRating());
			pstmt.setInt(6, comm.getMainNo());
			pstmt.setInt(7, comm.getSubNo());
			pstmt.setString(8, comm.getRef_Table());
			result = pstmt.executeUpdate();

			System.out.println("updateComments 실행..." + result);
		} catch (Exception e) {
			System.out.println("updateComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return result;
	}

	public int deleteComments(int mainNo, int subNo, String ref_Table) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from comments where mainno=? and subno=? and ref_Table=?";
		int result = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			pstmt.setInt(2, subNo);
			pstmt.setString(3, ref_Table);
			result = pstmt.executeUpdate();

			System.out.println("deleteComment 실행..." + result);
		} catch (Exception e) {
			System.out.println("deleteComment error -> " + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return result;
	}

	// 리뷰가 새로 등록되면 평균 별점 다시 구함
	public int itemRatingUpdate(int mainNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
		int count = 0;
		int result = 0;
		String sql = "select sum(rating), count(rating) from comments where mainNo=? and ref_table='ITEMINFO'";
		String sql2 = "update iteminfo set rating=? where itemNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
				count = rs.getInt(2);
				pstmt.close();
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(2, mainNo);
			pstmt.setFloat(1, (float) sum / count);
			result = pstmt.executeUpdate();

			System.out.println("itemRatingUpdate 진행...");
		} catch (Exception e) {
			System.out.println("itemRatingUpdate error -> " + e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return result;
	}

	/**
	 * 사진까지 같이 관리
	 * 
	 */
	public int writeReview(MultipartRequest multi, Comments comm) throws SQLException {
		int result = 1;
		int mainNo = 0;
		int subNo = 0;
		
		try {
			int tmp = writeComment(comm);
			if (tmp > 0) {
//				getMaxNoForTable(String tableName, String referenceTable, int mainNo)
				mainNo = comm.getMainNo();
				subNo = comm.getSubNo();
			}

			if (mainNo > 0 && subNo > 0) {
	System.out.println("리뷰 정상 등록시");
				List<SaveFiles> list = FileProcess.getInstance().fileMoveToPart(multi, "comments", mainNo, subNo);
				if (list != null && list.size() > 0) {
					result = SaveFilesDao.getInstance().insertList(list);
				}
			} else {
				result = 0;
				// 임시 폴더에 저장된 파일 삭제
	System.out.println("임시저장파일 삭제...");
				FileProcess.getInstance().tempFileDelete(multi);
			}
		} catch (Exception e) {
			System.out.println("writeReview error -> " + e.getMessage());
		}
		
		return result;
	}

	public Comments readReview(int mainNo, int subNo, String ref_Table) throws SQLException {
		Comments comm = null;
		
		try {
			reviewHits(mainNo, subNo, ref_Table);

			comm = selectComment(mainNo, subNo, ref_Table);
			comm.setSaveFileList(SaveFilesDao.getInstance().list(mainNo, subNo, "comments"));

		} catch (Exception e) {
			System.out.println("readReview error -> " + e.getMessage());
		}
		
		return comm;
	}

	public int updateReview(MultipartRequest multi, Comments comm, String[] savefiles, String phsyicalPath)
			throws SQLException {
		int result = 1;
		int mainNo = 0;
		int subNo = 0;

		try {
			int tmp = updateComments(comm);
			if (tmp > 0) {
				mainNo = comm.getMainNo();
				subNo = comm.getSubNo();
			}

			if (mainNo > 0 && subNo > 0) {
	System.out.println("리뷰 정상 업데이트시");
				// 일단 기존 파일 삭제
				List<SaveFiles> saveFilesList = SaveFilesDao.getInstance().list(mainNo, subNo, "comments");
	System.out.println("_-----------saveFilesList.size() -> " + saveFilesList.size());
				for (int i = 0; i < savefiles.length; i++) {
					if (savefiles[i] != null && i < saveFilesList.size()) {
						List<SaveFiles> saveFile = saveFilesList.subList(i, i + 1);
						result = FileProcess.getInstance().saveFileDelete(phsyicalPath, saveFile);
						if (result > 0) {
							result = SaveFilesDao.getInstance().deleteList(saveFile);
						}
					}
				}
				// 삭제 후, 삽입
				List<SaveFiles> list = FileProcess.getInstance().fileMoveToPart(multi, "comments", mainNo, subNo);
				if (list != null && list.size() > 0) {
					result = SaveFilesDao.getInstance().insertList(list);
				}
			} else {
				result = 0;
	System.out.println("임시저장파일 삭제...");
				FileProcess.getInstance().tempFileDelete(multi);
			}
		} catch (Exception e) {
			System.out.println("updateReview -> " + e.getMessage());
		}

		return result;
	}

	public String deleteFile(int mainNo, int subNo, String ref_Table, int filesNo, String phsyicalPath) throws SQLException {
		String fileName = null;
		
		try {
			List<SaveFiles> saveFile = SaveFilesDao.getInstance().list(mainNo, subNo, ref_Table, filesNo);
			int result = FileProcess.getInstance().saveFileDelete(phsyicalPath, saveFile);
			if(result > 0) {
	System.out.println("리뷰 수정중, 저장파일 삭제 완료!!");
				fileName = saveFile.get(0).getSavedFileName();
				result = SaveFilesDao.getInstance().deleteList(saveFile);
				if(result > 0) {
	System.out.println("리뷰 수정중, 저장파일 DB 삭제 완료!!");
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

	public int deleteReview(int mainNo, int subNo, String ref_Table, String phsyicalPath) throws SQLException {
		int result = 1;
		
		try {
	System.out.println("Review 삭제 실행..");
			result = JJYCommentDao.getInstance().deleteCommentsAll(subNo, "REVIEW", mainNo);

	System.out.println("Comments 삭제 완료");
			List<SaveFiles> saveFilesList = SaveFilesDao.getInstance().list(mainNo, subNo, "comments");
			result = FileProcess.getInstance().saveFileDelete(phsyicalPath, saveFilesList);

	System.out.println("리뷰 저장파일 삭제 완료");
			result = SaveFilesDao.getInstance().deleteList(saveFilesList);
					
	System.out.println("저장파일 DB삭제 완료");
			result = deleteComments(mainNo, subNo, ref_Table);
			
		} catch (Exception e) {
			System.out.println("deleteReview -> " + e.getMessage());
		}
		
		return result;
	}
	
	public int deleteReviewAll(List<Comments> comms, String phsyicalPath) {
		int result = 1;
		try {
			for(Comments comm : comms) {
				result = deleteReview(comm.getMainNo(), comm.getSubNo(), comm.getRef_Table(), phsyicalPath);
				if(result <= 0) {
	System.out.println("Review 삭제중 이상발생..."+comm.getMainNo()+","+comm.getSubNo()+","+comm.getRef_Table());
					break;
				}
			}
						
		} catch (Exception e) {
			System.out.println("deleteReviewAll error -> " + e.getMessage());
		}
		return result;
	}
}
