package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KISIngtDao;

public class KISIngtDeleteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			int ingredientNo = Integer.parseInt(request.getParameter("ingredientNo"));
			KISIngtDao kd = KISIngtDao.getInstance();
			int result = kd.delete(ingredientNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("ingredientNo", ingredientNo);
			request.setAttribute("result", result);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISIngtDeletePro.jsp";
	}

}
