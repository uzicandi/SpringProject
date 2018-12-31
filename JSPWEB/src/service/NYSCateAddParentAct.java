package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.NYSCateDao;

public class NYSCateAddParentAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("NYSinsertData start");
		try {
			String name = request.getParameter("name");
			System.out.println("NYSinsertData name->" + name);
			String isPublic = request.getParameter("isPublic");
			System.out.println("NYSinsertData isPublic->" + isPublic);

			Category cate = new Category();
			cate.setName(name);
			cate.setIsPublic(isPublic);

			NYSCateDao yd = NYSCateDao.getInstance();
			int result = yd.cateParentInsert(cate);
			
			request.setAttribute("result", result);

			System.out.println("NYSinsertData result->" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return "NYSAddParentSucess.jsp";
	}

}
