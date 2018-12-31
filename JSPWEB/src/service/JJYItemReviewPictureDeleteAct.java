package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileManager;
import dao.JJYItemReviewDao;

public class JJYItemReviewPictureDeleteAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int mainNo = Integer.parseInt(request.getParameter("mainNo"));
			int subNo = Integer.parseInt(request.getParameter("subNo"));
			String ref_Table = request.getParameter("ref_Table");
			int filesNo = Integer.parseInt(request.getParameter("filesNo"));
			
			JJYItemReviewDao jd = JJYItemReviewDao.getInstance();
			String phsyicalPath = FileManager.getPygicalPath(request);
			String fileName = jd.deleteFile(mainNo, subNo, ref_Table, filesNo, phsyicalPath);
					
			request.setAttribute("fileName", fileName);
			
		} catch (Exception e) {
			System.out.println("JJYItemReviewPictureDeleteAct error -> " + e.getMessage());
		}

		return "JJYItemReviewPictureDeletePro.jsp";
	}

}
