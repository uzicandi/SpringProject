package oracle.java.bmw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.Category;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.Ingredient;
import oracle.java.bmw.model.IngtList;
import oracle.java.bmw.model.ItemInfo;

@Repository
public class KISItemDaoImpl implements KISItemDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<ItemInfo> itemList(ItemInfo item) {
		return session.selectList("KISItemList", item);						
	}

	@Override
	public int itemTotal(ItemInfo item) {
		return session.selectOne("KISItemTotal", item);
	}
	// 키워드 있을때 제품리스트
	@Override
	public List<ItemInfo> itemListForSearch(ItemInfo item) {
		return session.selectList("KISItemSearchList", item);
	}
	// 키워드 있을때 제품리스트 갯수
	@Override
	public int itemSearchTotal(ItemInfo item) {
		return session.selectOne("KISItemSearchTotal", item);	
	}

	@Override
	public ItemInfo content(int itemNo) {
		return session.selectOne("KISItemContent", itemNo);
	}

	@Override
	public int insert(ItemInfo item) {
		return session.insert("KISItemWrite", item);
	}

	@Override
	public List<Category> catelist() {
		return session.selectList("KISCateList");
	}

	@Override
	public List<ItemInfo> itemList() {
		return session.selectList("KISCateList");
	}

	@Override
	public List<Category> childSelect(Category cate) {
		return session.selectList("childCate", cate);
	}

	@Override
	public int delete(int itemNo) {
		return session.delete("KISItemDelete", itemNo);
	}

	@Override
	public int upHits(int itemNo) {
		return session.update("KISItemHits", itemNo);		
	}

	@Override
	public List<Category> content() {
		return session.selectList("KISCateList");
	}

	@Override
	public List<Ingredient> ingtList() {
		return session.selectList("KISIngtList");
	}

	@Override
	public int update(ItemInfo item) {
		return session.update("KISItemUpdate", item);
	}

	@Override
	public List<IngtList> mapplist(int itemNo) {
		return session.selectList("KISMappingList", itemNo);
	}

	@Override
	public List<Ingredient> ingtList(int itemNo) {
		return session.selectList("KISIngtName", itemNo);
	}	

	@Override
	public int selectItemNo() {
		return session.selectOne("KISItemNoSelect");
	}

	@Override
	public int mappInsert(IngtList ingtList) {
		return session.insert("KISMappingInsert", ingtList);
	}

	@Override
	public int update(IngtList ingtlist) {
		return session.insert("KISMappingInsert", ingtlist);
	}

	@Override
	public void mappDelete(IngtList ingtlist) {
		session.delete("KISMappingDelete", ingtlist);		
	}

	@Override
	public ItemInfo cateSelected(int itemNo) {
		return session.selectOne("KISLoadCate", itemNo);
	}

	@Override
	public Category catelist2(int itemNo) {
		return session.selectOne("KISLoadCate", itemNo);
	}

	@Override
	public void ratingUpdate(Comments comm) {
		session.update("ratingUpdate", comm);
	}

	@Override
	public int lickCntUpdateAdd(int itemNo) {
		return session.update("lickCntUpdateAdd", itemNo);
	}

	@Override
	public int lickCntUpdateSub(int itemNo) {
		return session.update("lickCntUpdateSub", itemNo);
	}

	@Override
	public int getLikeCnt(int itemNo) {
		return session.selectOne("getLikeCnt", itemNo);
	}

	@Override
	public List<ItemInfo> itemBestList(ItemInfo item) {
		return session.selectList("itemBestList", item);		
	}





}
