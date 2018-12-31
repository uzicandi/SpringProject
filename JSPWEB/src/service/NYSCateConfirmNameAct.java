package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NYSCateDao;

public class NYSCateConfirmNameAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		try {
			System.out.println("NYSCateConfirmNameAct 도착...");
			String name = request.getParameter("name");
			System.out.println("name -> " + name);
			
			NYSCateDao yd = NYSCateDao.getInstance();
			int result = yd.confirm(name);
			
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			
		}
		return "NYSCateConfirmName.jsp";
	}

}
