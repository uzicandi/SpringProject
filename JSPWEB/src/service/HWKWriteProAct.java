package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.HWKMemberDao;

public class HWKWriteProAct implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	        request.setCharacterEncoding("utf-8"); 
	        Member member = new Member();        
	        member.setMemberId(request.getParameter("memberId"));
	        member.setPasswd(request.getParameter("passwd"));
	        member.setNickName(request.getParameter("nickname"));
	        member.setName(request.getParameter("name"));
	        member.setBirth(request.getParameter("year")+request.getParameter("month")+request.getParameter("day"));
	        member.setSex(request.getParameter("sex"));
	        member.setSkinType(request.getParameter("skintype"));
	        member.setSkinComplex(request.getParameter("skincomplex"));
	        member.setEmail(request.getParameter("email"));
	        member.setIsEmail(request.getParameter("isemail"));
	        member.setPhone(request.getParameter("phone"));
	        member.setAddress(request.getParameter("address"));
	        member.setQuestion(request.getParameter("question"));
	        member.setAnswer(request.getParameter("answer"));
	        
	        
	        HWKMemberDao md = HWKMemberDao.getInstance();  // DB
	        int result = md.insert(member);   
	        request.setAttribute("result", result);
	        System.out.println("result -> " + result);
	        } catch(Exception e) { System.out.println(e.getMessage()); }
        return "writePro.jsp";
	}

}
