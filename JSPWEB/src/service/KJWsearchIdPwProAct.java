package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KJWMemberDao;

public class KJWsearchIdPwProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			KJWMemberDao KMD = KJWMemberDao.getInstance();
			String id = request.getParameter("memberId");
			String email = request.getParameter("email");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			String result = "";
			String gubun = request.getParameter("gubun"); 
			if(gubun.equals("1")) {
				result = KMD.searchIdPw(email, question, answer, gubun);
				request.setAttribute("id", result);
				System.out.println("idresult ->" +result);
				return "KJWsearchIdPwPro.jsp";
			} else {
				result = KMD.searchIdPw(id, question, answer, gubun);
				request.setAttribute("passwd", result);
				System.out.println("pwdresult ->" +result);
				return "KJWsearchIdPwPro.jsp";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KJWsearchIdPwPro.jsp";
	}
}
