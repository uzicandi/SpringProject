package oracle.java.bmw.service;

import java.util.List;

import oracle.java.bmw.model.Point;
import oracle.java.bmw.model.Shopping;

public interface ShoppingService {
	int OrderInsert(Shopping shopping);
	int		subtractionPoint(Point point);
	List<Shopping> OrderedList(Shopping shopping);
	int PaymentCompleteCount(Shopping shopping);
	int DeliveryCompleCount(Shopping shopping);
}
