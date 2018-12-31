package oracle.java.bmw.dao;

import java.util.List;

import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.Cart;

public interface ClipDao {
	
	int			reviewClipTotal(String memberId);
	List<Clip> 	reviewClipList(Clip clip);
	int			reviewClipDelete(Clip clip);
	int			boardClipTotal(String memberId);
	List<Clip> 	boardClipList(Clip clip);
	int			boardClipDelete(Clip clip);
	
	/*장바구니*/
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
	int reviewContentClipInsert(Comments com);
	int reviewContentClipChk(Comments com);
	int BoardContentClipInsert(Board board);
	int BoardContentClipChk(Board board);
	
}
