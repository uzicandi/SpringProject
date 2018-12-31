package service;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;


import dao.Comments;
import dao.Ingredient;
import dao.JJYCommentsDao;

public class JJYBoardCommentReadAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// memberID는 session에서 가져오는걸로
		HttpSession session = request.getSession();
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		
		int mainNo = (int) session.getAttribute("boardNo");
		List<Comments> comms = null;
		
		JJYCommentsDao jd = JJYCommentsDao.getInstance();
		
		try {
			//comms = jd.selectComments(mainNo, commPageNum);
			System.out.println("mainNo -> " + mainNo);
			System.out.println("commPageNum -> " + commPageNum);
			comms = jd.selectBoardComments(mainNo, commPageNum);

		} catch (Exception e) {
			System.out.println("ItemCommentWriteAct error -> " + e.getMessage());
		}
		
		System.out.println("comms -> " + comms);
		
		request.setAttribute("ReadWrite", 1);
		request.setAttribute("comms", comms);
		
//		JSONArray jsonArr = new JSONArray(comms);
//		PrintWriter pw = response.getWriter();
//		pw.println(jsonArr);
		
		return "../comments/JJYSuccess.jsp";
	}

}
