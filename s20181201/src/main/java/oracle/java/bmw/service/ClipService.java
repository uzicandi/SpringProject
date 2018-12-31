package oracle.java.bmw.service;

import java.util.List;

import oracle.java.bmw.model.Cart;
import oracle.java.bmw.model.Clip;


public interface ClipService {

	void insertCart(Cart cart);

	List<Cart> listCart(String memberId);

	void deleteCart(Cart cart);

	void modifyCart(Cart cart);

	int sumMoney(String memberId);

	int countCart(int mainNo, String memberId);

	void updateCart(Cart cart);

	Clip likeCheck(Clip clip);

	void insertClip(Clip oldClip);

	void deleteClip(Clip oldClip);

}
