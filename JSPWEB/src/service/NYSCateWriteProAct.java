package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.NYSCateDao;


public class NYSCateWriteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			int pageNumCt = Integer.parseInt(request.getParameter("pageNumCt"));
			/*int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));*/
			String parent = request.getParameter("parent");
			String name = request.getParameter("name");
			String isPublic = request.getParameter("isPublic");
			
			System.out.println("parent=>"+parent);
			System.out.println("name=>"+name);
			System.out.println("isPublic=>"+isPublic);
			
			Category cate = new Category();
			/*cate.setCategoryNo(categoryNo);*/
			cate.setName(name);
			cate.setParent(parent);
			cate.setIsPublic(isPublic);

			NYSCateDao yd = NYSCateDao.getInstance();
			int result = yd.cateInsert(cate);
			
			request.setAttribute("result", result);
			request.setAttribute("pageNumCt", pageNumCt);
		} catch (Exception e) {
			System.out.println("WriteProAction error -> " + e.getMessage());
		}
		return "NYSCateWritePro.jsp";
	}

}
