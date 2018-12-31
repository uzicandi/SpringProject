package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JJYCommentDao;

public class JJYItemCommentDeleteAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		int mainNo = Integer.parseInt(request.getParameter("mainNo"));
		int subNo = Integer.parseInt(request.getParameter("subNo"));
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		int result = 0;
		
		JJYCommentDao jd = JJYCommentDao.getInstance();
		
		try {
			result = jd.deleteComments(mainNo, subNo, reviewNo);

		} catch (Exception e) {
			System.out.println("ItemCommentDeleteAct error -> " + e.getMessage());
		}
		
		request.setAttribute("UpdateDelete", 0);
		request.setAttribute("result", result);
		request.setAttribute("commPageNum", commPageNum);

		return "JJYSuccessUpdateDelete.jsp";
	}

}
