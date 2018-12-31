package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.SDBBoardDao;

public class SDBBoardDeleteAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			String temp = request.getParameter("boardNo");
			String boardCategory = request.getParameter("boardCategory");
			int boardNo = Integer.parseInt(temp);
			SDBBoardDao bd = SDBBoardDao.getInstance();
			Board board = bd.select(boardNo);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("boardCategory", boardCategory);
			request.setAttribute("board", board);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardDeleteForm.jsp";
	}

}
