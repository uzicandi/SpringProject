package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KJWMemberDao;
import dao.Member;

public class SDBMemberLoginAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			System.out.println("SDBMemberLoginAct start");
			String memberId = request.getParameter("memberId");
			String passwd = request.getParameter("passwd");
			KJWMemberDao KMD = KJWMemberDao.getInstance();
			/*int check = KMD.loginCheck(memberId, passwd);*/
//			int check = KMD.check(memberId, passwd);
			Member member = KMD.check(memberId, passwd);
//			System.out.println("로그인엑트 check -> " + check);
			HttpSession session = request.getSession();
			int result = member.getResult();
			if(result == 1) {
				session.setAttribute("sessionId", memberId);
				session.setAttribute("passwd", passwd);
				session.setAttribute("grade", member.getGrade());
				session.setAttribute("nickname", member.getNickName());
			}			
			request.setAttribute("result", result);
			
		}catch(Exception e) {System.out.println(e.getMessage());	}
		
		
		return "KJWloginPro.jsp";
	}

}
