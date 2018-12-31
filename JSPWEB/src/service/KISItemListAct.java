package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Category;
import dao.ItemInfo;
import dao.JJYItemCategoryDao;

public class KISItemListAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JJYItemCategoryDao jd = JJYItemCategoryDao.getInstance();
		try {			
			HttpSession session = request.getSession();
			String memberId = (String)session.getAttribute("sessionId");
			String grade = (String)session.getAttribute("grade");
			
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			String cateNum = request.getParameter("cateNum");
			if (cateNum == null || cateNum.equals("")) {
				cateNum = "0";
			}
			
			int categoryNo = Integer.parseInt(cateNum);
			String sortName = request.getParameter("sortName");
			if (sortName == null || sortName.equals("")) {
				sortName = "0";
			}
			int sortNum = Integer.parseInt(sortName);
	System.out.println("Sort ->> " + sortNum);
			// 키워드
			String keyword = request.getParameter("keyword");
			System.out.println("keyword --> " + keyword);
			
			// 키워드 있을때 없을 때 구분해서 totCnt 가져옴
			if (keyword == null || keyword == "") {
				int totCnt = jd.getTotalCnt(categoryNo, grade);
				int currentPage = Integer.parseInt(pageNum);
				int pageSize = 10, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = startRow + pageSize - 1;
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				int endPage = startPage + blockSize -1;
				if (endPage > pageCnt) endPage = pageCnt;
				int startNum = (currentPage-1) * 10 + 1;

				List<ItemInfo> itemList = jd.itemCateList(categoryNo, sortNum, startRow, endRow, grade);
				
				request.setAttribute("memberId", memberId);				
				request.setAttribute("list", itemList);				
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("startNum", startNum);
				request.setAttribute("pageNum", pageNum);		
				request.setAttribute("categoryNo", categoryNo);
				
			} else {
				
				int totCnt = jd.getSearchTotalCnt(categoryNo, keyword, grade);
				int currentPage = Integer.parseInt(pageNum);
				int pageSize = 10, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = startRow + pageSize - 1;
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				int endPage = startPage + blockSize -1;
				if (endPage > pageCnt) endPage = pageCnt;
				int startNum = (currentPage-1) * 10 + 1;
				
				List<ItemInfo> itemList = jd.itemCateList2(categoryNo, sortNum, startRow, endRow, keyword, grade);

				request.setAttribute("list", itemList);				
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("startNum", startNum);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("keyword", keyword);
				request.setAttribute("categoryNo", categoryNo);
			}

			List<Category> cateAll = jd.cateAll();

			request.setAttribute("cateAll", cateAll);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "KISItemList.jsp";
	}

}