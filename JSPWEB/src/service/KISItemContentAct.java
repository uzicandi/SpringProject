package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.FileManager;
import dao.Ingredient;
import dao.ItemInfo;
import dao.JJYItemCategoryDao;
import dao.KISIngtListDao;
import dao.KISItemDao;

public class KISItemContentAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JJYItemCategoryDao jd = JJYItemCategoryDao.getInstance();
		try {
			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			String pageNum = request.getParameter("pageNum");
			String pageNumRv = request.getParameter("pageNumRv");
			if(pageNumRv == null || pageNumRv.equals("")) { 
				pageNumRv = "1";
			}
			String cateNum = request.getParameter("cateNum");
			
			KISItemDao kd = KISItemDao.getInstance();
			KISIngtListDao kd2 = KISIngtListDao.getInstance();
//			kd.hits(itemNo);
//			ItemInfo item = kd.select(itemNo);
			ItemInfo item = kd.readIteminfo(itemNo);
			String realPath = FileManager.getPygicalPath(request);
			System.out.println("realPath -->> " + realPath);
			// 등록된 성분
			List<Ingredient> list = kd2.list(itemNo);
			// 등록된 카테고리
			List<Category> list2 = kd.list();
			
			List<Category> cateAll = jd.cateAll();
			request.setAttribute("cateAll", cateAll);
			
			request.setAttribute("list2", list2);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageNumRv", pageNumRv);
			request.setAttribute("cateNum", cateNum);
			request.setAttribute("item", item);
			request.setAttribute("list", list);
			
		} catch (Exception e) {
			System.out.println("KISItemContentAct -->" + e.getMessage());
		}
		return "KISItemContent.jsp";
	}

}