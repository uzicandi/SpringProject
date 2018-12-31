package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ingredient;
import dao.KISIngtDao;

public class KISIngtListAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		KISIngtDao kd = KISIngtDao.getInstance();
		try {
			request.setCharacterEncoding("UTF-8");
			String keyWord = request.getParameter("keyWord");
			String pageNum = request.getParameter("pageNum");
			
			System.out.println("keyWord-->" + keyWord);
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			
			if (keyWord == null || keyWord == "") {
				
				int totCnt = kd.getTotalCnt();
				int currentPage = Integer.parseInt(pageNum);
				int pageSize = 10, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = startRow + pageSize - 1;
				int startNum = totCnt - startRow + 1;
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				int endPage = startPage + blockSize -1;
				
				List<Ingredient> list = kd.IngtList(startRow, endRow);
				request.setAttribute("list", list);
				
				if (endPage > pageCnt) endPage = pageCnt;
				
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("startNum", startNum);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
			} else {
				int totCnt = kd.getSearchCnt(keyWord);
				int currentPage = Integer.parseInt(pageNum);
				int pageSize = 10, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = startRow + pageSize - 1;
				int startNum = totCnt - startRow + 1;
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				int endPage = startPage + blockSize -1;

				if (endPage > pageCnt) endPage = pageCnt;
				
				List<Ingredient> list = kd.IngtList2(startRow, endRow, keyWord);
				request.setAttribute("keyWord", keyWord);
				request.setAttribute("list", list);				
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("startNum", startNum);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISIngtList.jsp";
	}
}
