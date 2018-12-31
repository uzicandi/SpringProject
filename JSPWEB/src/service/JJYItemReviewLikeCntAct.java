package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JJYItemReviewDao;

public class JJYItemReviewLikeCntAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		try {
			
			int mainNo = Integer.parseInt(request.getParameter("mainNo"));
			int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
			String ref_Table = "ITEMINFO";
			
			JJYItemReviewDao jd = JJYItemReviewDao.getInstance();
			int likeCnt = jd.likeCnt(mainNo, reviewNo, ref_Table);
			
			request.setAttribute("likeCnt", likeCnt);
						
		} catch (Exception e) {
			System.out.println("JJYItemReviewLikeCntAct error -> " + e.getMessage());
		}
		return "JJYItemReviewLikeCntPro.jsp";
	}

}
