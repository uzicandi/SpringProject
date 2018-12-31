package service;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HWKMemberDao;

public class HWKDeleteProAction implements CommandProcess {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("memberid");
			String passwd = request.getParameter("passwd");
			HttpSession session = request.getSession();
			int result = 0;
			if(id.equals(session.getAttribute("sessionId")) || passwd.equals(session.getAttribute("sessionpwd"))) {
				HWKMemberDao md = HWKMemberDao.getInstance();
				result = md.delete(id);
			}
			request.setAttribute("result", result);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "deletePro.jsp";
	}
}