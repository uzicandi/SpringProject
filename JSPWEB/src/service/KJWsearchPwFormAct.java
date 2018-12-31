package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KJWMemberDao;
public class KJWsearchPwFormAct implements CommandProcess {

//1. memberId, question, answer를 parameter로 설정
//2. memberId, question, answer를 가지고 md.searchPw(-,-,-)
//3. 
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			KJWMemberDao md = KJWMemberDao.getInstance();
		//	String result = md.searchPw(memberId,question,answer);
			
		//	request.setAttribute("result", result);
			request.setAttribute("memberId", memberId);
			request.setAttribute("question", question);
			request.setAttribute("answer", answer);
			
		} catch(Exception e) { System.out.println(e.getMessage());
		}
		return "KJWsearchPwForm.jsp";
	}

}
