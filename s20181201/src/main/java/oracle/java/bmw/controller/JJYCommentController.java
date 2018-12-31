package oracle.java.bmw.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oracle.java.bmw.model.Comments;
import oracle.java.bmw.service.JJYItemReviewService;

@Controller
public class JJYCommentController {

//	@Autowired 
//	JJYCommentService jcs;
	@Autowired
	JJYItemReviewService jrs;

	
	@RequestMapping(value="JJYCommentContent")
	public String JJYCommentContent(int mainNo, int subNo, int kinds, Model model, HttpSession session) {
		System.out.println("itemNo -> " + mainNo);
		System.out.println("reviewNo -> " + subNo);
		System.out.println("kinds -> " + kinds);
		
		Comments comm = new Comments();
		comm.setMainNo(mainNo);
		comm.setSubNo(subNo);
//		comm.setRef_Table("ITEMINFO");		// 필요없음!!!
//		comm = jrs.commentSelect(comm);
		
		if(kinds == 0) {
			comm.setRef_Table("REVIEW");		// Board에 적용할때 이부분만 바꾸면 됨!!
			model.addAttribute("ref_Table", "REVIEW");
		} else if (kinds == 1) {
			comm.setRef_Table("BOARD");
			model.addAttribute("ref_Table", "BOARD");
		}
		comm.setCommentCount(jrs.commentCount(comm));
		
		System.out.println("commentCount -> " + comm.getCommentCount());
		HashMap<String, String> hm = (HashMap<String, String>)session.getAttribute("ssMap");
		model.addAttribute("userId", hm.get("nickname"));
		model.addAttribute("comm", comm);
		
		return "item/JJYCommentContent";
	}

	@RequestMapping(value="JJYCommentRead", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public List<Comments> JJYCommentRead(Comments comm, Model model) {

		System.out.println("JJYCommentRead.......\n" + comm.toString());
		System.out.println("\n----\ncontent -> " + comm.getContent());
		List<Comments> comms = jrs.commentList(comm);
		
		return comms;
	}
	
	@RequestMapping(value="JJYCommentWrite", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public HashMap<String, Object> JJYCommentWrite(Comments comm, Model model) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		System.out.println("JJYCommentWrite......\n" + comm.toString());
		int result = jrs.commentWrite(comm);
		if(comm.getRef_Table().equals("REVIEW") && result == 1) {
			jrs.replyCntUpdate(comm);
		}
		System.out.println("CommentWrite insert End....");
		hm.put("result", result);
		if(result > 0) {
			comm.setCommPageNum(10);
			List<Comments> comms = jrs.commentList(comm);
			hm.put("comments", comms);
		}
		
		return hm;
	}	
	
	@RequestMapping(value="JJYCommentUpdateForm", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public HashMap<String, Object> JJYCommentUpdateForm(Comments comm, String trId, Model model) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		System.out.println("JJYCommentUpdateForm......\n" + comm.toString());
		comm = jrs.commentSelect(comm);
		System.out.println("JJYCommentUpdateForm insert End....\n" + comm.toString());
		
		hm.put("trId", trId);
		hm.put("comm", comm);
		
		return hm;
	}	
	
	@RequestMapping(value="JJYCommentUpdatePro", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public int JJYCommentUpdatePro(Comments comm, Model model) {

		System.out.println("JJYCommentUpdatePro.......\n" + comm.toString());
		System.out.println("\n----\ncontent -> " + comm.getContent());
		int result = jrs.commentUpdate(comm);
		
		return result;
	}
	
	@RequestMapping(value="JJYCommentDelete", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public int JJYCommentDelete(Comments comm, Model model) {

		System.out.println("JJYCommentDelete.......\n" + comm.toString());
		System.out.println("\n----\ncontent -> " + comm.getContent());
		int result = jrs.commentDelete(comm);
		if(comm.getRef_Table().equals("REVIEW") && result == 1) {
			jrs.replyCntUpdateSub(comm);
		}
		
		return result;
	}
}
