package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.JJYCommentsDao;

public class SDBBoardCommentContentAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		//-------- memberId 필요		
		HttpSession session = request.getSession();
		
		System.out.println(session.getAttribute("sessionId"));
		String memberId = (String)session.getAttribute("sessionId");
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String ref_Table = "BOARD";
		
		JJYCommentsDao jd = JJYCommentsDao.getInstance();
		Board board = null;
		
		try {
			// Review 정보 얻기 및 댓글 개수 구하기
			board = jd.getBoardInfo(boardNo, ref_Table);
					
		} catch (Exception e) {
			System.out.println("ItemCommentListAct error -> " + e.getMessage());
		}
		
		request.setAttribute("userId", memberId);
		request.setAttribute("comm", board);
		request.setAttribute("ref_Table", ref_Table);

		System.out.println("BoardCommentListAct...");
		
		return "SDBBoardCommentContent.jsp";
	}

}
