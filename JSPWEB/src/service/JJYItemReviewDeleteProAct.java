package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileManager;
import dao.JJYItemReviewDao;

public class JJYItemReviewDeleteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String pageNum = request.getParameter("pageNum");
			String pageNumRv = request.getParameter("pageNumRv");
			int mainNo = Integer.parseInt(request.getParameter("itemNo"));
			int subNo = Integer.parseInt(request.getParameter("reviewNo"));
			String ref_Table = "ITEMINFO";
			
			JJYItemReviewDao jd = JJYItemReviewDao.getInstance();
//			int result = jd.deleteComments(mainNo, subNo, ref_Table);
			String phsyicalPath = FileManager.getPygicalPath(request);
			int result = jd.deleteReview(mainNo, subNo, ref_Table, phsyicalPath);
			
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageNumRV", pageNumRv);
			request.setAttribute("mainNo", mainNo);
			request.setAttribute("subNo", subNo);
			
		} catch (Exception e) {
			System.out.println("ItemReviewDeletePro error -> " + e.getMessage());
		}
		
		return "JJYItemReviewDeletePro.jsp";
	}

}
