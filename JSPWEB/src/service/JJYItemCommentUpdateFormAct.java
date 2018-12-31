package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.JJYCommentDao;

public class JJYItemCommentUpdateFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
System.out.println("--------JJYItemCommentUpdateFormAct 실행---------");
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
System.out.println("commPageNum -> " + commPageNum);
		int mainNo = Integer.parseInt(request.getParameter("mainNo"));
System.out.println("mainNo -> " + mainNo);
		int subNo = Integer.parseInt(request.getParameter("subNo"));
System.out.println("subNo -> " + subNo);
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
System.out.println("reviewNo -> " + reviewNo);
		String ref_Table = request.getParameter("ref_Table");
System.out.println("ref_Table -> " + ref_Table);		
		String trId = request.getParameter("trId");
System.out.println("trId -> " + trId);
		
		Comments comm = null;		
		try {
			JJYCommentDao jd = JJYCommentDao.getInstance();
			comm = jd.selectComment(mainNo, subNo, ref_Table, reviewNo);
			System.out.println("여기까지는 성공...");
		} catch (Exception e) {
			System.out.println("ItemCommentUpdateAct error -> " + e.getMessage());
		}
		
		request.setAttribute("UpdateDelete", 1);
		request.setAttribute("comm", comm);
		request.setAttribute("commPageNum", commPageNum);
		request.setAttribute("trId", trId);
		

		return "JJYSuccessUpdateDelete.jsp";
	}

}
