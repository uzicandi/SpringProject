package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.JJYItemReviewDao;

public class JJYItemReviewUpdateFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			String memberId = (String)session.getAttribute("sessionId");
			
			int mainNo = Integer.parseInt(request.getParameter("itemNo"));
			int subNo = Integer.parseInt(request.getParameter("reviewNo"));
			String pageNum = request.getParameter("pageNum");
			String pageNumRv = request.getParameter("pageNumRv");
			String ref_Table = "ITEMINFO";
			
			JJYItemReviewDao jd = JJYItemReviewDao.getInstance();
//			Comments comm = jd.selectComment(mainNo, subNo, ref_Table);
			Comments comm = jd.readReview(mainNo, subNo, ref_Table);
			
			request.setAttribute("memberId", memberId);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageNumRv", pageNumRv);
			request.setAttribute("comm", comm);
			request.setAttribute("startNum", 0);
		} catch (Exception e) {
			System.out.println("ItemReviewUpdateForm error -> " + e.getMessage());
		}
		
		return "JJYItemReviewUpdateForm.jsp";
	}

}
