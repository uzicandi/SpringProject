package oracle.java.bmw.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oracle.java.bmw.model.Cart;
import oracle.java.bmw.model.Point;
import oracle.java.bmw.model.Shopping;
import oracle.java.bmw.service.ClipService;
import oracle.java.bmw.service.MemberService;
import oracle.java.bmw.service.ShoppingService;
import oracle.java.bmw.service.UtilityServiceImpl;

@Controller
public class ShoppingController {
	
	@Autowired
	private ShoppingService ss;
	@Autowired
	private ClipService cs;
	@Autowired
	private MemberService ms;
	@Autowired
	private UtilityServiceImpl us;
	
	@RequestMapping(value="OrderSheet")
	public String OrderSheet(Shopping sp, Model model, HttpSession session) {
		System.out.println("welcome ordersheet");
		//String memberId = returnSession("memberId", session);
		String memberId = (String) session.getAttribute("sessionId");
		System.out.println("배송페이지 멤버아이디 " + memberId);
		int sumMoney = cs.sumMoney(memberId);
		List<Cart> list = cs.listCart(memberId);
		
		HashMap<String, String> hm = (HashMap<String, String>) session.getAttribute("ssMap");
		
		System.out.println("세션 전화번호 -> " + hm.get("phone"));
		if(hm.get("phone") != null) {
			String[] phone = hm.get("phone").split("-");
			for(int i = 0; i < 3; i++) {
				model.addAttribute("phone"+i, phone[i]);
			}
		}
		
		int curPoint = ms.lastPoint(memberId);			//현재 포인트
		model.addAttribute("curPoint", curPoint);
		model.addAttribute("list", list);				//장바구니 리스트
		model.addAttribute("sumMoney", sumMoney);		//결제금액 총액
		model.addAttribute("count", list.size());		//장바구니 사이즈
		return "shopping/OrderSheet";
	}
	
	@RequestMapping(value="OrderPayment")
	public String OrderPayment(Shopping sp, Model model, HttpSession session, HttpServletRequest request) {
		String memberId = (String) session.getAttribute("sessionId");
		sp.setRecvTel(request.getParameter("phone0")+"-"+request.getParameter("phone1")+"-"+request.getParameter("phone2"));
		System.out.println("수령인 번호 -> " + sp.getRecvTel());
		System.out.println("수령인 -> " + sp.getReceiver());
		System.out.println("메모 -> " + sp.getMemo());
		System.out.println("총액 -> " + request.getParameter("sumMoney"));
		System.out.println("보유 포인트 -> " + request.getParameter("curPoint"));
		String address = request.getParameter("addr2") + request.getParameter("addr3");
		sp.setAddress(address);
		System.out.println("받는 주소 -> " + address);
		List<Cart> list = cs.listCart(memberId);
	
		Cart cart = new Cart();
		
		for(int i = 0; i < list.size(); i++) {
			//shopping 테이블에 insert
			/*System.out.println("아이템번호 -> " + list.get(i).getMainNo());
			System.out.println("수량 -> " + list.get(i).getSubNo());
			System.out.println("돈 -> " + list.get(i).getMoney());*/
			sp.setMemberId(memberId);
			sp.setItemNo(Integer.toString(list.get(i).getMainNo()));
			sp.setAmount(list.get(i).getSubNo());
			/*System.out.println("메머아이디 -> " + sp.getMemberId());
			System.out.println("아번 -> " + sp.getItemNo());
			System.out.println("수량 -> " + sp.getAmount());
			*/
			System.out.println(sp.toString());
			ss.OrderInsert(sp);
			
			//clip 테이블에 delete (장바구니 삭제)
			cart.setMainNo(list.get(i).getMainNo());
			cart.setMemberId(memberId);
			cs.deleteCart(cart);
		}
		// 구매 완료하고 세션에 장바구니정보 수정
		list = cs.listCart(memberId);
		us.cartFix(list, session);
		Point point = new Point();
		point.setMemberId(memberId);
		point.setPrice(Integer.parseInt(request.getParameter("sumMoney")));
		System.out.println("포인트 멤버아이디 -> " + point.getMemberId());
		System.out.println("포인트 지출액 -> " + point.getPrice());
		int result = ss.subtractionPoint(point);
		System.out.println("포인트 차감 -> " + result);
		
		int delivery = ss.DeliveryCompleCount(sp);
		int complete = ss.PaymentCompleteCount(sp);
		
		model.addAttribute("delivery", delivery);
		model.addAttribute("complete", complete);
		int curPoint = ms.lastPoint(memberId);			//현재 포인트
		model.addAttribute("curPoint", curPoint);
		List<Shopping> Shoppinglist = ss.OrderedList(sp);
		model.addAttribute("list", Shoppinglist);

		
		return "shopping/OrderList";
	}
	
	@RequestMapping(value="OrderList")
	public String OrderList(Shopping sp, Model model, HttpSession session, HttpServletRequest request) {
		
		String memberId = (String) session.getAttribute("sessionId");
		sp.setMemberId(memberId);
		List<Shopping> Shoppinglist = ss.OrderedList(sp);
		int delivery = ss.DeliveryCompleCount(sp);
		int complete = ss.PaymentCompleteCount(sp);
		
		model.addAttribute("delivery", delivery);
		model.addAttribute("complete", complete);
		int curPoint = ms.lastPoint(memberId);			//현재 포인트
		model.addAttribute("curPoint", curPoint);
		model.addAttribute("list", Shoppinglist);
		return "shopping/OrderList";
	}
	

}
