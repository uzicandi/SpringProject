package oracle.java.bmw.dao;

import java.util.List;

import oracle.java.bmw.model.Category;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.Ingredient;
import oracle.java.bmw.model.IngtList;
import oracle.java.bmw.model.ItemInfo;

public interface KISItemDao {
	List<ItemInfo> itemList(ItemInfo item);
	ItemInfo content(int itemNo);
	int insert(ItemInfo item);
	List<Category> catelist();
	List<ItemInfo> itemList();
	List<Category> childSelect(Category cate);
	int delete(int itemNo);
	int upHits(int itemNo);
//	String regDate();
	List<Category> content();
	List<Ingredient> ingtList();
	int update(ItemInfo item);
	int mappInsert(IngtList ingtList);
	List<IngtList> mapplist(int itemNo);
	List<Ingredient> ingtList(int itemNo);
	int selectItemNo();
	int update(IngtList ingtlist);
	void mappDelete(IngtList ingtlist);
	ItemInfo cateSelected(int itemNo);
	Category catelist2(int itemNo);
	List<ItemInfo> itemListForSearch(ItemInfo item);
	int itemSearchTotal(ItemInfo item);
	void ratingUpdate(Comments comm);
	int lickCntUpdateSub(int itemNo);
	int lickCntUpdateAdd(int itemNo);
	int getLikeCnt(int itemNo);
	List<ItemInfo> itemBestList(ItemInfo item);
	int itemTotal(ItemInfo item);
}
