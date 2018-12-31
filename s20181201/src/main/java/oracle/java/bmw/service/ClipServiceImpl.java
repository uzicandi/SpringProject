package oracle.java.bmw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.ClipDao;
import oracle.java.bmw.model.Cart;
import oracle.java.bmw.model.Clip;

@Service
public class ClipServiceImpl implements ClipService {
	@Autowired
	private ClipDao cd;
	
	// 1. 장바구니 추가 
	@Override
	public void insertCart(Cart cart) {
		System.out.println("insertCart ClipServiceImpl");
		cd.insertCart(cart);
	}
	// 2. 장바구니 목록
	@Override
	public List<Cart> listCart(String memberId) {
		System.out.println("listCart ClipServiceImpl");
		return cd.listCart(memberId);
	}
	// 3. 장바구니 삭제
	@Override
	public void deleteCart(Cart cart) {
		System.out.println("ServiceImpl deleteCart");
		cd.deleteCart(cart);
		
	}
	// 4. 장바구니 수정
	@Override
	public void modifyCart(Cart cart) {
		cd.modifyCart(cart);
		
	}
	// 5. 장바구니 금액 합계
	@Override
	public int sumMoney(String memberId) {
		return cd.sumMoney(memberId);
	}
	// 6. 장바구니 상품 확인
	@Override
	public int countCart(int mainNo, String memberId) {
		System.out.println("clipServiceImpl countCart");
		return cd.countCart(mainNo, memberId);
	}
	// 7. 장바구니 상품수량 변경
	@Override
	public void updateCart(Cart cart) {
		System.out.println("clipServiceImpl updateCart");
		cd.updateCart(cart);
		
	}
	@Override
	public Clip likeCheck(Clip clip) {
		return cd.likeCheck(clip);
	}
	@Override
	public void insertClip(Clip oldClip) {
		cd.insertClip(oldClip);
	}
	@Override
	public void deleteClip(Clip oldClip) {
		cd.deleteClip(oldClip);
	}
	

}
