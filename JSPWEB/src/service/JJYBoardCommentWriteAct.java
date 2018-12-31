package service;

import java.io.IOException;
/*import java.io.PrintWriter;*/
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import org.json.JSONObject;*/

/*import dao.Ingredient;*/
/*import dao.JJYIngtDao;*/
import dao.JJYCommentsDao;

public class JJYBoardCommentWriteAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		System.out.println("WriteAction 도착!!!");
		
		HttpSession session = request.getSession();
		
		// memberID는 session에서 가져오는걸로
		//String memberId = request.getParameter("memberId");
		String memberId = (String) session.getAttribute("sessionId");
		System.out.println("memberId -> " + memberId);
		
		String commentContent = request.getParameter("commentContent");
		System.out.println("commentContent -> " + commentContent);
		
		int mainNo = (int) session.getAttribute("boardNo");
		System.out.println("mainNo -> " + mainNo);
		
		session.setAttribute("isPublic", "1");
		String isPublic = (String) session.getAttribute("isPublic");
		System.out.println("isPublic -> " + isPublic);
		
		HashMap<String, Object> result = null;
		
		System.out.println("WriteAction 진행중.");
		
		JJYCommentsDao jd = JJYCommentsDao.getInstance();
		
		try {
			System.out.println("WriteAction 진행중..");
			result = jd.insertBoardComment(memberId, commentContent, mainNo, isPublic);
			System.out.println("WriteAction 진행중...");
			System.out.println("여기까지 띄워보자");
		} catch (Exception e) {
			System.out.println("ItemCommentWriteAct error -> " + e.getMessage());
		}
		
		request.setAttribute("ReadWrite", 0);
		request.setAttribute("result", result);
		
//		JSONObject jsonObj = new JSONObject(result);
//		PrintWriter pw = response.getWriter();
//		pw.println(jsonObj);
//		
//		System.out.println("result -> " + result);
//		System.out.println("JSONObj -> " + jsonObj);
		
		
		return "../comments/JJYSuccess.jsp";
	}

}
