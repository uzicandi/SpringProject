package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.JJYCommentDao;

public class JJYItemCommentContentAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		
		//-------- memberId 필요
//		String userId = "commentWriter";
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("sessionId");

		
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String ref_Table = "REVIEW";
		String ref_Table1 = "ITEMINFO";
		
		JJYCommentDao jd = JJYCommentDao.getInstance();
		Comments comm = null;
		
		try {
			// Review 정보 얻기 및 댓글 개수 구하기
			comm = jd.getCommentInfo(itemNo, reviewNo, ref_Table1, ref_Table);
					
		} catch (Exception e) {
			System.out.println("ItemCommentListAct error -> " + e.getMessage());
		}
		
		request.setAttribute("userId", userId);
		request.setAttribute("comm", comm);
		request.setAttribute("ref_Table", ref_Table);

		System.out.println("ItemCommentListAct...");
		
		return "JJYItemCommentContent.jsp";
	}

}
