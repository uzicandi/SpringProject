package service;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.JJYCommentDao;

public class JJYItemCommentReadAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// memberID는 session에서 가져오는걸로
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		int mainNo = Integer.parseInt(request.getParameter("mainNo"));
		String ref_Table = request.getParameter("ref_Table");
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		List<Comments> comms = null;
		
		JJYCommentDao jd = JJYCommentDao.getInstance();
		
		try {
			comms = jd.selectComments(mainNo, ref_Table, reviewNo, commPageNum);

		} catch (Exception e) {
			System.out.println("ItemCommentWriteAct error -> " + e.getMessage());
		}
		
		System.out.println("comms -> " + comms);
		
		request.setAttribute("ReadWrite", 1);
		request.setAttribute("comms", comms);
		
//		JSONArray jsonArr = new JSONArray(comms);
//		PrintWriter pw = response.getWriter();
//		pw.println(jsonArr);
		
		return "JJYSuccessReadWrite.jsp";
	}

}
