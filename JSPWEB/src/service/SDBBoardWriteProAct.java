package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;

import dao.Board;
import dao.BoardDisplay;
import dao.BoardProcess;
import dao.FileProcess;
import dao.SDBBoardDao;

public class SDBBoardWriteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		System.out.println("writeproact 실행");
		request.setCharacterEncoding("utf-8");		
		
		try {
			MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response);
			String boardCategory = multi.getParameter("boardCategory");
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String isPublic = multi.getParameter("isPublic");
			String pageNum = multi.getParameter("pageNum");	
				
			BoardDisplay bd = new BoardDisplay(request, response, "Write");
			System.out.println("게시글 저장(DB) + 파일 정리 Move + 파일 저장(DB)");	
			int result = BoardProcess.getInstance().writeBoard(multi, boardCategory, title, content, "1", isPublic, sessionId);
			
			request.setAttribute("result", result);
			request.setAttribute("boardCategory", boardCategory);
			request.setAttribute("pageNum", pageNum);

			
			
			/*Board board = new Board();
			board.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
			System.out.println("4");
			board.setBoardCategory(request.getParameter("category"));
			board.setTitle(request.getParameter("title"));
			board.setContent(request.getParameter("content"));
			board.setMemberId(request.getParameter("memberId"));
			board.setIsPublic(request.getParameter("isPublic"));
			board.setRegDate(request.getParameter("regDate"));
			System.out.println("여기 오나?");
			SDBBoardDao dbPro = SDBBoardDao.getInstance();// DB
//			int result = dbPro.insert(board);
			System.out.println("result -> " + result);
			request.setAttribute("board", board);
			request.setAttribute("boardCategory", board.getBoardCategory());
			request.setAttribute("boardNo", board.getBoardNo());
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardWritePro.jsp";
	}

}
