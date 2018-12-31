package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.SDBCommentsDao;

public class SDBCommentWriteAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			System.out.println("commentWriteAct 실행");
			
			SDBCommentsDao cd = SDBCommentsDao.getInstance();
			Comments comments = new Comments();
			String pageNum = request.getParameter("pageNum");
			System.out.println("pageNum -> " + pageNum);
			
			
			
			String memberId = request.getParameter("memberId");
			String content = request.getParameter("content");
			System.out.println("memberId -> " + memberId);
			System.out.println("content -> " + content);
			int boardNo;
			String temp = request.getParameter("boardNo");
			System.out.println("temp -> " + temp);
			boardNo = Integer.parseInt(temp);
			System.out.println(boardNo);
			System.out.println("2");
			
			
			comments.setMemberId(memberId);
			comments.setContent(content);

			int result = cd.insert(comments, boardNo);
			request.setAttribute("comments", comments);
			request.setAttribute("result", result);

		} catch (Exception e) {
			System.out.println("여기 오나?");
			System.out.println(e.getMessage());
		}
		return "boardUpdateForm.jsp";

	}

}
