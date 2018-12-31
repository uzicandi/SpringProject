package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KJWMemberDao;
import dao.Member;
public class KJWupdateFormAct implements CommandProcess {


	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("KJWupdateFormAct start...");
			String memberId = request.getParameter("memberId");
//			String passwd = request.getParameter("passwd");
//			String name = request.getParameter("name");
//			String address = request.getParameter("address");
//			String phone = request.getParameter("phone");
//			String joinDate = request.getParameter("joinDate");
			 
			// 로그인에서 id만 넘어가면 회원정보 넘어가니까 다른거 필요 없음 
		
			KJWMemberDao md = KJWMemberDao.getInstance();
			Member member = md.select(memberId);	// memberId가 member(dto)에 담김
			System.out.println("KJWupdateFormAct memberId->"+member.getMemberId());
			System.out.println("KJWupdateFormAct name->"+member.getName());
			System.out.println("KJWupdateFormAct address->"+member.getAddress());

			request.setAttribute("memberId", member.getMemberId());	//member에 담긴 memberId불러옴
			request.setAttribute("passwd", member.getPasswd());
			request.setAttribute("name", member.getName());
			request.setAttribute("address", member.getAddress());
			request.setAttribute("phone", member.getPhone());
			request.setAttribute("joinDate", member.getJoinDate());
			
		} catch(Exception e) { System.out.println(e.getMessage());
		}
		return "KJWupdateForm.jsp";
	}

}
