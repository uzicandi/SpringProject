package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SDBCommentsDao;

public class SDBCommentsDeleteProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String ref_Table = request.getParameter("ref_Table");
			System.out.println("여긴 오냐");
			String pageNum = request.getParameter("pageNum");
			int mainNo = Integer.parseInt(request.getParameter("mainNo"));
			int subNo = Integer.parseInt(request.getParameter("subNo"));
			System.out.println("mainNo -> " + mainNo);
			System.out.println("subNo -> " + subNo);
			System.out.println("ref_t -> " + ref_Table);
			SDBCommentsDao cd = SDBCommentsDao.getInstance();
			int result = cd.delete(mainNo, subNo, ref_Table);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mainNo", mainNo);
			request.setAttribute("subNo", subNo);
			request.setAttribute("ref_Table", ref_Table);
			request.setAttribute("result", result);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "SDBCommentsDeletePro.jsp";
	}

}
