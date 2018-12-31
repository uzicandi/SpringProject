package oracle.java.bmw.controller;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import oracle.java.bmw.dao.UtilityDao;
import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.SaveFiles;
import oracle.java.bmw.service.ClipService;
import oracle.java.bmw.service.JJYItemReviewService;
import oracle.java.bmw.service.KISItemService;
import oracle.java.bmw.service.MemberService;
import oracle.java.bmw.service.SaveFileService;

@Controller
public class JJYItemReviewController {
	
	@Autowired
	private KISItemService kis;
	@Autowired
	private JJYItemReviewService jrs;
	@Autowired
	private SaveFileService sfs;
	@Autowired
	private UtilityDao ud;
	@Autowired
	private ClipService cls;
	@Autowired
	private MemberService ms;
	
	
	
	@RequestMapping(value="JJYItemReviewWriteForm")
	public String JJYItemReviewWriteForm(int itemNo, String currentPage, String currentPageRv, Model model) {
		ItemInfo item = kis.content(itemNo);
		
		model.addAttribute("item",item);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentPageRv", currentPageRv);
		
		return "item/JJYItemReviewWriteForm";
	}
	
	@RequestMapping(value="JJYItemReviewWritePro", method=RequestMethod.POST)
	public String JJYItemReviewWritePro(Comments comm, Model model, MultipartHttpServletRequest multipartRequest) {
		
		
		
		int subNo = ud.getMaxNoForTable("comments", "ITEMINFO", comm.getMainNo());
		System.out.println("subNo --> " + subNo);
		
		comm.setRef_Table("ITEMINFO");
		comm.setSubNo(subNo);
		System.out.println("Before--------------\n" + comm.toString());
		int result = jrs.commentWrite(comm);

		if(result > 0 ) {
			kis.itemRaingUpdate(comm);
			
			//********* File Upload Start ***********//
			String filePath = "/review";
			String formName = "saveFiles";
			
			SaveFiles saveFiles = new SaveFiles();
			saveFiles.setMainNo(comm.getMainNo());
			saveFiles.setSubNo(comm.getSubNo());
			saveFiles.setRef_Table("REVIEW");
			// 서버용
			result = sfs.uploadFileWithServer(saveFiles, filePath, formName, multipartRequest);
			// 로컬용
//			result = sfs.uploadFileWithLocal(saveFiles, formName, multipartRequest);
			//********* File Upload End ***********//		
			
			return "redirect:KISItemContent.do?itemNo="+comm.getMainNo()+"&currentPage="+comm.getCurrentPage()+"&currentPageRv="+comm.getCurrentPageRv();
		} else {
			model.addAttribute("msg","입력실패... 확인 필요...");
			return "forward:JJYItemReviewWriteForm?itemNo="+comm.getMainNo()+"&currentPage="+comm.getCurrentPage()+"&currentPageRv="+comm.getCurrentPageRv();
		}
	}
	
//	@RequestMapping(value="JJYItemReviewContent")
	@RequestMapping(value="JJYItemReviewContent", method={RequestMethod.POST, RequestMethod.GET}, produces="application/text;charset=utf-8")
//	@ResponseBody
	public String JJYItemReviewContent(int itemNo, int reviewNo, String currentPage, String currentPageRv, Model model) {
		
		System.out.println("JJYItemReviewContent itemNo : " + itemNo + " // reviewNo : " + reviewNo);
		Comments comm = new Comments();
		comm.setMainNo(itemNo);
		comm.setSubNo(reviewNo);
		comm.setRef_Table("ITEMINFO");
		
		comm = jrs.commentSelect(comm);
		System.out.println("memberId(nickName) : " + comm.getMemberId());
		Member member = ms.nickNameToMemberId(comm.getMemberId());
		String age = Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(member.getBirth().substring(0,4)));
		member.setAge(age);
//		System.out.println("age -> " + age);
		
		//********* File Read Start ***********//
		SaveFiles savefile = new SaveFiles();
		savefile.setMainNo(comm.getMainNo());
		savefile.setSubNo(comm.getSubNo());
		savefile.setRef_Table("REVIEW");
		List<SaveFiles> savefileList = sfs.uploadFileRead(savefile);
		
		model.addAttribute("saveFileList", savefileList);
		
		//********* File Read End ***********//
		
		model.addAttribute("comm", comm);
		model.addAttribute("member", member);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentPageRv", currentPageRv);
		
		return "item/JJYItemReviewContentModal";
//		return "item/JJYItemReviewContent";
	}
	
	@RequestMapping(value="JJYItemReviewUpdateForm")
	public String JJYItemReviewUpdateForm(int itemNo, int reviewNo, String currentPage, String currentPageRv, Model model) {
		
		Comments comm = new Comments();
		comm.setMainNo(itemNo);
		comm.setSubNo(reviewNo);
		comm.setRef_Table("ITEMINFO");
		
		comm = jrs.commentSelect(comm);
		
		//********* File Read Start ***********//
		SaveFiles savefile = new SaveFiles();
		savefile.setMainNo(comm.getMainNo());
		savefile.setSubNo(comm.getSubNo());
		savefile.setRef_Table("REVIEW");
		List<SaveFiles> savefileList = sfs.uploadFileRead(savefile);
		
		model.addAttribute("saveFileList", savefileList);
		//********* File Read End ***********//
		
		comm.setCurrentPage(currentPage);
		comm.setCurrentPageRv(currentPageRv);
		
		model.addAttribute("comm", comm);
		
		return "item/JJYItemReviewUpdateForm";
	}
	
	@RequestMapping(value="JJYItemReviewUpdatePro", method=RequestMethod.POST)
	public String JJYItemReviewUpdatePro(Comments comm, Model model, MultipartHttpServletRequest multipartRequest) {
		
		int result = jrs.commentUpdate(comm);
		
		model.addAttribute("itemNo",comm.getMainNo());
		model.addAttribute("reviewNo",comm.getSubNo());
		model.addAttribute("currentPage", comm.getCurrentPage());
		model.addAttribute("currentPageRv",comm.getCurrentPageRv());
		
		
		if(result > 0) {				
			//********* File Upload Start ***********//
			String filePath = "/review";
			String formName = "saveFiles";
			
			SaveFiles saveFiles = new SaveFiles();
			saveFiles.setMainNo(comm.getMainNo());
			saveFiles.setSubNo(comm.getSubNo());
			saveFiles.setRef_Table("REVIEW");
			// 서버용
			result = sfs.uploadFileWithServer(saveFiles, filePath, formName, multipartRequest);
			// 로컬용
//			result = sfs.uploadFileWithLocal(saveFiles, formName, multipartRequest);
			//********* File Upload End ***********//		
			
			return "redirect:JJYItemReviewContent.do";
		} else {
			model.addAttribute("msg", "수정실패.. 확인 필요..");
			return "forward:JJYItemReviewUpdateForm.do";
		}
	}
	
	@RequestMapping(value="JJYItemReviewDeletePro.do")
	public String JJYItemReviewDeletePro(int itemNo, int reviewNo, Model model) {
		Comments comm = new Comments();
		comm.setMainNo(itemNo);
		comm.setSubNo(reviewNo);
		comm.setRef_Table("ITEMINFO");
		
		//********* File Delete Start ***********//	
		SaveFiles savefile = new SaveFiles();
		savefile.setMainNo(comm.getMainNo());
		savefile.setSubNo(comm.getSubNo());
		savefile.setRef_Table("REVIEW");
		
		String filePath = "/review";
		int result = sfs.uploadFileDeleteAll(savefile, filePath);
		//********* File Delete End ***********//	
		
		result = jrs.reviewDelete(comm);
		
		
		
		model.addAttribute("itemNo",comm.getMainNo());
		model.addAttribute("reviewNo",comm.getSubNo());
		model.addAttribute("currentPage", comm.getCurrentPage());
		model.addAttribute("currentPageRv",comm.getCurrentPageRv());
		
		if(result > 0) {
			return "redirect:KISItemContent.do";
		} else {
			model.addAttribute("msg", "삭제에 실패...");
			return "forward:JJYItemReviewContent.do";
		}
	}
		
	@RequestMapping(value="ItemReviewLikeUpdate", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public HashMap<String, Object> ItemReviewLikeUpdate(Comments comm, Model model) {
		System.out.println("----------ItemReviewLikeUpdate---------------\n" + comm.toString());
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Clip oldClip = new Clip();
		oldClip.setMainNo(comm.getMainNo());
		oldClip.setSubNo(comm.getSubNo());
		oldClip.setMemberId(comm.getMemberId());
		oldClip.setRef_Table("LIKEREVIEW");
		System.out.println("oldClip....." + oldClip.toString());
		Clip newClip = cls.likeCheck(oldClip);
		
		int likeCnt = 0;
		if(newClip == null) {
			cls.insertClip(oldClip);
			likeCnt = jrs.reviewLikeCntUpdateAdd(comm);
			hm.put("msg", "좋아요!!");
		} else {
			System.out.println("newClip....." + newClip.toString());
			cls.deleteClip(oldClip);
			likeCnt = jrs.reviewLikeCntUpdateSub(comm);
			hm.put("msg", "좋아요!를 취소하였습니다.");
		}
		hm.put("likeCnt", likeCnt);
		return hm;
	}
}
