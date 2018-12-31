package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClipDao;

public class SDBBoardClipAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("BoardClipAct 실행");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String title = request.getParameter("title");
			HttpSession session = request.getSession();
			String memberId = (String)session.getAttribute("sessionId");
			
			int result = 0;
			ClipDao cd = ClipDao.getInstance();	
			result = cd.insert(memberId, boardNo, title);

			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "SDBBoardClipPro.jsp";
	}
}
