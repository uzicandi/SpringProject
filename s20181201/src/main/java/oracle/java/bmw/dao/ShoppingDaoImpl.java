package oracle.java.bmw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.Shopping;

@Repository
public class ShoppingDaoImpl implements ShoppingDao {

	@Autowired
	private SqlSession session;

	@Override
	public int OrderInsert(Shopping shopping) {
		return session.insert("OrderInsert", shopping);
	}

	@Override
	public List<Shopping> OrderedList(Shopping shopping) {
		return session.selectList("OrderedList", shopping);
	}

	@Override
	public int PaymentCompleteCount(Shopping shopping) {
		return session.selectOne("PaymentCompleteCount", shopping);
	}

	@Override
	public int DeliveryCompleCount(Shopping shopping) {
		return session.selectOne("DeliveryCompleCount", shopping);
	}
	
	
	
	
}
