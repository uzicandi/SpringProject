package oracle.java.bmw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.KISIngtDao;
import oracle.java.bmw.model.Ingredient;

@Service
public class KISIngtServiceImpl implements KISIngtService {
	
	@Autowired
	private KISIngtDao kid;
	
	@Override
	public List<Ingredient> ingtList(Ingredient ingt) {
		return kid.ingtList(ingt);
	}

	@Override
	public int KISIngtTotal() {
		return kid.total();
	}

	@Override
	public Ingredient content(int ingredientNo) {
		Ingredient ingt = kid.content(ingredientNo);
		return kid.content(ingredientNo);
	}

	@Override
	public int update(Ingredient ingt) {
		return kid.update(ingt);
	}

	@Override
	public int delete(int ingredientNo) {
		return kid.delete(ingredientNo);
	}

	@Override
	public int insert(Ingredient ingt) {
		return kid.insert(ingt);
	}

	@Override
	public int KISIngtSearchTotal(Ingredient ingt) {
		return kid.ingtSearchTotal(ingt);
	}

	@Override
	public List<Ingredient> ingtListForSearch(Ingredient ingt) {
		return kid.ingtListForSearch(ingt);
	}
	

}
