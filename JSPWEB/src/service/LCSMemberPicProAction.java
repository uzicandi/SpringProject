package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import dao.FileManager;
import dao.FileProcess;
import dao.MemberDisplay;
import dao.MemberProcess;

public class LCSMemberPicProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String loginUserId = (String) request.getSession().getAttribute("loginUserId");
		String loginUserId = (String) request.getSession().getAttribute("sessionId");
		System.out.println("loginUserId -> " + loginUserId);
		if(loginUserId == null) return "/";
	/*	try {
			// 파일처리 공통 사용 - 서버에 임시폴더로 로딩 : MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response); 
			MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response);
//			String file = multi.getParameter("file");
			MemberDisplay md = new MemberDisplay(request, response, "Write");
//System.out.println("게시글 저장(DB) + 파일 정리 Move + 파일 저장(DB)");	
			int result = MemberProcess.getInstance().writeMemberMapping(multi, loginUserId);
			request.setAttribute("result", result);
//System.out.println("result->"+ result);			
		} catch(Exception e) { System.out.println(e.getMessage()); }*/
			
		
		try {
			// 파일처리 공통 사용 - 서버에 임시폴더로 로딩
			MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response);
//			String file = multi.getParameter("file");
System.out.println("display 실행");
			MemberDisplay md = new MemberDisplay(request, response);
//System.out.println("게시글 저장(DB) + 파일 정리 Move + 파일 저장(DB)");	
			String realPath = FileManager.getPygicalPath(request);
System.out.println("realPath -> "+realPath);
			int result = MemberProcess.getInstance().writeMemberMapping(multi, loginUserId, realPath);
			request.setAttribute("result", result);
		} catch(Exception e) { System.out.println(e.getMessage()); }


		return "fixResult.jsp";
	}

}
