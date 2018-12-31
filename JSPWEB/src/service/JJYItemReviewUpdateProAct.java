package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import dao.Comments;
import dao.FileManager;
import dao.FileProcess;
import dao.JJYItemReviewDao;
import service.CommandProcess;

public class JJYItemReviewUpdateProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
	System.out.println("리뷰 Update Pro 시작...");
	
			
			MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response);

			String pageNum = multi.getParameter("pageNum");
			String pageNumRv = multi.getParameter("pageNumRv");
			
	System.out.println("-------------TEST---------------------");
			String savefiles[] = new String[3];
			savefiles[0] = multi.getFilesystemName("saveFiles1");
	System.out.println("saveFiles1 --> " + savefiles[0]);
			if(savefiles[0] == null || savefiles[0] == "") {
				savefiles[0] = null;
			}
	System.out.println("saveFiles1 --> " + savefiles[0]);
			savefiles[1] = multi.getFilesystemName("saveFiles2");
	System.out.println("savefile2 --> " + savefiles[1]);
			if(savefiles[1] == null || savefiles[1] == "") {
				savefiles[1] = null;
			}
	System.out.println("savefile2 --> " + savefiles[1]);
			savefiles[2] = multi.getFilesystemName("saveFiles3");
			if(savefiles[2] == null || savefiles[2] == "") {
				savefiles[2] = null;
			}
			
			Comments comm = new Comments();

			comm.setMainNo(Integer.parseInt(multi.getParameter("mainNo")));
			comm.setSubNo(Integer.parseInt(multi.getParameter("subNo")));
			comm.setRef_Table(multi.getParameter("ref_Table"));
			comm.setRating(Integer.parseInt(multi.getParameter("rating")));
			comm.setContent(multi.getParameter("content"));
			comm.setContent2(multi.getParameter("content2"));
			comm.setContent3(multi.getParameter("content3"));
			comm.setIsPublic(multi.getParameter("isPublic"));
			
			JJYItemReviewDao jd = JJYItemReviewDao.getInstance();
//			int result = jd.updateComments(comm);
			String phsyicalPath = FileManager.getPygicalPath(request);
			int result = jd.updateReview(multi, comm, savefiles, phsyicalPath);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageNumRv", pageNumRv);
			request.setAttribute("result", result);
			request.setAttribute("comm", comm);
			
		} catch (Exception e) {
			System.out.println("ItemReviewUpdatePro error -> " + e.getMessage());
		}
		
		return "JJYItemReviewUpdatePro.jsp";
	}

}
