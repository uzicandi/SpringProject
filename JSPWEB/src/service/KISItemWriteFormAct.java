package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Category;
import dao.KISItemDao;


public class KISItemWriteFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String memberId = (String)session.getAttribute("sessionId");
			
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null)
				pageNum = "1";
			
			KISItemDao kd = KISItemDao.getInstance();
			List<Category> list = kd.list();			
			
			request.setAttribute("memberId", memberId);
			request.setAttribute("list", list);
			request.setAttribute("pageNum", pageNum);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISItemWriteForm.jsp";
	}

}
