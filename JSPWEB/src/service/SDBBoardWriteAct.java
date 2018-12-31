package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SDBBoardWriteAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("writeact 실행");
			int boardNo = 0;
			String boardCategory = request.getParameter("boardCategory");
			String pageNum = request.getParameter("pageNum");
			HttpSession session = request.getSession();
			if (pageNum == null)
				pageNum = "1";
			if (request.getParameter("boardNo") != null) {
				boardNo = Integer.parseInt(request.getParameter("boardNo"));
			}
			request.setAttribute("boardCategory", boardCategory);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("session", session);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardWriteForm.jsp";
	}

}
