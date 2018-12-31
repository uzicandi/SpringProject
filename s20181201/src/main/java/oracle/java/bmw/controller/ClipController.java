package oracle.java.bmw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.Cart;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Shopping;
import oracle.java.bmw.service.BoardService;
import oracle.java.bmw.service.ClipService;
import oracle.java.bmw.service.JJYItemReviewService;
import oracle.java.bmw.service.UtilityServiceImpl;


@Controller
public class ClipController {
	
	@Autowired
	private ClipService cs;
	@Autowired
	private UtilityServiceImpl us;
	@Autowired
	private BoardService bs;
	@Autowired
	private JJYItemReviewService jrs;
		
	// modifyCart() = 장바구니 화면에서 상품수량을 변경할 때
	// updateCart() = 상품목록 화면에서 동일한 상품을 장바구니에 담았을 때 
	
	// 1. 장바구니 추가
	@RequestMapping(value="CartInsert")
	public String insertCart(ItemInfo item, Cart cart, int amount, HttpSession session) {
//		public String insertCart(@ModelAttribute ItemInfo item, Cart cart, int amount, HttpSession session) {
		String memberId = us.returnSession("memberId", session);
//		String memberId = "aa";
		cart.setMemberId(memberId);
		System.out.println("set memberId->"+cart.getMemberId());
		cart.setMainNo(item.getItemNo());
		System.out.println("setMainNo->"+cart.getMainNo());
		cart.setTitle(item.getName());
		System.out.println("setName->"+cart.getTitle());
		cart.setSubNo(amount);
		System.out.println("amount->"+cart.getSubNo());
		cart.setPrice(item.getPrice());
		System.out.println("amount->"+cart.getPrice());
		// 장바구니에 기존 상품이 있는지 검사		
		int count = cs.countCart(cart.getMainNo(), memberId);
		// count == 0 ? cs.updateCart(clip) : cs.insertCart(clip);
		System.out.println("Controller cart.getMainNo()->"+cart.getMainNo());
		System.out.println("Controller memberId->"+memberId);
		System.out.println("Controller count->"+ count);
		if (count == 0) {
			System.out.println("없으면 insert...");
			// 없으면 insert
			cs.insertCart(cart);		
		} else {
			System.out.println("있으면 update...");
			// 있으면 update
			cs.updateCart(cart);
		}
		
		// 추가하고 세션에 장바구니정보 수정
		List<Cart> list = cs.listCart(memberId);
		us.cartFix(list, session);
		
		return "redirect:CartList.do";
	}	
	// 2. 장바구니 목록
	@RequestMapping(value="CartList")
	public ModelAndView CartList(Cart cart, ModelAndView mav, HttpSession session){
		System.out.println("CartList Start...");
		String memberId = us.returnSession("memberId", session);
//		String memberId = clip.getMemberId();	//clip에 저장된 memberId
		System.out.println("CartList memberId->"+memberId);
		Map<String, Object> map = new HashMap<String,Object>();
		System.out.println("CartList listCart Before..");
		List<Cart> list = cs.listCart(memberId);	//장바구니 정보
		System.out.println("CartList listCart list.size()->"+list.size());
		int sumMoney = cs.sumMoney(memberId);	//장바구니 전체금액 호출
		System.out.println("CartList sumMoney ->"+sumMoney);
		map.put("list", list);	//장바구니 정보를 map에 저장
		map.put("count", list.size());	//장바구니 상품의 유무
		map.put("sumMoney", sumMoney);	//장바구니 전체 금액
		mav.setViewName("clip/CartList");
		mav.addObject("map",map);
		return mav;
	}
	
	
	// 3. 장바구니 삭제
	@RequestMapping(value="CartDelete")
	public String deleteCart(@RequestParam int mainNo, HttpSession session, Cart cart) {
		String memberId = us.returnSession("memberId", session);
		cart.setMainNo(mainNo);
		cart.setMemberId(memberId);
		cs.deleteCart(cart);
		
		// 삭제하고 세션에 장바구니정보 수정
		List<Cart> list = cs.listCart(memberId);
		us.cartFix(list, session);
		
		return "redirect:CartList.do";
	}
	// 4. 장바구니 수정
	@RequestMapping(value="CartUpdate")
	public String updateCart(@RequestParam int[] subNo, @RequestParam int[] mainNo, HttpSession session) {
		// item의 Id
		String memberId = us.returnSession("memberId", session);
		// 레코드의 갯수만큼 반복문 실행
		for(int i=0; i<mainNo.length; i++) {
			Cart cart = new Cart();
			cart.setMemberId(memberId);
			cart.setSubNo(subNo[i]);
			cart.setMainNo(mainNo[i]);
			cs.modifyCart(cart);
		}
		
		// 수정하고 세션에 장바구니정보 수정
		List<Cart> list = cs.listCart(memberId);
		us.cartFix(list, session);
		
		return "redirect:CartList.do";
	}
	
	
	// -------------- 에이..동범 ------------------------------------------------------------------
	@RequestMapping(value="BoardContentClipInsert", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<Object, Object> BoardContentClipInsert(@RequestBody String boardNo, Model model, HttpSession session) {
		Board board = bs.BoardContent(Integer.parseInt(boardNo));
		board.setMemberId(us.returnSession("nickname", session));
		int result = 0;
		int chkClip = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		try {
			chkClip = bs.BoardContentClipChk(board);
			System.out.println("chkClip -> " + chkClip);
		} catch(Exception e) {
			chkClip = 0;
			result = bs.BoardContentClipInsert(board);
		}
		
		if(chkClip == 0)  map.put("chkClip", 0);
		else  map.put("chkClip", chkClip);
		
		map.put("insertResult", result);		
		System.out.println("BoardContentClipInsert result -> " + result);
		
		return map;
	}
	
	@RequestMapping(value="reviewContentClipInsert", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<Object, Object> reviewContentClipInsert(Comments com, HttpSession session, Model model) {
		com.setRef_Table("ITEMINFO");
		com = jrs.commentSelect(com);
		com.setMemberId(us.returnSession("nickname", session));
		int result = 0;
		int chkClip = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		try {
			chkClip = jrs.reviewContentClipChk(com);
			System.out.println("chkClip -> " + chkClip);
		} catch(Exception e) {
			chkClip = 0;
			result = jrs.reviewContentClipInsert(com);
		}
		
		if(chkClip == 0) map.put("chkClip", 0);
		else map.put("chkClip", chkClip);

		map.put("insertResult", result);		
		
		System.out.println("BoardContentClipInsert result -> " + result);
		
		return map;
	}

}
