package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.KDWMemberDao;
import dao.Member;

public class KDWUpdateProAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("UpdateProAction Start..");
			Member member = new Member();
			String grade = request.getParameter("grade");
			String memberId = request.getParameter("memberId");

			System.out.println("KDWUpdateProAction grade->"+grade);
			System.out.println("KDWUpdateProAction memberId->"+memberId);
		
			member.setMemberId(memberId);
			member.setGrade(grade);
			
			KDWMemberDao bd = KDWMemberDao.getInstance();					
			int result = bd.update(member);
			
			request.setAttribute("grade", grade);
			request.setAttribute("result", result);
			
			
		} catch(Exception e) {System.out.println(e.getMessage());}
		
	return "../member/KDWupdatePro.jsp";
	
	} 

}
