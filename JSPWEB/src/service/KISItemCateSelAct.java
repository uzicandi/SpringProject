package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.KISItemDao;


public class KISItemCateSelAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int optionSel = Integer.parseInt(request.getParameter("optionSel"));
			System.out.println("optionSel -->" + optionSel);
			
			KISItemDao kd = KISItemDao.getInstance();
			List<Category> list2 = kd.list2(optionSel);			
			System.out.println("list2 --> " + list2);
			request.setAttribute("list2", list2);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISItemChildCateForm.jsp";
	}

}
