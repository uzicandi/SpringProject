package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KJWMemberDao;
import dao.Member;


public class KJWupdateProAct implements CommandProcess {

//1. memberId, question, answer를 파라미터로 받고, memberDTO 세팅
//2. String result = searchPw(member)
//3. memberId, question, answer 저장하고 searchPwPro.jsp 리턴
	
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("KJWupdateProAct start");
			request.setCharacterEncoding("utf-8");
			String memberId = request.getParameter("memberId");
	//		Member1 member = new Member1();
			Member member = new Member();
			System.out.println("KJWupdateProAct member...");
			System.out.println("memberId->"+memberId);
			
			member.setMemberId(request.getParameter("memberId"));
			member.setPasswd(request.getParameter("passwd"));
			member.setName(request.getParameter("name"));	
			member.setAddress(request.getParameter("address"));
			member.setPhone(request.getParameter("phone"));
			member.setJoinDate(request.getParameter("joinDate"));
		
			KJWMemberDao md = KJWMemberDao.getInstance();
			int result = md.update(member);
			
			System.out.println("KJWupdateProAct passwd->"+member.getPasswd());
			System.out.println("KJWupdateProAct name->"+member.getName());
			System.out.println("KJWupdateProAct address->"+member.getAddress());
			System.out.println("KJWupdateProAct memberId->"+member.getMemberId());
			
			request.setAttribute("result", result);
			request.setAttribute("passwd", member.getPasswd());
			request.setAttribute("name", member.getName());
			request.setAttribute("address", member.getAddress());
			request.setAttribute("phone", member.getPhone());
			
			System.out.println("KJWupdateAct result->"+result);
			
//			member.setPasswd(request.getParameter("passwd"));
//			member.setName(request.getParameter("name"));	
//			member.setAddress(request.getParameter("address"));
//			member.setPhone(request.getParameter("phone"));
//			member.setMemberId(request.getParameter("memberId"));
	//		member.setRegDate(request.getParameter("regDate"));	
			
		}catch(Exception e) {System.out.println(e.getMessage());	}
		return "/member/KJWupdatePro.jsp";
		
	}

}
