package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.SDBBoardDao;
import dao.SaveFiles;
import dao.SaveFilesDao;

public class SDBBoardContentAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			System.out.println("SDBBoardContentAct start");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			System.out.println("boardNo -> " + boardNo);
			//String pageNum = request.getParameter("pageNum");
			SDBBoardDao bd = SDBBoardDao.getInstance();
			bd.hits(boardNo);
			Board board = bd.select(boardNo);
			
			SaveFilesDao fd = SaveFilesDao.getInstance();				//
			int count = 0;
			count = fd.getCnt(boardNo);
//			List<SaveFiles> savefiles = fd.list(boardNo, 0, "board", 1);//
			List<SaveFiles> savefiles = fd.list(boardNo, "BOARD", count);
			
			System.out.println("getTitle -> " + board.getTitle());
			System.out.println("count -> " +count);
			request.setAttribute("board", board);
			request.setAttribute("savefiles", savefiles);
			request.setAttribute("count", count);			
			
			HttpSession session = request.getSession();
			session.setAttribute("boardNo", boardNo);
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardContent.jsp";
	}

}
