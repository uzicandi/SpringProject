package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.JJYItemReviewDao;

public class JJYItemReviewListAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 이전에서 가저올 값
//		String memberId = "ReviewWriter";
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("sessionId");
		//int re_level = 0;	// 0:리뷰  1:댓글  2:대댓글
		String ref_Table = "ITEMINFO";
		
		JJYItemReviewDao jd = JJYItemReviewDao.getInstance();
		try {
			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			String pageNum = request.getParameter("pageNum");
			String pageNumRv = request.getParameter("pageNumRv");
			if (pageNumRv == null || pageNumRv.equals("")) {
				pageNumRv = "1";
			}
			int totCnt = jd.getTotalCommentCnt(itemNo, ref_Table);
			int currentPage = Integer.parseInt(pageNumRv);
			int pageSize = 10, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = totCnt - startRow + 1;
			List<Comments> list = jd.list(itemNo, ref_Table, startRow, endRow);
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize -1;
			if (endPage > pageCnt) {
				endPage = pageCnt;
			}
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageNumRv", pageNumRv);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("itemNo", itemNo);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			// ------
			request.setAttribute("memberId", memberId);
			
		} catch (Exception e) {
			System.out.println("JJYItemReviewListAct error -> " + e.getMessage());
		}
		
		return "JJYItemReviewList.jsp";
	}

}
