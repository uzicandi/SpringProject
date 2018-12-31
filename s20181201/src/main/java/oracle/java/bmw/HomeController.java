package oracle.java.bmw;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oracle.java.bmw.HomeController;
import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.Category;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Ranking;
import oracle.java.bmw.model.SaveFiles;
import oracle.java.bmw.service.BoardService;
import oracle.java.bmw.service.CategoryService;
import oracle.java.bmw.service.KISItemService;
import oracle.java.bmw.service.RankingService;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService bs;
	@Autowired
	private RankingService rs;
	@Autowired
	private CategoryService cs;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		System.out.println("index 스타뚜");

		
		//실시간 판매랭킹 부분
		List<ItemInfo> SellRankList = rs.SellRankList();
		System.out.println("sellranklist.size -> " + SellRankList.size());
		for(int i = 0; i < SellRankList.size(); i++) {
			System.out.println("셀렝리 정보 -> " + SellRankList.get(i).getName());
			System.out.println("셀렝리 파일패스 -> " + SellRankList.get(i).getFilePath());
		}
		
		//많이 본 제품 리뷰 부분
		List<ItemInfo> HitsItemReviewRank = rs.HitsItemReviewRank();
		System.out.println("hitsitemreviewrank size -> " + HitsItemReviewRank.size());
		
		
		//Beauty Plus 부분
		Board board = new Board();
		List<Board> WebzineList = bs.WebzineList(board);
		SaveFiles sf = new SaveFiles();
		List<SaveFiles> WebzineSaveFilesList = bs.BoardSaveFilesList(sf);

		//유저들은 부분
		int InfoTotal = bs.BoardCategoryTotal("정보");
		if(InfoTotal > 5)
			InfoTotal = 5;
		System.out.println("infototal -> " + InfoTotal);
		List<Board> InfoList = bs.BoardCategoryList("정보");
		System.out.println("infotolist.size -> " + InfoList.size());
		
		// Header에 필요한 카테고리 session 등록
		List<Category> headerCateList = cs.headerCateView();
		session.setAttribute("headerCateList", headerCateList);
		
		
		
		model.addAttribute("SellRankList", SellRankList);
		
		model.addAttribute("HitsItemReviewRank", HitsItemReviewRank);
		
		model.addAttribute("WebzineList", WebzineList);
		model.addAttribute("WebzineSaveFilesList", WebzineSaveFilesList);
		
		model.addAttribute("InfoTotal", InfoTotal);
		model.addAttribute("InfoList", InfoList);
		
//		return "starter";
		return "home";
	}
}
