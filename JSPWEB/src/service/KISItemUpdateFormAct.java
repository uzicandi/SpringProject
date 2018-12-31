package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Category;
import dao.Ingredient;
import dao.ItemInfo;
import dao.KISIngtListDao;
import dao.KISItemDao;

public class KISItemUpdateFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			HttpSession session = request.getSession();
			String memberId = (String)session.getAttribute("sessionId");
			
			String pageNum = request.getParameter("pageNum");
			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			
			KISItemDao kd = KISItemDao.getInstance();
//			ItemInfo item = kd.select(itemNo);
			ItemInfo item = kd.readIteminfo(itemNo);
			
			// 제품정보의 카테고리상세 정보
			Category cateInfo = kd.cateSelect(item.getCategoryNo()); 
			List<Category> list2 = kd.list(); //대분류 카테고리 
			if(cateInfo.getParent() == null) {
				List<Category> childList = kd.list2(item.getCategoryNo()); // 소분류 카테고리	parent가 null이면 
				request.setAttribute("childList", childList);
			}else {
				List<Category> childList = kd.list3(item.getCategoryNo());
				request.setAttribute("childList", childList);
			}
			
			// 제품수정화면에서 등록된 성분 display
			KISIngtListDao kd2 = KISIngtListDao.getInstance();
			List<Ingredient> list = kd2.list(itemNo);		
			
			request.setAttribute("memberId", memberId);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("list2", list2);
			
			request.setAttribute("cateInfo", cateInfo);
			request.setAttribute("list", list);			
			request.setAttribute("itemNo", itemNo);
			request.setAttribute("item", item);
			request.setAttribute("startNum", 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISItemUpdateForm.jsp";
	}

}