package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class MemberProcess {
	private static MemberProcess instance;
	private MemberProcess() {}
	public static MemberProcess getInstance() {
		if (instance == null) { instance = new MemberProcess(); }
		return instance;
	}
	
//	public BoardListDisplay listBoard(HttpServletRequest request, HttpServletResponse response) throws SQLException {
//		BoardListDisplay bld = new BoardListDisplay(request, response, "List");
//		List<BoardDisplay> boardDisplay = 
//				BoardDao.getInstance().list(
//					bld.getLoginUserId()
//				, 	bld.getLoginUserGrade()
//				, 	bld.getBoardCategory()
//				, 	bld.getStartRow()
//				, 	bld.getEndRow()
//				);
//		bld.setBoardDisplay(boardDisplay);
//
//		return bld;
//	}
	
	// Memeber 정보 저장(DB) + 파일 정리 Move + 파일 저장(DB)
	public int writeMemberMapping(MultipartRequest multi, String memberId, String realPath) throws SQLException {
		int result = 1;
		int mainNo = 0;
		
		mainNo = MemberFilesDao.getInstance().getMainNo(memberId);  // MemberFiles에 값이 있나확인
		if(mainNo >= 0){  // MemberFiles 테이블에 값이 있으면
			SaveFiles saveFile = SaveFilesDao.getInstance().getPic(mainNo);  // 경로지정
			result = FileProcess.getInstance().saveFileDelete(realPath, saveFile);  // delete메소드는 0값을 return (폴더파일삭제)
			if(result == 0) {  // 폴더에 파일삭제
				mainNo = MemberFilesDao.getInstance().insert(new MemberFiles(memberId));  // MemberFiles 테이블에 값 저장
				List<SaveFiles> list = FileProcess.getInstance().fileMoveToPart(multi, "Member", mainNo, 0);
				if(list != null && list.size() > 0) {
					result = SaveFilesDao.getInstance().insertList(list);  // SaveFiles 테이블에 값 저장
				}
			}
		} else {
			result = 0;
			// 임시 폴더에 저장된 파일 삭제
			FileProcess.getInstance().tempFileDelete(multi);
		}
		return result;
	}
//
//	// 게시글 저장(DB) + 파일 정리 Move + 파일 저장(DB)
	public int updateBoard(MultipartRequest multi, String memberId, int mainNo) throws SQLException {
		int result = 1;
//		int mainNo = BoardDao.getInstance().update(new Board(boardNo, boardCategory, title, content, viewGrade, isPublic, memberId));
		// 실제 파일정보를 구함 => query
//		int result = BoardProcess.getInstance().deleteBoard(FileManager.getPygicalPath(request), board.getBoardNo(), 0, "Board", 0);
//		int result = BoardProcess.getInstance().deleteBoard(FileManager.getPygicalPath(request), board.getBoardNo(), 0, "Board", 0);
//		public static String getPygicalPath(HttpServletRequest request) {
//			return request.getServletContext().getRealPath("/");
//		}
		// 실제 파일 삭제
//		result = deleteSaveFiles(FileManager.getLogicalPath("root"), mainNo, 0, "Member", 0)
		
		// 파일 저장
		if(mainNo > 0){
System.out.println("정상 게시물 등록시");			
			List<SaveFiles> list = FileProcess.getInstance().fileMoveToPart(multi, "Member", mainNo, 0);
			if(list != null && list.size() > 0) {
				result = SaveFilesDao.getInstance().insertList(list);
			}
		} else {
			result = 0;
			// 임시 폴더에 저장된 파일 삭제
			FileProcess.getInstance().tempFileDelete(multi);
		}
		return result;
	}
//
//	public BoardDisplay readBoard(String loginUserId, String loginUserGrade, int boardNo) throws SQLException {
//		BoardDao bd = BoardDao.getInstance();
//		bd.readCount(boardNo);
//		BoardDisplay boardDisplay = bd.select(boardNo);
//		boardDisplay.setSaveFileList(SaveFilesDao.getInstance().list(boardNo, 0, "Board"));
//		
//		List<CommentsDisplay> commentDisplayList1 = CommentsDao.getInstance().list(loginUserId, loginUserGrade, boardNo, "Board");
//		List<CommentsDisplay> commentDisplayList2 = new ArrayList<>(); 
//System.out.println("boardDispaly");
//		for (CommentsDisplay commentsDisplay : commentDisplayList1) {
//			List<SaveFiles> saveFileList = SaveFilesDao.getInstance().list(boardNo, commentsDisplay.getSubNo(), "Board");
//			commentsDisplay.setCommentFileList(saveFileList);
////			commentDisplayList1.add(commentsDisplay);
//			commentDisplayList2.add(commentsDisplay);
//System.out.println("commentsDisplay"+commentsDisplay.getContent());
//		}
//		// 테스트 해볼것 commentDisplayList1로 되는지...
////		boardDisplay.setCommentDisplay(commentDisplayList1);
//		boardDisplay.setCommentDisplay(commentDisplayList2);
//
//		return boardDisplay;
//	}
//
////	// commentsProcess에 존재해야함
////	public CommentsDisplay readComments(String loginUserId, String loginUserGrade, int boardNo, int subNo) throws SQLException {
////		CommentsDao cd = CommentsDao.getInstance();
////		CommentsDisplay commentDisplay = cd.select(loginUserId, loginUserGrade, boardNo, subNo, "Board");
////		commentDisplay.setCommentFileList(SaveFilesDao.getInstance().list(boardNo, subNo, "Board"));
////
////		return commentDisplay;
////	}
//
//	public int deleteBoard(String pygicalBasePath, int mainNo, int subNo, String ref_Table, int filesNo) throws SQLException {
//
//		// 1. 파일 삭제 + 파일 데이터 삭제
//		// 2. 게시물 관련 덧글 삭제
//		int result = deleteComments(pygicalBasePath, mainNo, subNo, ref_Table, filesNo);
//		// 3. 게시물 삭제
//		result += BoardDao.getInstance().delete(mainNo);
//
//		return result;
//	}
//
//	private int deleteComments(String pygicalBasePath, int mainNo, int subNo, String ref_Table, int filesNo) throws SQLException {
//		// 1. 파일 삭제 + 파일 데이터 삭제
//		int result = this.deleteSaveFiles(pygicalBasePath, mainNo, subNo, ref_Table, filesNo);
//		// 2. 게시물 관련 덧글 삭제
////		*기능 만들면 살릴것 
////		result += CommentsDao.getInstance().delete(mainNo, subNo, ref_Table);
//		
//		return result;
//	}
	public int deleteSaveFiles(String pygicalBasePath, int mainNo, int subNo, String ref_Table, int filesNo) throws SQLException {
		// 1. 실제 파일 삭제
		// 1.1 Data List Select(파일 경로 정보)
		List<SaveFiles> saveFilesList = SaveFilesDao.getInstance().list(mainNo, subNo, "Member", filesNo);
		// 1.2 파일 삭제
		int result = FileProcess.getInstance().saveFileDelete(pygicalBasePath, saveFilesList);
		// 2. 데이터 삭제
		if(result > 0) {
			result += SaveFilesDao.getInstance().deleteList(saveFilesList);
		}
		return result;
	}
	

}
