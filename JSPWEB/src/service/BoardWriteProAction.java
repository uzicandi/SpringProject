package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Board;
import dao.BoardDao;
import dao.BoardDisplay;
import dao.BoardProcess;
import dao.FileManager;
import dao.FileProcess;
import dao.SaveFiles;
import dao.SaveFilesDao;

public class BoardWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginUserId = (String) request.getSession().getAttribute("loginUserId");
		if(loginUserId == null) return "/";
System.out.println("BoardWriteProAction");		
System.out.println("파일 임시 저장");	

		try {
			// 파일처리 공통 사용 - 서버에 임시폴더로 로딩 : MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response); 
			MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response);
			String boardCategory = multi.getParameter("boardCategory");
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String viewGrade = multi.getParameter("viewGrade");
			String isPublic = multi.getParameter("isPublic");
			String pageNum = multi.getParameter("pageNum");

			BoardDisplay bd = new BoardDisplay(request, response, "Write");
System.out.println("게시글 저장(DB) + 파일 정리 Move + 파일 저장(DB)");	
			int result = BoardProcess.getInstance().writeBoard(multi, boardCategory, title, content, viewGrade, isPublic, loginUserId);
			request.setAttribute("result", result);
			request.setAttribute("boardCategory", boardCategory);
			request.setAttribute("pageNum", pageNum);
//System.out.println("result->"+ result);			
//System.out.println("boardCategory->"+ bd.getBoardCategory());			
//System.out.println("pageNum->"+bd.getPageNum());			
		} catch(Exception e) { System.out.println(e.getMessage()); }

		return "board/boardWritePro.jsp";
	}

}
