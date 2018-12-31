package oracle.java.bmw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.Cart;

@Repository
public class ClipDaoImpl implements ClipDao {

	@Autowired
	private SqlSession session;
		
	@Override
	public int reviewClipTotal(String memberId) {
		return session.selectOne("reviewClipTotal", memberId);
	}

	@Override
	public List<Clip> reviewClipList(Clip clip) {
		return session.selectList("reviewClipList", clip);
	}
	
	@Override
	public int reviewClipDelete(Clip clip) {
		return session.delete("reviewClipDelete", clip);
	}

	@Override
	public int boardClipTotal(String memberId) {
		return session.selectOne("boardClipTotal", memberId);
	}
	
	@Override
	public List<Clip> boardClipList(Clip clip) {
		return session.selectList("boardClipList", clip);
	}

	@Override
	public int boardClipDelete(Clip clip) {
		return session.delete("boardClipDelete", clip);
	}
	
	@Override
	public int BoardContentClipInsert(Board board) {
		return session.insert("BoardContentClipInsert", board);
	}

	@Override
	public int BoardContentClipChk(Board board) {
		return session.selectOne("BoardContentClipChk", board);
	}
	
	@Override
	public int reviewContentClipInsert(Comments com) {
		return session.insert("reviewContentClipInsert", com);
	}

	@Override
	public int reviewContentClipChk(Comments com) {
		return session.selectOne("reviewContentClipChk", com);
	}
	
	/*장바구니*/
	
	@Override
	public void insertCart(Cart cart) {
		System.out.println("insertCart ClipDaoImpl");
		System.out.println("set memberId->"+cart.getMemberId());
		System.out.println("setMainNo->"+cart.getMainNo());
		System.out.println("setName->"+cart.getTitle());
		System.out.println("amount->"+cart.getSubNo());
		session.insert("insertCart",cart);
		
	}

	@Override
	public List<Cart> listCart(String memberId) {
		System.out.println("listCart memberId->"+memberId);
		System.out.println("listCart ClipDaoImpl");
		return session.selectList("listCart",memberId);
	}

	@Override
	public void deleteCart(Cart cart) {
		System.out.println("DAOIMPL deleteCart");
		session.delete("deleteCart", cart);
		
	}

	@Override
	public void modifyCart(Cart cart) {
		session.update("modifyCart",cart);
		
	}
	//5. 장바구니 금액 합계
	@Override
	public int sumMoney(String memberId) {
		System.out.println("clipDaoImpl sumMoney memberId->"+memberId);
		int kkk = 0;
		try {
			kkk = session.selectOne("sumMoney",memberId);
		
		} catch (Exception e) {
			System.out.println("clipDaoImpl sumMoney Exception->"+e.getMessage());
		}
		return kkk;
	}
	//6. 장바구니 동일한 상품 레코드 확인
	@Override
	public int countCart(int mainNo, String memberId) {
		int kkk = 0;
		System.out.println("clipDaoImpl countCart mainNo->"+mainNo);
		System.out.println("clipDaoImpl countCart memberId->"+memberId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mainNo",mainNo);
		map.put("memberId", memberId);
		try {
			kkk = session.selectOne("countCart",map);
		
		} catch (Exception e) {
			System.out.println("clipDaoImpl countCart Exception->"+e.getMessage());
		}
		return kkk;
	}
	
	//7. 장바구니 상품수량 변경
	@Override
	public void updateCart(Cart cart) {
		System.out.println("clipDaoImpl updateCart");
		session.update("updateCart",cart);
		
	}

	@Override
	public Clip likeCheck(Clip clip) {
		return session.selectOne("likeCheck", clip);
	}

	@Override
	public void insertClip(Clip oldClip) {
		session.insert("insertClip", oldClip);
	}

	@Override
	public void deleteClip(Clip oldClip) {
		session.delete("deleteClip", oldClip);
	}

	
	
}
