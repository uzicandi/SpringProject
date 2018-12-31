package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KDWMemberDao;
import dao.Member;

public class KDWSearchPro implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("KDWSearchPro 33333... start...");
		String search2 = request.getParameter("search");
		String search = request.getParameter("search");
		String pageNum = request.getParameter("pageNum");
		System.out.println("KDWSearchPro 33333 search...->" +search);
		System.out.println("KDWSearchPro 33333 pageNum...->" +pageNum);

		KDWMemberDao bd = KDWMemberDao.getInstance();

		try {
			int index = 0;
			
			String find = request.getParameter("find");
			System.out.println("KDWSearchPro 33333 find...->" +find);

			int totCnt = bd.getTotalCnt2(search, index, find); // 27
			
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum); // 초기화시 페이지 넘버가 1
			int pageSize = 10, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1; // 초기화시 스타트로우도 1
			int endRow = startRow + pageSize - 1; // 초기화시 endRow는 10
			int startNum = totCnt - startRow + 1; // 초기화시 38

			System.out.println("KDWSearchPro find->" + find);

			if (find.equals("아이디")) {
				index = 0;
			} else if (find.equals("이름")) {
				index = 1;
			} else {
				index = 2;
			}

			search = search + "%";
			System.out.println("KDWSearchPro search->" + search);
			System.out.println("KDWSearchPro index->" + index);

			// 초기화시 1 10
			List<Member> list = bd.search3(search, index);
			// 38/10 , pageCnt는 page 갯수
			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			// 초기화시 1

			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			// 초기화시 10

			int endPage = startPage + blockSize - 1;
			// 초기화시 4
			if (endPage > pageCnt)
				endPage = pageCnt;

			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);

			request.setAttribute("find", find);
			request.setAttribute("search", search2);

			System.out.println("------------------------------------------------");
			System.out.println("startNum-->" + startNum);
			System.out.println("totCnt-->" + totCnt);
			System.out.println("currentPage-->" + currentPage);
			System.out.println("blockSize-->" + blockSize);
			System.out.println("pageSize-->" + pageSize);
			System.out.println("pageCnt-->" + pageCnt);
			System.out.println("startPage-->" + startPage);
			System.out.println("endPage-->" + endPage);
			System.out.println("search-->" + search2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "../member/KDWlist.jsp";
	}
}
