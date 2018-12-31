package oracle.java.bmw.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import oracle.java.bmw.model.Category;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Ranking;
import oracle.java.bmw.service.CategoryService;
import oracle.java.bmw.service.RankingService;

@Controller
public class RankingController {

	@Autowired
	private RankingService rs;
	
	@Autowired
	private CategoryService cs;
	
	
	
	@RequestMapping(value="RankingMainView")
	public String RankingMainView(Category cate, Ranking rank, Model model, HttpSession session) {
		
		if(session.getAttribute("sessionId") == null ) {
			return "member/LoginView";
		}
		
		List<Category> parList = cs.CategoryParentNameList(cate);
		
		HashMap<String, String> hm = (HashMap<String, String>)session.getAttribute("ssMap") ;
		
		String skinType = (String) hm.get("skinType");
		if(skinType.equals("0")) {
			skinType = "건성";
		} else if(skinType.equals("1")) {
			skinType = "중성";
		} else if(skinType.equals("2")) {
			skinType = "지성";
		} else if(skinType.equals("3")) {
			skinType = "복합성";
		}
		rank.setGroupName(skinType);
		List<ItemInfo> typeBestList = rs.RankingType(rank);
		
		System.out.println("GroupName -> " + rank.getGroupName());
		
		String sex = (String) hm.get("sex");
		if(sex.equals("0")) {
			sex = "남성";
		} else {
			sex = "여성";
		}
		rank.setGroupName(sex);
		List<ItemInfo> sexBestList = rs.RankingType(rank);
		
		System.out.println("GroupName -> " + rank.getGroupName());
		
		List<ItemInfo> likeBestList = rs.LikeRank(rank);
		
		
		for(ItemInfo i : likeBestList) {
			System.out.println("bestList ------> " + i.getName());
		}
		
		model.addAttribute("likeBestList", likeBestList);
		model.addAttribute("parList", parList);
		model.addAttribute("typeBestList", typeBestList);
		model.addAttribute("sexBestList", sexBestList);
		
		return "/ranking/RankingMainView";
	}
	
	@RequestMapping(value="RankingType")
	public String RankingType(Category cate, Ranking rank, Model model) {
		
		
		List<ItemInfo> list = rs.RankingType(rank);		
		List<Category> parList = cs.CategoryParentNameList(cate);
		
		model.addAttribute("parList", parList);
		model.addAttribute("list", list);	
		
		
		for(ItemInfo t : list) {
			System.out.println("itemList -> " +t.getName());
		}
		
		System.out.println(rank.getGroupName());		
		model.addAttribute("groupName", rank.getGroupName());
		
		return "/ranking/RankingType";
	}

	
	
}
