package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;

import dao.KDWMemberDao;
 
public class KDWUpdateFormAction implements CommandProcess {


	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String grade = request.getParameter("grade");
			String memberId = request.getParameter("memberId");
			System.out.println("grade->"+grade);
			System.out.println("memberId->"+memberId);
			KDWMemberDao bd = KDWMemberDao.getInstance();
			
			Member member = bd.select(grade); 
			
			request.setAttribute("grade", grade);
			request.setAttribute("memberId", memberId);
		} catch(Exception e) {System.out.println(e.getMessage());}
		
	return "../member/KDWupdateForm.jsp";
	}
}
