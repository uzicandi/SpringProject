package oracle.java.bmw.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import oracle.java.bmw.dao.UtilityDao;
import oracle.java.bmw.model.Cart;
import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Coupon;
import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.Point;
import oracle.java.bmw.model.SaveFiles;
import oracle.java.bmw.service.Paging;
import oracle.java.bmw.service.SaveFileService;
import oracle.java.bmw.service.UtilityServiceImpl;
import oracle.java.bmw.service.ClipService;
import oracle.java.bmw.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService ms;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SaveFileService sfs;
	@Autowired
	private UtilityDao ud;
	@Autowired
	private UtilityServiceImpl us;
	@Autowired
	private ClipService cs;
	
	@Resource(name = "uploadServerPath")
	private String uploadServerPath;
	
	@RequestMapping(value="basicForm")
	public String basicForm(Model model) {
		System.out.println("basicForm() ....");
		return "forward:home.do";
	}
	
	// ------------------- 원규 --------------------
	// 약관동의창으로 이동
	@RequestMapping(value="memberJoinAgreeForm")
	public String memberJoinAgreeForm(Model model) {
		return "/member/memberJoinAgreeForm";
	}
	
	// 회원가입창으로 이동
	@RequestMapping(value="memberJoinForm")
	public String memberJoinForm(Model model) {
		return "/member/memberJoinForm";
	}
	
	@RequestMapping(value="memberInsert", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberInsert(Member member, HttpServletRequest request, Model model) {
		member.setAddress(request.getParameter("addr1")+request.getParameter("addr2")+request.getParameter("addr3"));
		int result = ms.memberInsert(member);
		if(result == 1) {
			System.out.println("회원가입 성공!");
			model.addAttribute("tomail", member.getEmail());
			model.addAttribute("id", member.getMemberId());
			return "redirect:memberMailTest.do";
		} else {
			System.out.println("회원가입 실패!");
		}
		return "redirect:home.do";     	
	}
	
	// mailSending 코드
	  @RequestMapping(value="memberMailTest")
	  public String memberMailTest(HttpServletRequest request, Model model) throws Exception {
	   
	    String setfrom = "wkhong93@gmail.com";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = "BMW 회원가입 인증 메일입니다.";      // 제목
	    
	    String msg = "";
		String path = "http://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>"
		 + "<h3 style='color: blue;'>"
		 + request.getParameter("id") + "님 회원가입을 환영합니다.</h3>"
		 + "<div style='font-size: 130%'>"
		 + "하단의 인증 버튼 클릭 시 정상적으로 회원가입이 완료됩니다.</div><br/>"
		 + "<form method='post' action='"+path+"memberMailTestResult.do'>"
		 + "<input type='hidden' name='memberId' value='" + request.getParameter("id") + "'>"
		 + "<input type='hidden' name='email' value='" + tomail + "'>"
		 + "<input type='hidden' name='path' value='" + path + "'>"
		 + "<input type='submit' value='인증'></form><br/></div>";
		
	    try {
	      MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	 
	      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	      messageHelper.setTo(tomail);     // 받는사람 이메일
	      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	      messageHelper.setText(msg, true);  // 메일 내용
	     
	      mailSender.send(message);
		  System.out.println(msg);
	      System.out.println("보냄! ");
	    } catch(Exception e){
	    	System.out.println("메일발송 실패 : "+e);
	    }
	    return "redirect:home.do";
	  }
	
	@RequestMapping(value="memberMailTestResult", method= {RequestMethod.POST, RequestMethod.GET})
	public void mailSuccess(Member member, HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (ms.memberMailTestResult(member) == 0) { // 이메일 인증에 실패하였을 경우
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		} else { // 이메일 인증을 성공하였을 경우
			out.println("<script>");
			out.println("alert('인증이 완료되었습니다. 로그인 후 이용하세요.');");
			out.println("location.replace('"+request.getParameter("path")+"home.do');");
			out.println("</script>");
			out.close();
		}
	}
	
	// 아이디 중복체크
	@RequestMapping(value="memberIdCheck")
	 	@ResponseBody
	    public Map<Object, Object> memberIdCheck(@RequestBody String userid) {
	        Map<Object, Object> map = new HashMap<Object, Object>();
	        int count = ms.memberIdCheck(userid);
	        map.put("cnt", count);
	        return map;
	    }
	
	// 닉네임 중복체크
	@RequestMapping(value="memberNickCheck")
 	@ResponseBody
    public Map<Object, Object> memberNickCheck(@RequestBody String nick) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        int count = ms.memberNickCheck(nick);
        map.put("cnt", count);
        return map;
    }
	 
	 @RequestMapping(value="memberMyPageForm")
	 public String memberMyPageForm(HttpSession session, Model model, Member member) {
		 String id = (String) session.getAttribute("sessionId");
		 model.addAttribute("member", ms.mypageForm((String)session.getAttribute("sessionId")));

		 
		 return "/member/memberMyPageForm";
	 }
	 
	 @RequestMapping(value="memberPictureUpload")
	 public String memberPicture(Model model, HttpSession session, MultipartHttpServletRequest multipartRequest) {
		String id = (String) session.getAttribute("sessionId");

		String filePath = "/member";
		String formName = "saveFiles";
		SaveFiles saveFiles = new SaveFiles();
		saveFiles.setMainNo(ms.selectSeq(id));
		saveFiles = sfs.memberPictureRead(saveFiles);

		String path = uploadServerPath + "/member/";
		File file = new File(path + saveFiles.getSavedFileName());
		System.out.println(file);
		if(file.exists() == true) file.delete();
		// 서버용
		int result = sfs.uploadFileWithServerOfMember(saveFiles, filePath, formName, multipartRequest);
		// 로컬용
		// result = sfs.uploadFileWithLocal(saveFiles, formName, multipartRequest);
		// ********* File Upload End ***********//
		
		saveFiles = sfs.memberPictureRead(saveFiles);
		us.pathFix(saveFiles.getFilePath(), session);
		
		
		model.addAttribute("result", result);
		return "forward:memberMyPageForm.do";
	 }
	 
	 @RequestMapping(value="memberPictureDelete")
		public String memberPictureDelete(Model model, HttpSession session) {
			String id = (String) session.getAttribute("sessionId");
			
			SaveFiles saveFiles = new SaveFiles();
			saveFiles.setMainNo(ms.selectSeq(id));
			saveFiles = sfs.memberPictureRead(saveFiles);

			String path = uploadServerPath + "/member/";
			File file = new File(path + saveFiles.getSavedFileName());
			System.out.println(file);
			if(file.exists() == true) {
				System.out.println("삭제 파일의 이름-> " + file);
				file.delete();
				System.out.println("파일 삭제 성공");
			}
			int result = sfs.mempicDelete(ms.selectSeq(id));
			
			saveFiles = sfs.memberPictureRead(saveFiles);
			us.pathFix(saveFiles.getFilePath(), session);
			
			model.addAttribute("result", result);
			return "forward:memberMyPageForm.do";
		}
	 
	// 회원탈퇴창으로 이동
	@RequestMapping(value="memberWithdrawalForm")
	public String memberWithdrawalForm(Model model) {
		
		return "/member/memberWithdrawalForm";
	}
	
	// 회원탈퇴
	@RequestMapping(value="memberWithdrawalPro")
	public String memberWithdrawalPro(Member member, String id, String passwd, HttpSession session, Model model) {

		member.setMemberId(id);
		member.setPasswd(passwd);
		
		String dbid = ms.memberInfo(member).getMemberId();
		String dbpasswd = ms.memberInfo(member).getPasswd();
		if(dbid.equals(id) && dbpasswd.equals(passwd)) {
			int result = ms.memberWithdrawal(dbid);
			
			model.addAttribute("result", result);
			session.invalidate();
			return "forward:LoginView.do";
		} else {
			model.addAttribute("result", "0");
			return "forward:memberWithdrawalForm.do";
		}
	}

	 @RequestMapping(value="pointHistoryForm")
	 public String pointHistoryForm(Point point, HttpSession session, String currentPage, Model model) {
		String id= (String)session.getAttribute("sessionId");
		// 해쉬맵 세션  나중에 써야할때 쓰자
		// HashMap<String, String> ssm = (HashMap<String, String>)session.getAttribute("ssMap");
		
		int total = ms.pointTotal(id);
		System.out.println("total->"+total+", currentPage->"+currentPage);
		
		Paging pg = new Paging(total, currentPage);
		point.setMemberId(id);
		point.setStart(pg.getStart());
		point.setEnd(pg.getEnd());
		List<Point> list = ms.pointList(point);
		
		model.addAttribute("list", list);
		model.addAttribute("pg", pg);
		model.addAttribute("rpoint", lastPoint(id));
		
		return "/member/pointHistoryForm";
	 }
	 
	 public int lastPoint(String memberId) {
		 int rpoint = ms.lastPoint(memberId);
		 return rpoint;
	 }
	 
	 @RequestMapping(value="pointCharge")
	 public String pointCharge(HttpServletRequest request, HttpSession session, Model model) {
		 Point point = new Point();
		 point.setPrice(Integer.parseInt(request.getParameter("money").trim()));
		 point.setMemberId((String)session.getAttribute("sessionId"));
		 int result = ms.pointCharge(point);
		 if(result == 1) System.out.println("충전완료!");
		 else System.out.println("충전실패!");
		 return "forward:memberMyPageForm.do";
	 }
	 
	 @RequestMapping(value="clipListForm")
	 public String clipListForm(Clip clip, HttpServletRequest request, HttpSession session, String currentPage, Model model) {
		 String view = request.getParameter("view");
		 String delview = request.getParameter("delview");

		 String id = us.returnSession("nickname", session);
		 
		 int total = 0;
		 if(view.equals("1")) {
			 total = ms.reviewClipTotal(id);
			 System.out.println("total->"+total+", currentPage->"+currentPage);
			 Paging pg = new Paging(total, currentPage);
			clip.setMemberId(id);
			clip.setStart(pg.getStart());
			clip.setEnd(pg.getEnd());
			List<Clip> list = ms.reviewClipList(clip);
				
			model.addAttribute("totCnt", total);
			model.addAttribute("view", view);
			model.addAttribute("delview", delview);
			model.addAttribute("list", list);
			model.addAttribute("pg", pg);
		 } 
		 if(view.equals("2")) {
			 total = ms.boardClipTotal(id);
			 System.out.println("total->"+total+", currentPage->"+currentPage);
			 Paging pg = new Paging(total, currentPage);
				clip.setMemberId(id);
				clip.setStart(pg.getStart());
				clip.setEnd(pg.getEnd());
				List<Clip> list = ms.boardClipList(clip);
					
				model.addAttribute("totCnt", total);
				model.addAttribute("view", view);
				model.addAttribute("delview", delview);
				model.addAttribute("list", list);
				model.addAttribute("pg", pg);		
		 }
		 return "/member/clipListForm";
	 }
	 
	@RequestMapping(value="clipDelete", method= {RequestMethod.POST, RequestMethod.GET})
	public String clipDelete(Clip clip, HttpServletRequest request, HttpSession session, Model model) {
		String id = us.returnSession("nickname", session);
		String delview = request.getParameter("delview");
		String[] main = request.getParameterValues("mainNo");
		for(int i=0; i<main.length; i++) {
			System.out.println("main["+i+"]= "+main[i]);
		}
		
		int result = 0;
		clip.setMemberId(id);
		if (main != null) {
			if (delview.equals("1")) {
				String[] sub = request.getParameterValues("subNo");
				for (int i = 0; i < main.length; i++) {
					clip.setMainNo(Integer.parseInt(main[i]));
					clip.setSubNo(Integer.parseInt(sub[i]));
					result = ms.reviewClipDelete(clip);
				}
			} else {
				for (int i = 0; i < main.length; i++) {
					clip.setMainNo(Integer.parseInt(main[i]));
					result = ms.boardClipDelete(clip);
				}
				model.addAttribute("view", 2);
			}
			model.addAttribute("result", result);
			model.addAttribute("view", request.getParameter("view"));
			model.addAttribute("delview", delview);
		}

		return "redirect:memberMyPageForm.do";
	}
	

	 
	// ------------------- 동욱 --------------------
	 @RequestMapping(value="memberToListAdmin") //회원리스트
		public String memberToListAdmin(Member member,String keyword, String search, String currentPage, Model model) {
			member.setKeyword(keyword);
			member.setSearch(search);
			int total = ms.memberListTotal(member);
			System.out.println("total=>" + total);
			System.out.println("currentPage=>" + currentPage);
		
			Paging pg = new Paging(total, currentPage); 
			member.setStart(pg.getStart());
			member.setEnd(pg.getEnd()); 
			 
			List<Member> list = ms.memberToListAdmin(member);
			System.out.println("list.size()=>" + list.size());
			System.out.println("total=>" + total);
			System.out.println("currentPage=>" + currentPage);
			model.addAttribute("list",  list);
			model.addAttribute("pg",  pg);
			model.addAttribute("keyword",  keyword);
			model.addAttribute("search",  search);
			
			return "member/memberToListAdmin"; 
		}
		   
		@RequestMapping(value="memberToDetailAdmin") //회원리스트의 상세정보
		public String memberToDetailAdmin(String memberId, Model model) {
			System.out.println("memberId - > " + memberId);
			Member member = ms.memberToDetailAdmin(memberId);	
			model.addAttribute("member", member);
			System.out.println("memberId!!!!!!!!!!! -> " + member.getMemberId());
			
			return "member/memberToDetailAdmin"; 
		} 
		   
		@RequestMapping(value="memberToUpdateAdminForm") //회원정보 화면
		public String memberToUpdateAdminForm(String memberId, String grade, Model model) {
			System.out.println("KDWupdateForm start...");
			System.out.println("KDWupdateForm memberId->"+memberId);
			System.out.println("KDWupdateForm grade->"+grade);
			Member member = ms.memberToDetailAdmin(memberId); 
			model.addAttribute("member", member);
			
			return "member/memberToUpdateAdminForm";
		}
		   
	
		@RequestMapping(value="memberToUpdateAdmin", method=RequestMethod.POST) //회원정보 수정
		public String memberToUpdateAdmin(Member member,String birth, Model model) {
			System.out.println("member.getMemberId()->" +member.getMemberId() );
			System.out.println("member.getBirth()->" +member.getBirth() );
			System.out.println("member-------------->" +member);
			member.setBirth(member.getBirth().replace("-", ""));
			int k = ms.memberToUpdateAdmin(member);
			model.addAttribute("member",member);
			System.out.println("member##########>" +member);
			
			return "forward:memberToListAdmin.do";
		}
	
	// ------------------- 지우 --------------------
		// 관리자 페이지 form
		 @RequestMapping(value="AdminForm")
		 public String AdminForm(HttpSession session, Model model, Member member) {
			 String id = (String) session.getAttribute("sessionId");
			 model.addAttribute("member", ms.mypageForm((String)session.getAttribute("sessionId")));
			 return "/member/AdminForm";
		 }
		
		// 01. 로그인 화면
		@RequestMapping(value = "LoginView")
		public String login() {
			return "member/LoginView"; // views/member/KJWlogin.jsp 로 포워드
		}

		// 02. 로그인 처리
				@RequestMapping(value = "LoginCheck")
				public ModelAndView LoginCheck(@ModelAttribute Member member, HttpSession session) {
					boolean result = ms.loginCheck(member, session);
					ModelAndView mav = new ModelAndView();
					if (result == true) { // 로그인 성공
						// main.jsp 로 이동
						
						//********* File Read Start ***********//
						SaveFiles savefile = new SaveFiles();
						savefile.setMainNo(ms.selectSeq(member.getMemberId()));
						savefile = sfs.memberPictureRead(savefile);
						//System.out.println("filePath -> "+savefile.getFilePath());
						//********* File Read End ***********//
						
						
						// 그냥 세션 저장방식
						session.setAttribute("sessionId", member.getMemberId());

						member = ms.memberGetInfo((String) session.getAttribute("sessionId"));
						String age = Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(member.getBirth().substring(0,4)));
						// 해쉬맵 세션 저장방식
						HashMap<String, String> sessionHashMap = new HashMap<String, String>();
						sessionHashMap.put("memberId", member.getMemberId());
						sessionHashMap.put("passwd", member.getPasswd());
						sessionHashMap.put("nickname", member.getNickname());
						sessionHashMap.put("name", member.getName());
						sessionHashMap.put("birth", member.getBirth());
						sessionHashMap.put("sex", member.getSex());
						sessionHashMap.put("skinType", member.getSkinType());
						sessionHashMap.put("skinComplex", member.getSkinComplex());
						sessionHashMap.put("grade", member.getGrade());
						sessionHashMap.put("email", member.getEmail());
						sessionHashMap.put("isMail", member.getIsEmail());
						sessionHashMap.put("phone", member.getPhone());
						sessionHashMap.put("address", member.getAddress());
						sessionHashMap.put("question", member.getQuestion());
						sessionHashMap.put("answer", member.getAnswer());
						sessionHashMap.put("recommender", member.getRecommender());
						sessionHashMap.put("joinDate", member.getJoinDate());
						sessionHashMap.put("regDate", member.getRegDate());
						sessionHashMap.put("filePath", savefile.getFilePath());
						sessionHashMap.put("age", age);
						session.setAttribute("ssMap", sessionHashMap);
				
						
						
						HashMap<Object, Object> cartMap = new HashMap<Object, Object>();
						List<Cart> list = cs.listCart(member.getMemberId()); // 장바구니 정보
						cartMap.put("list", list); // 장바구니 정보를 map에 저장
						cartMap.put("count", list.size()); // 장바구니 상품의 개수
						session.setAttribute("cartMap", cartMap);

						mav.setViewName("../../index");// view 이름 설정
						mav.addObject("memberId", member.getMemberId());
						mav.addObject("msg", "success"); // 데이터를보냄 ("변수이름", "데이터값")
					} else { // 로그인 실패
						// login.jsp로 이동
						mav.setViewName("member/LoginView");
						mav.addObject("msg", "failure");
					}
					return mav;
				}

		// 03. 로그아웃 처리
		@RequestMapping(value = "Logout")
		public ModelAndView logout(HttpSession session) {
			ms.logout(session);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/LoginView");
			mav.addObject("msg", "logout");
			return mav;
		}

		// 04. 회원정보수정 화면
		@RequestMapping(value = "UpdateForm")
		public String updateForm(HttpSession session, Model model, Member member) {
			System.out.println("UpdateForm");
			// save "member" to model
			model.addAttribute("member", ms.updateForm((String)session.getAttribute("sessionId")));
			// logger.info("클릭한 아이디 : " + memberId);
			// forward "view"
			return "member/UpdateForm";
		}
		//05. 회원정보수정
		@RequestMapping(value = "UpdateMember")
		public String updateMember(@ModelAttribute Member member, Model model) {
			ms.updateMember(member);
			model.addAttribute("msg", "updateOK");
			return "member/memberMyPageForm";
		}
		// 01. 아이디 찾기 페이지 불러오기
		@RequestMapping(value="FindIdView")
		public String findId() throws Exception{
			return "member/FindIdView";
		} 
		// 02. 아이디찾기 입력정보 확인, id불러오기
		@RequestMapping(value="FindIdCheck", method=RequestMethod.POST)
		public ModelAndView KJWFindIdCheck(@ModelAttribute Member member, HttpSession session, Model model) {
			System.out.println("FindIdCheck 1...");
			String memberId = null;
			memberId=ms.findIdCheck(member, session);
			/*boolean result = fs.findIdCheck(member,session);*/
			System.out.println("FindIdCheck 2...");
			ModelAndView mav = new ModelAndView();
			model.addAttribute("member", member);
			model.addAttribute("memberId",memberId);
			System.out.println("memberId->"+memberId);
			if(memberId != null) { // 이메일,질문,답변 일치
				//findIdOK.jsp로 이동
				mav.setViewName("member/FindIdView");	//view 이름 설정
				mav.addObject("msg","success");	//데이터를보냄 ("변수이름", "데이터값")
			} else { //정보일치X
				// alert로 틀렸다고 창 오픈
				mav.setViewName("member/FindIdView");
				mav.addObject("msg","failure");
			}
			return mav;
		
		}
		
		// 03. 비밀번호 찾기
		@RequestMapping(value="FindPwView")
		public String findPw() throws Exception{
			return "member/FindPwView";
		}
		
		//04. 비밀번호 답변 정보 확인 
		@RequestMapping(value="FindPwCheck", method=RequestMethod.POST)
		public String KJWFindPwCheck(Model model, Member member) {
			System.out.println("FindPwCheck 1...");
			boolean result = ms.findPwCheck(member);
			System.out.println("FindPwCheck 2...");
			System.out.println("memberId->"+member.getMemberId());
			System.out.println("Email->"+member.getEmail());
			System.out.println("Question->"+member.getQuestion());
			System.out.println("Answer->"+member.getAnswer());
			System.out.println("result->"+result);
			if(result == true) { //아이디, 이메일, 질문, 답변 일치
//				model.addAttribute("msg","success");
				return "forward:mailTransport.do";
			} else {
				model.addAttribute("msg","failure");
				return "member/FindPwView";
			} 
		}
			//답변이 맞았는지 틀렸는지 확인하는 코드 필요
		
		
		
			// 05. 비밀번호 메일로 보내주기 (비밀번호 맞으면)
			// mailTransport 코드
			@RequestMapping(value = "mailTransport")
			public String mailTransport(HttpServletRequest request, Model model, Member member) {
				System.out.println("mailSending");
				String tomail = request.getParameter("email");              // 받는 사람 이메일
				String memberId = request.getParameter("memberId");          // 받는사람 아이디
				System.out.println(tomail);
				String setfrom = "uzicandi@gmail.com";             // 보내는 사람 이메일
				String title = "mailTransport 입니다";                 // 제목
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
					messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
					messageHelper.setTo(tomail);      // 받는사람 이메일
					messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
					String tempPassword = (int) (Math.random() * 999999) + 1 + "";
					messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
					System.out.println("임시 비밀번호입니다 : " + tempPassword);
					//DataSource dataSource =   new FileDataSource("c:\\log\\Jellyfish.jpg");
//				    messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), 
//				    		dataSource);
				    mailSender.send(message);
//					model.addAttribute("check", 1);   // 정상 전달
				    model.addAttribute("msg","success");
					member.setMemberId(memberId);
					member.setPasswd(tempPassword);
					System.out.println("memberid -> " + memberId);
					System.out.println("tempPassword -> " + tempPassword);
					int kkk = ms.update_pw(member);// db에 비밀번호를 임시비밀번호로 업데이트
					System.out.println("update_pw after kkk->"+kkk);
				} catch (Exception e) {
					System.out.println(e);
//					model.addAttribute("check", 2);  // 메일 전달 실패
					model.addAttribute("msg","mailFailure");
				}
//				return "mailResult";	// findPw.jsp
				return "member/FindPwView";
			
		}
	 
}



