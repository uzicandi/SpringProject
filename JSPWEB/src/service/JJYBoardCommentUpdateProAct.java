package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JJYCommentsDao;

public class JJYBoardCommentUpdateProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		int mainNo = Integer.parseInt(request.getParameter("mainNo"));
		int subNo = Integer.parseInt(request.getParameter("subNo"));
		String content = request.getParameter("content");
		int result = 0;
		
		JJYCommentsDao jd = JJYCommentsDao.getInstance();
		
		try {
			result = jd.updateComments(mainNo, subNo, content);

		} catch (Exception e) {
			System.out.println("ItemCommentUpdateAct error -> " + e.getMessage());
		}
		
		request.setAttribute("UpdateDelete", 2);
		request.setAttribute("result", result);
		request.setAttribute("commPageNum", commPageNum);

		return "../comments/JJYSuccessUpdateDelete.jsp";
	}

}
