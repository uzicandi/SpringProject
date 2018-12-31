package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.NYSCateDao;


public class NYSCateUpdateFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));		
			String pageNumCt = request.getParameter("pageNumCt");
			
			NYSCateDao yd = NYSCateDao.getInstance();
			
			System.out.println("categoryNo => " + categoryNo);
			System.out.println("==========start query==========");
			Category cate = yd.select(categoryNo);
						
			request.setAttribute("categoryNo", categoryNo);	
			request.setAttribute("pageNumCt", pageNumCt);
			request.setAttribute("cate", cate);
					
			System.out.println("No=> "+ String.valueOf(cate.getCategoryNo()));
			
		} catch (Exception e) {
			System.out.println("updateFromAction error -> " + e.getMessage());
		}
		return "NYSCateUpdateForm.jsp";
	}
}
