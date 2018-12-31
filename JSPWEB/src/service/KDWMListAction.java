package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.KDWMemberDao;
import dao.Member;

public class KDWMListAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		
		try {
			                                        
			String memberID = request.getParameter("memberId");			
			System.out.println("KDWMListAction memberID->" + memberID);
			
			
			KDWMemberDao kd = KDWMemberDao.getInstance();
			List<Member> list = kd.mList(memberID);   
			
			System.out.println("list->" +list);
			System.out.println("list.size->" +list.size());
			
			request.setAttribute("memberID", memberID);			
			request.setAttribute("list", list);
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KDWMList.jsp";
	}

	
}
