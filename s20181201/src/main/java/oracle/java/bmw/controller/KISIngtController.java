package oracle.java.bmw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oracle.java.bmw.model.Ingredient;
import oracle.java.bmw.service.KISIngtService;
import oracle.java.bmw.service.Paging;

@Controller
public class KISIngtController {
	
	@Autowired
	private KISIngtService kis;
	
	@RequestMapping(value="KISIngtList")
	public String KISIngtList(String keyword, Ingredient ingt, String currentPage, Model model) {
		if (keyword == null) {
			int total = kis.KISIngtTotal();
			System.out.println("total -> " + total);
			Paging pg = new Paging(total, currentPage);
			ingt.setStart(pg.getStart());
			ingt.setEnd(pg.getEnd());
			List<Ingredient> list = kis.ingtList(ingt);
			model.addAttribute("list", list);
			model.addAttribute("pg", pg);
			model.addAttribute("total", total);
			return "/item/KISIngtList";
		} else {
//		검색 후 페이징
			ingt.setKeyword(keyword);
			int total2 = kis.KISIngtSearchTotal(ingt);
			Paging pg = new Paging(total2, currentPage);
			ingt.setStart(pg.getStart());
			ingt.setEnd(pg.getEnd());
			List<Ingredient> listForSearch = kis.ingtListForSearch(ingt);						
			model.addAttribute("total", total2);
			model.addAttribute("pg", pg);
			model.addAttribute("list", listForSearch);
		}		
		return "/item/KISIngtList";
	}
	
	@RequestMapping(value="KISPopIngtList")
	public String KISPopIngtList(Ingredient ingt, String currentPage, Model model) {
		int total = kis.KISIngtTotal();
		Paging pg = new Paging(total, currentPage);
		ingt.setStart(pg.getStart());
		ingt.setEnd(total);
		List<Ingredient> list = kis.ingtList(ingt);
		
		model.addAttribute("list", list);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "/item/KISPopIngtList";
	}
	
	@RequestMapping(value="KISIngtWriteForm")
	public String KISIngtWriteForm(Model model) {
		return "/item/KISIngtWriteForm";
	}
	
	@RequestMapping(value="KISIngtWrite", method=RequestMethod.POST)
	public String KISIngtWrite(Ingredient ingt, Model model) {
		System.out.println("KISIngtWrite() 시작  ");
		int result = kis.insert(ingt);
		return "redirect:KISIngtList.do";
//		if(result > 0) {
//			return "redirect:KISIngtList.do";
//		} else {
//			model.addAttribute("msg", "입력실패");
//			return "forward:KISIngtWriteForm.do";
//		}
	}	
	
	@RequestMapping(value="KISIngtContent")
	public String KISIngtContent(int ingredientNo, Model model) {
		Ingredient ingt = kis.content(ingredientNo);
		model.addAttribute("ingt", ingt);
		return "/item/KISIngtContent";
	}
	
	@RequestMapping(value="KISIngtUpdateForm")
	public String KISIngtUpdate(int ingredientNo, Model model) {
		Ingredient ingt = kis.content(ingredientNo);
		model.addAttribute("ingt", ingt);
		return "/item/KISIngtUpdateForm";		
	}
	
	@RequestMapping(value="KISIngtUpdate", method=RequestMethod.POST)
	public String KISIngtUpdate(Ingredient ingt, Model model) {
		int u = kis.update(ingt);
		return "redirect:KISIngtList.do";
	}
	
	@RequestMapping(value="KISIngtDelete")
	public String KISIngtDelete(int ingredientNo, Model model) {
		kis.delete(ingredientNo);
		return "redirect:KISIngtList.do";
	}
	

}

