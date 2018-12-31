package oracle.java.bmw.dao;

import java.util.List;

import oracle.java.bmw.model.Shopping;

public interface ShoppingDao {
	int OrderInsert(Shopping shopping);
	List<Shopping> OrderedList(Shopping shopping);
	int PaymentCompleteCount(Shopping shopping);
	int DeliveryCompleCount(Shopping shopping);
}
