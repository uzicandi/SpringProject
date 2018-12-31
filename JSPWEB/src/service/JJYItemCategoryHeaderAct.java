package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.ItemInfo;
import dao.JJYItemCategoryDao;


public class JJYItemCategoryHeaderAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");	
		try {
			String pageNumIC = request.getParameter("pageNumIC");
			if (pageNumIC == null || pageNumIC.equals("")) {
				pageNumIC = "1";
			}
			
			String cateNum = request.getParameter("cateNum");
			if(cateNum == null || cateNum.equals("")) {
				cateNum = "0";
			}
			int categoryNo = Integer.parseInt(cateNum);
			String sortName = request.getParameter("sortName");
			if (sortName == null || sortName.equals("")) {
				sortName = "0";
			}
			int sortNum = Integer.parseInt(sortName);
			
			JJYItemCategoryDao jd = JJYItemCategoryDao.getInstance();			
			List<Category> cateList = jd.cateParentList();
			
			Category cateInfo = jd.cateSelect(categoryNo);
			if(cateInfo.getParent() == null) {
				List<Category> childList = jd.cateChildList(categoryNo);
				request.setAttribute("childList", childList);
			} else {
				List<Category> childList = jd.cateChildList2(categoryNo);
				request.setAttribute("childList", childList);
			}
			
			request.setAttribute("cateList", cateList);
			request.setAttribute("cateInfo", cateInfo);
			request.setAttribute("cateNum", cateNum);
			request.setAttribute("pageNumIC", pageNumIC);
			request.setAttribute("sortNum", sortNum);


		} catch (Exception e) {
			System.out.println("JJYItemCategoryListAct error -> " + e.getMessage());
		}
		
		return "JJYItemCategoryHeader.jsp";
	}

}
