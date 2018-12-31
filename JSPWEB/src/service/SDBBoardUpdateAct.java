package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.SDBBoardDao;

public class SDBBoardUpdateAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			SDBBoardDao bd = SDBBoardDao.getInstance();
			Board board = bd.select(boardNo);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "boardUpdateForm.jsp";
	}

}
