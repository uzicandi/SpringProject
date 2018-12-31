package service;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Clip;
import dao.ClipDao;

public class ClipDeleteProAction implements CommandProcess {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String gubun = request.getParameter("gubun");
			String id = (String)request.getSession().getAttribute("sessionId");
			String[] temp = request.getParameterValues("mainno");
			int result = 0;
			if(temp != null) {
				if(gubun.equals("1")) {
					int[] main = new int[temp.length];
					for(int i=0; i<temp.length; i++) {
						main[i] = Integer.parseInt(temp[i]);
					}
					temp = request.getParameterValues("subno");
					int[] sub = new int[temp.length];
					for(int i=0; i<temp.length; i++) {
						sub[i] = Integer.parseInt(temp[i]);
					}
					ClipDao cd = ClipDao.getInstance();
					result = cd.delete(id, main, sub);
				} else {
					int[] main = new int[temp.length];
					for(int i=0; i<temp.length; i++) {
						main[i] = Integer.parseInt(temp[i]);
					}
					ClipDao cd = ClipDao.getInstance();
					result = cd.delete2(id, main);
				}
				request.setAttribute("result", result);
				request.setAttribute("gubun", "1");
			}		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "clipDeletePro.jsp";
	}
}