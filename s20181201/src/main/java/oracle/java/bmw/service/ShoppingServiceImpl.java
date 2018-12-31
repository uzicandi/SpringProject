package oracle.java.bmw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.PointDao;
import oracle.java.bmw.dao.ShoppingDao;
import oracle.java.bmw.model.Point;
import oracle.java.bmw.model.Shopping;

@Service
public class ShoppingServiceImpl implements ShoppingService {

	@Autowired
	private ShoppingDao sd;
	@Autowired
	private PointDao pd;

	@Override
	public int OrderInsert(Shopping shopping) {
		return sd.OrderInsert(shopping);
	}

	@Override
	public int subtractionPoint(Point point) {
		return pd.subtractionPoint(point);
	}

	@Override
	public List<Shopping> OrderedList(Shopping shopping) {
		return sd.OrderedList(shopping);
	}

	@Override
	public int PaymentCompleteCount(Shopping shopping) {
		return sd.PaymentCompleteCount(shopping);
	}

	@Override
	public int DeliveryCompleCount(Shopping shopping) {
		return sd.DeliveryCompleCount(shopping);
	}
	
	
	
}
