package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NYSCateDao;

public class NYSCateDeleteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//			System.out.println("왓냐???????????????????????????????????????");
			
//			String[] val = request.getParameterValues("check");
			String pageNumCt = request.getParameter("pageNumCt");
			System.out.println(pageNumCt);
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			System.out.println(categoryNo);
			NYSCateDao yd = NYSCateDao.getInstance();
			
//			int result = yd.delete(categoryNo);
			int result = yd.deleteCate(categoryNo);
			
			request.setAttribute("pageNumCt", pageNumCt);
//			request.setAttribute("categoryNo", categoryNo);
			request.setAttribute("result", result);
			
			
			
		} catch (Exception e) {
			System.out.println("deleteFormAction error -> " + e.getMessage());
		}
		
		return "NYSCateDeletePro.jsp";
	}

}
