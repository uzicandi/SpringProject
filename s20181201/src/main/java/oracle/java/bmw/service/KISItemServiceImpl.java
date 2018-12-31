package oracle.java.bmw.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.KISItemDao;
import oracle.java.bmw.model.Category;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.Ingredient;
import oracle.java.bmw.model.IngtList;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.SaveFiles;

@Service
public class KISItemServiceImpl implements KISItemService {

	@Autowired
	private KISItemDao kid;
	@Autowired
	private SaveFileService sfs;

	@Override
	public int KISItemTotal(ItemInfo item) {
		return kid.itemTotal(item);
	}

	@Override
	public List<ItemInfo> itemList(ItemInfo item) {
		List<ItemInfo> oldItemList = kid.itemList(item);
		List<ItemInfo> newItemList = new ArrayList<ItemInfo>();
		SaveFiles saveFile = new SaveFiles();
		for (ItemInfo iteminfo : oldItemList) {
			saveFile.setMainNo(iteminfo.getItemNo());
			saveFile.setSubNo(0);
			saveFile.setRef_Table("ITEMINFO");
			iteminfo.setSaveFileList(sfs.uploadFileRead(saveFile));
			newItemList.add(iteminfo);
		}
		return newItemList;
	}
	
	@Override
	public List<ItemInfo> itemBestList(ItemInfo item) {
		List<ItemInfo> oldItemList = kid.itemBestList(item);
		List<ItemInfo> newItemList = new ArrayList<ItemInfo>();
		SaveFiles saveFile = new SaveFiles();
		for (ItemInfo iteminfo : oldItemList) {
			saveFile.setMainNo(iteminfo.getItemNo());
			saveFile.setSubNo(0);
			saveFile.setRef_Table("ITEMINFO");
			iteminfo.setSaveFileList(sfs.uploadFileRead(saveFile));
			newItemList.add(iteminfo);
		}
		return newItemList;
	}

	// 제품 검색시 리스트
	@Override
	public List<ItemInfo> itemListForSearch(ItemInfo item) {
		List<ItemInfo> oldItemList = kid.itemListForSearch(item);
		List<ItemInfo> newItemList = new ArrayList<ItemInfo>();
		SaveFiles saveFile = new SaveFiles();
		for (ItemInfo iteminfo : oldItemList) {
			saveFile.setMainNo(iteminfo.getItemNo());
			saveFile.setSubNo(0);
			saveFile.setRef_Table("ITEMINFO");
			iteminfo.setSaveFileList(sfs.uploadFileRead(saveFile));
			newItemList.add(iteminfo);
		}
		return newItemList;
	}

	// 제품 검색시 리스트 갯수
	@Override
	public int KISItemSearchTotal(ItemInfo item) {
		return kid.itemSearchTotal(item);
	}

	@Override
	public ItemInfo content(int itemNo) {
		 ItemInfo item = kid.content(itemNo);

		// ********* File Read Start ***********//
		SaveFiles savefile = new SaveFiles();
		savefile.setMainNo(item.getItemNo());
		savefile.setSubNo(0);
		savefile.setRef_Table("ITEMINFO");
		item.setSaveFileList(sfs.uploadFileRead(savefile));
		// ********* File Read End ***********//

		return item;
	}

	@Override
	public int insert(ItemInfo item) {
		return kid.insert(item);
	}

	@Override
	public List<Category> cateList() {
		return kid.catelist();
	}

	@Override
	public List<ItemInfo> itemList() {
		return kid.itemList();
	}

	@Override
	public List<Category> select() {
		return kid.catelist();
	}

	@Override
	public List<Category> childSelect(Category cate) {
		return kid.childSelect(cate);
	}

	@Override
	public int update(ItemInfo item) {
		return kid.update(item);
	}

	@Override
	public int delete(int itemNo) {
		return kid.delete(itemNo);
	}

	@Override
	public int upHits(int itemNo) {
		return kid.upHits(itemNo);
	}

	@Override
	public List<Category> content() {
		return kid.content();
	}

	@Override
	public List<Ingredient> ingtList() {
		return kid.ingtList();
	}

	@Override
	public List<IngtList> mapplist(int itemNo) {
		return kid.mapplist(itemNo);
	}

	@Override
	public List<Ingredient> ingtList(int itemNo) {
		return kid.ingtList(itemNo);
	}

	@Override
	public int mappInsert(IngtList ingtList) {
		return kid.mappInsert(ingtList);
	}

	@Override
	public int selectItemNo() {
		return kid.selectItemNo();
	}

	@Override
	public int mappUpdate(IngtList ingtlist) {
		return kid.update(ingtlist);
	}

	@Override
	public void mappDelete(IngtList ingtlist) {
		kid.mappDelete(ingtlist);
	}

	@Override
	public ItemInfo cateSelected(int itemNo) {
		return kid.cateSelected(itemNo);
	}

	@Override
	public Category catelist2(int itemNo) {
		return kid.catelist2(itemNo);
	}

	@Override
	public void itemRaingUpdate(Comments comm) {
		kid.ratingUpdate(comm);
	}

	@Override
	public int lickCntUpdateAdd(int itemNo) {
		kid.lickCntUpdateAdd(itemNo);
		return kid.getLikeCnt(itemNo);
	}

	@Override
	public int lickCntUpdateSub(int itemNo) {
		kid.lickCntUpdateSub(itemNo);
		return kid.getLikeCnt(itemNo);
	}

	@Override
	public String foldingItem(int itemNo, HttpSession session) {
		ItemInfo item = content(itemNo);
		List<Ingredient> mapplist = kid.ingtList(itemNo);
		// System.out.println("TEST -> " +session.getAttribute("foldingList"));
		if (session.getAttribute("foldingList") == null) {
			System.out.println("세션 새로 등록!!");
			List<ItemInfo> itemList = new ArrayList<ItemInfo>();
			itemList.add(item);
			// 비교함에서 성분표시부분
			
			ArrayList<ArrayList<Ingredient>> compareIngtList = new ArrayList<ArrayList<Ingredient>>();
			compareIngtList.add((ArrayList<Ingredient>)mapplist);
			
			session.setAttribute("compareIngtList", compareIngtList);			
			session.setAttribute("foldingList", itemList);

			return "비교함 담기 완료";
		} else {
			System.out.println("기존 세션에 등록!!");
			List<ItemInfo> itemList = (List<ItemInfo>) session.getAttribute("foldingList");
			ArrayList<ArrayList<Ingredient>> compareIngtList = (ArrayList<ArrayList<Ingredient>>) session.getAttribute("compareIngtList");
			for (ItemInfo iteminfo : itemList) {
				if (iteminfo.getItemNo() == itemNo) {
					return "이미 담겨 있습니다";
				}
			}
			if (itemList.size() < 3) {
				itemList.add(item);
				compareIngtList.add((ArrayList<Ingredient>)mapplist);
				session.setAttribute("compareIngtList", compareIngtList);
				session.setAttribute("foldingList", itemList);

				return "비교함 담기 완료";
			} else {
				return "비교함이 가득 찾습니다(max : 3)";
			}
		}
	}

}
