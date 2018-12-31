package oracle.java.bmw.dao;

import java.util.List;

import oracle.java.bmw.model.Ingredient;

public interface KISIngtDao {
	List<Ingredient> ingtList(Ingredient ingt);
	int total();
	Ingredient content(int ingredientNo);
	int delete(int ingredientNo);
	int update(Ingredient ingt);
	int insert(Ingredient ingt);
	void insert();
//	List<Ingredient> ingtList(String keyword);
	List<Ingredient> ingtListForSearch(Ingredient ingt);
	int ingtSearchTotal(Ingredient ingt);

}
