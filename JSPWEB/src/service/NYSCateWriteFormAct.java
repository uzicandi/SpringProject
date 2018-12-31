package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.NYSCateDao;

public class NYSCateWriteFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {			
			String pageNumCt = request.getParameter("pageNumCt");
			
			NYSCateDao yd = NYSCateDao.getInstance();
			List<Category> cate = yd.selectMC();
			
			request.setAttribute("pageNumCt", pageNumCt);
			request.setAttribute("cate", cate);
			
			System.out.println("NYSCateWriteFormAct 실행...");
		} catch (Exception e) {
			System.out.println("NYSCateWriteFormAct error -> " + e.getMessage());
		}
		
		return "NYSCateWriteForm.jsp";
	}

}
