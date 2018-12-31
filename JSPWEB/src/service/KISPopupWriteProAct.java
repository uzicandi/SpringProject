package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ingredient;
import dao.KISIngtDao;

public class KISPopupWriteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			// 화면 타입 : PageType - pop  : 성분팝업 목록 화면
			String PageType = request.getParameter("PageType");
			if (PageType == null || PageType.equals("")) {
				PageType = "";
			}
			
			String name = request.getParameter("name");
			String grade = request.getParameter("grade");
			String danger20 = request.getParameter("danger20");
			String dangerAllergy = request.getParameter("dangerAllergy");
			String specialyType = request.getParameter("specialyType");
			String functional = request.getParameter("functional");

			Ingredient ingt = new Ingredient();
			ingt.setName(name);
			ingt.setGrade(grade);
			ingt.setDanger20(danger20);
			ingt.setDangerAllergy(dangerAllergy);
			ingt.setSpecialyType(specialyType);
			ingt.setFunctional(functional);

			KISIngtDao kd = KISIngtDao.getInstance();
			int result = kd.insert(ingt);

			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			
			request.setAttribute("PageType", PageType);
		} catch (Exception e) {
			System.out.println("WriteProAction error -> " + e.getMessage());
		}
		return "KISPopupWritePro.jsp";
	}

}
