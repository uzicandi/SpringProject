package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileManager;
import dao.KISItemDao;

public class KISItemDeleteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			
			
			KISItemDao kd = KISItemDao.getInstance();	
			String phsyicalPath = FileManager.getPygicalPath(request);
			int result = kd.deleteIteminfo(itemNo, phsyicalPath);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("itemNo", itemNo);
			request.setAttribute("result", result);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISItemDeletePro.jsp";
	}

}
