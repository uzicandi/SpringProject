package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemInfo;
import dao.SDBItemDao;

public class SDBBrandListAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SDBItemDao sd = SDBItemDao.getInstance();
		try {
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			int totCnt = sd.getTotalCnt();
			System.out.println("brandlist totcnt -> " + totCnt);
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 100, blockSize = 10;
			List<ItemInfo> list = sd.listBrand();
			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt)
				endPage = pageCnt;

			request.setAttribute("end", totCnt-1);

			request.setAttribute("list", list);
		} catch (Exception e) {
			System.out.println("브랜드 리스트 액트 익셉션");
			System.out.println(e.getMessage());
		}
		return "SDBBrandList.jsp";
	}

}
