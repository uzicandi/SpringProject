package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SDBBoardDao;

public class SDBBoardDeleteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("DeleteProAct 시작");
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String boardCategory = request.getParameter("boardCategory");
			SDBBoardDao bd = SDBBoardDao.getInstance();
			int result = bd.delete(boardNo);
			
			System.out.println("boarcate delete -> " + boardCategory);
			
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			request.setAttribute("boardCategory", boardCategory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardDeletePro.jsp";
	}

}
