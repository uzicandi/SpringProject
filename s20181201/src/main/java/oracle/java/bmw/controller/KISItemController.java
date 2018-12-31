package oracle.java.bmw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import oracle.java.bmw.model.Category;
import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.Ingredient;
import oracle.java.bmw.model.IngtList;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.SaveFiles;
import oracle.java.bmw.service.CategoryService;
import oracle.java.bmw.service.ClipService;
import oracle.java.bmw.service.JJYItemReviewService;
import oracle.java.bmw.service.KISItemService;
import oracle.java.bmw.service.Paging;
import oracle.java.bmw.service.SaveFileService;

@Controller
public class KISItemController {

	@Autowired
	private KISItemService kis;
	@Autowired
	private JJYItemReviewService jrs;
	@Autowired
	private SaveFileService sfs;
	@Autowired
	private ClipService cls;
	@Autowired
	private CategoryService cs;

	@RequestMapping(value = "KISItemList")
	public String KISItemList(String keyword, String search, ItemInfo item, Category cate, String cateSel, String currentPage,
			Model model) {
		System.out.println("카테고리 넘버 ---->>>> " + cateSel);
		if(cateSel == null || cateSel.equals("")) {
			cateSel = "0";
		}
		item.setCateNum(Integer.parseInt(cateSel));
		Category cateP = cs.detail(Integer.parseInt(cateSel));
		model.addAttribute("cateP", cateP);
		model.addAttribute("cateSel", cateSel);
		
		List<Category> catelist = kis.cateList();
		List<ItemInfo> bestlist = kis.itemBestList(item);
		
		model.addAttribute("bestlist", bestlist);
		model.addAttribute("catelist", catelist);
		if (keyword == null) {
			// 초기실행시 가져오는 제품리스트 (keyword 없을때)
			item.setCateNum(Integer.parseInt(cateSel));
			int total = kis.KISItemTotal(item);
			System.out.println("total -> " + total);
			Paging pg = new Paging(total, currentPage, 9);
			item.setStart(pg.getStart());
			item.setEnd(pg.getEnd());
			List<ItemInfo> list = kis.itemList(item);

			model.addAttribute("list", list);
			model.addAttribute("pg", pg);
			model.addAttribute("itemTotal", total);
			return "/item/KISItemList";
		} else {
			// 검색어가 있을때 가져올 제품리스트
			item.setCateNum(Integer.parseInt(cateSel));
			System.out.println("키워드 입력 --> " + item.getKeyword());
			item.setKeyword(keyword);
			item.setSearch(search);
			System.out.println("search 값 --> " + search);
			int searchTotal = kis.KISItemSearchTotal(item);

			System.out.println("searchTotal -> " + searchTotal);
			Paging pg = new Paging(searchTotal, currentPage);
			item.setStart(pg.getStart());
			item.setEnd(pg.getEnd());
			List<ItemInfo> list = kis.itemListForSearch(item);

			model.addAttribute("itemTotal", searchTotal);
			model.addAttribute("pg", pg);
			model.addAttribute("list", list);
			return "/item/KISItemList";
		}

	}


	@RequestMapping(value = "KISPopIngtListForItemView", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/text;charset=utf-8")
	public String KISPopIngtListForItemView(int itemNo, Ingredient ingt, Model model) {
		System.out.println("itemNo -> " + itemNo);
		List<Ingredient> ingtlist = kis.ingtList(itemNo);

		model.addAttribute("ingtlist", ingtlist);

		return "/item/KISPopIngtListForItemView";
	}

	@RequestMapping(value = "KISItemContent")
	public String KISItemContent(int itemNo, String currentPage, String currentPageRv, Model model) {
		kis.upHits(itemNo); // 조회수
		ItemInfo item = kis.content(itemNo);

		// Review
		Comments comm = new Comments();
		comm.setMainNo(itemNo);
		comm.setRef_Table("ITEMINFO");
		int reviewTotal = jrs.JJYItemReviewTotal(comm);
		System.out.println("Reviewtotal -> " + reviewTotal);

		Paging pgRv = new Paging(reviewTotal, currentPageRv);
		comm.setStart(pgRv.getStart());
		comm.setEnd(pgRv.getEnd());

		List<Comments> comms = jrs.commentList(comm);
		List<Ingredient> mapplist = kis.ingtList(itemNo);
		List<Category> catelist = kis.content();
		
		for(Comments comm22 : comms) {
			System.out.println("원규야 잘봐!! --> " + comm22.toString());
		}

//		List<Integer> ratingCnt = new ArrayList<Integer>();
//		for(Comments comm1 : comms) {
//			ratingCnt.add(comm1.getRating(), element);
//		}
		model.addAttribute("catelist", catelist);
		model.addAttribute("mapplist", mapplist);
		model.addAttribute("item", item);
		model.addAttribute("comms", comms);
		model.addAttribute("pgRv", pgRv);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("reviewTotal", reviewTotal);

		return "/item/KISItemContent";
	}

	@RequestMapping(value = "KISItemWriteForm")
	public String KISItemWriteForm(Model model) {
		List<ItemInfo> list = kis.itemList();
		List<Category> catelist = kis.select();

		model.addAttribute("cate", list);
		model.addAttribute("catelist", catelist);
		return "/item/KISItemWriteForm";
	}
	
	

	@RequestMapping(value = "KISItemWrite", method = RequestMethod.POST)
	public String KISItemWrite(HttpServletRequest request, ItemInfo item, IngtList ingtList, Model model,
			MultipartHttpServletRequest multipartRequest) {
		int itemNo = kis.selectItemNo(); // itemNo select
		kis.insert(item);
		System.out.println("itemNo --> " + itemNo);
		item.setItemNo(itemNo);

		// ********* File Upload Start ***********//
		String filePath = "/item";
		String formName = "saveFiles";

		SaveFiles saveFiles = new SaveFiles();
		saveFiles.setMainNo(itemNo);
		saveFiles.setSubNo(0);
		saveFiles.setRef_Table("ITEMINFO");
		// 서버용
		int result = sfs.uploadFileWithServer(saveFiles, filePath, formName, multipartRequest);
		// 로컬용
		// result = sfs.uploadFileWithLocal(saveFiles, formName, multipartRequest);
		// ********* File Upload End ***********//

		// IngtList 테이블에 성분번호 배열값으로 받아서 저장
		String[] receiveValue = request.getParameter("receiveValue").split(",");
		System.out.println("receiveValue.length --> " + receiveValue.length);
		if (receiveValue.length > 1) {
			ingtList.setItemNo(itemNo);
			for (int i = 0; i < receiveValue.length; i++) {
				int ingredientNo = Integer.parseInt(receiveValue[i]);
				ingtList.setIngredientNo(ingredientNo);
				kis.mappInsert(ingtList);
			}
		}
		return "redirect:KISItemList.do";
	}

	@RequestMapping(value = "KISItemCateSel", method = RequestMethod.POST)
	public String KISItemCateSel(Category cate, Model model) {
		List<Category> list = kis.childSelect(cate);
		return "redirect:KISItemCateSel.do";
	}

	@RequestMapping(value = "KISItemUpdateForm")
	public String KISItemUpdate(int itemNo, Model model) {
		ItemInfo item = kis.content(itemNo);
		System.out.println("categoryNo --> " + item.getCategoryNo());
		List<Category> catelist = kis.cateList(); // 전체 카테고리 뿌려주는 용도
		List<Ingredient> mapplist = kis.ingtList(itemNo);

		Category cateNoSelect = kis.catelist2(itemNo); // 제품 수정시 디비에 저장된 카테고리 selected 시키는 용도
		System.out.println("cate에 담긴" + cateNoSelect.getCategoryNo());

		// ********* File Read Start ***********//
		SaveFiles savefile = new SaveFiles();
		savefile.setMainNo(item.getItemNo());
		savefile.setSubNo(0);
		savefile.setRef_Table("ITEMINFO");
		// List<SaveFiles> savefileList = sfs.uploadFileRead(savefile);
		item.setSaveFileList(sfs.uploadFileRead(savefile));

		// model.addAttribute("saveFileList", savefileList);
		// ********* File Read End ***********//

		model.addAttribute("cateNoSelect", cateNoSelect);
		model.addAttribute("item", item);
		model.addAttribute("catelist", catelist);
		model.addAttribute("mapplist", mapplist);
		return "/item/KISItemUpdateForm";
	}

	@RequestMapping(value = "KISItemUpdate", method = RequestMethod.POST)
	public String KISItemUpdate(HttpServletRequest request, ItemInfo item, IngtList ingtlist, Model model,
			MultipartHttpServletRequest multipartRequest) {
		System.out.println("KISItemUpdate() Start......");
		int k = kis.update(item);

		// ********* File Upload Start ***********//
		String filePath = "/item";
		String formName = "saveFiles";

		SaveFiles saveFiles = new SaveFiles();
		saveFiles.setMainNo(item.getItemNo());
		saveFiles.setSubNo(0);
		saveFiles.setRef_Table("ITEMINFO");
		// 서버용
		int result = sfs.uploadFileWithServer(saveFiles, filePath, formName, multipartRequest);
		// 로컬용
		// result = sfs.uploadFileWithLocal(saveFiles, formName, multipartRequest);
		// ********* File Upload End ***********//

		// IngtList 테이블에 성분번호 배열값으로 받아서 저장
		if (k > 0) {
			String[] receiveValue = request.getParameter("receiveValue").split(",");
			System.out.println("receiveValue.length --> " + receiveValue.length);
			System.out.println("receiveValue->" + receiveValue);

			if (receiveValue.length > 1) {
				ingtlist.setItemNo(item.getItemNo());
				kis.mappDelete(ingtlist);
				for (int i = 0; i < receiveValue.length; i++) {
					int ingredientNo = Integer.parseInt(receiveValue[i]);
					ingtlist.setIngredientNo(ingredientNo);
					kis.mappUpdate(ingtlist);
				}
			} else {
				return "redirect:KISItemContent.do";
			}
		}
		return "redirect:KISItemContent.do";
	}

	@RequestMapping(value = "KISItemDelete")
	public String KISItemDelete(int itemNo, Model model) {

		// 1. 아이템-성분 Mapping 지우기
		IngtList ingtlist = new IngtList();
		ingtlist.setItemNo(itemNo);
		kis.mappDelete(ingtlist);

		// 2. comments 지우기 & Review 지우기
		Comments comm = new Comments();
		comm.setMainNo(itemNo);
		comm.setSubNo(0);
		comm.setRef_Table("ITEMINFO");
		comm.setRef(itemNo);
		int result = jrs.reviewDeleteAll(comm);

		// ********* File Delete Start ***********//
		SaveFiles savefile = new SaveFiles();
		savefile.setMainNo(comm.getMainNo());
		savefile.setRef_Table("REVIEW");
		List<SaveFiles> saveFileList = sfs.uploadFileListAllwithItem(savefile);

		String filePath = "/review";
		for (SaveFiles savefile1 : saveFileList) {
			result = sfs.uploadFileDeleteAll(savefile1, filePath);
		}
		// ********* File Delete End ***********//

		// 3. item 지우기
		kis.delete(itemNo);

		// 4. 사진 제거
		// ********* File Delete Start ***********//
		savefile = new SaveFiles();
		savefile.setMainNo(itemNo);
		savefile.setSubNo(0);
		savefile.setRef_Table("ITEMINFO");

		filePath = "/item";
		result = sfs.uploadFileDeleteAll(savefile, filePath);
		// ********* File Delete End ***********//

		return "redirect:KISItemList.do";
	}

	@RequestMapping(value = "ItemLikeUpdate", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=utf-8")
	@ResponseBody
	public HashMap<String, Object> ItemLikeUpdate(ItemInfo item, Model model) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Clip oldClip = new Clip();
		oldClip.setMainNo(item.getItemNo());
		oldClip.setMemberId(item.getMemberId());
		oldClip.setSubNo(0);
		oldClip.setRef_Table("LIKEITEM");
		System.out.println("oldClip....." + oldClip.toString());
		Clip newClip = cls.likeCheck(oldClip);

		int likeCnt = 0;
		if (newClip == null) {
			cls.insertClip(oldClip);
			likeCnt = kis.lickCntUpdateAdd(item.getItemNo());
			hm.put("msg", "좋아요!!");
		} else {
			System.out.println("newClip....." + newClip.toString());
			cls.deleteClip(oldClip);
			likeCnt = kis.lickCntUpdateSub(item.getItemNo());
			hm.put("msg", "좋아요!를 취소하였습니다.");
		}
		hm.put("likeCnt", likeCnt);
		return hm;
	}

	@RequestMapping(value = "FoldingItem", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/text;charset=utf-8")
	@ResponseBody
	public String FoldingItem(int itemNo, HttpSession session, Model model) {
		System.out.println("itemNo -> " + itemNo);

		return kis.foldingItem(itemNo, session);
	}
}
