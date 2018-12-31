package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ingredient;
import dao.KISIngtDao;

public class KISIngtContentAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int ingredientNo = Integer.parseInt(request.getParameter("ingredientNo"));
			String pageNum = request.getParameter("pageNum");
			KISIngtDao kd = KISIngtDao.getInstance();
			Ingredient ingt = kd.select(ingredientNo);

			request.setAttribute("ingredientNo", ingredientNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("ingt", ingt);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISIngtContent.jsp";
	}

}
