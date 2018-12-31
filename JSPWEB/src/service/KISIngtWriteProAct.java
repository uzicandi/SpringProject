package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ingredient;
import dao.KISIngtDao;

public class KISIngtWriteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");			
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
			
		} catch (Exception e) {
			System.out.println("WriteProAction error -> " + e.getMessage());
		}
		return "KISIngtWritePro.jsp";
	}
}
