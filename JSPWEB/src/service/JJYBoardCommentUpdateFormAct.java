package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.JJYCommentsDao;

public class JJYBoardCommentUpdateFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		int mainNo = Integer.parseInt(request.getParameter("mainNo"));
		int subNo = Integer.parseInt(request.getParameter("subNo"));
		String trId = request.getParameter("trId");
		Comments comm = null;
				
		try {
			JJYCommentsDao jd = JJYCommentsDao.getInstance();
			comm = jd.selectComment(mainNo, subNo);
			System.out.println("여기까지는 성공...");
		} catch (Exception e) {
			System.out.println("ItemCommentUpdateAct error -> " + e.getMessage());
		}
		
		request.setAttribute("UpdateDelete", 1);
		request.setAttribute("comm", comm);
		request.setAttribute("commPageNum", commPageNum);
		request.setAttribute("trId", trId);
		

		return "../comments/JJYSuccessUpdateDelete.jsp";
	}

}
