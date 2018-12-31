package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.SDBBoardDao;

public class SDBBoardUpdateProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			Board board = new Board();
			board.setBoardCategory(request.getParameter("boardCategory"));
			board.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
			board.setTitle(request.getParameter("title"));
			board.setContent(request.getParameter("content"));
			SDBBoardDao bd = SDBBoardDao.getInstance();
			int result = bd.update(board);
			request.setAttribute("result", result);
			request.setAttribute("boardNo", board.getBoardNo());
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("boardCategory", board.getBoardCategory());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardUpdatePro.jsp";
	}

}
