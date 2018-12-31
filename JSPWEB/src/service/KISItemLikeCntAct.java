package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KISItemDao;

public class KISItemLikeCntAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		try {
			
			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			
			KISItemDao kd = KISItemDao.getInstance();
			int likeCnt = kd.likeCnt(itemNo);
			
			request.setAttribute("likeCnt", likeCnt);
						
		} catch (Exception e) {
			System.out.println("KISItemReviewLikeCntAct error -> " + e.getMessage());
		}
		return "KISItemLikeCntPro.jsp";
	}

}
