package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Category;
import dao.NYSCateDao;

public class NYSCateListAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NYSCateDao yd = NYSCateDao.getInstance();
		
		try {
			System.out.println("---ListAction----");
		
			String pageNumCt = request.getParameter("pageNumCt");
			if(pageNumCt == null || pageNumCt.equals("")) {
				pageNumCt = "1";
			}
			
			int totCnt = yd.getTotalCnt();

			int currentPage = Integer.parseInt(pageNumCt);
			int pageSize = 20, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = totCnt - startRow + 1;
			List<Category> list = yd.list(startRow, endRow);
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if(endPage > pageCnt) {
				endPage = pageCnt;
			}

			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNumCt", pageNumCt);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "NYSCateList.jsp";
	}

}
