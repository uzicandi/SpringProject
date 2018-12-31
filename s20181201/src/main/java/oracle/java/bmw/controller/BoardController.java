package oracle.java.bmw.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oracle.java.bmw.dao.JJYItemReviewDao;
import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.SaveFiles;
import oracle.java.bmw.service.BoardService;
import oracle.java.bmw.service.JJYItemReviewService;
import oracle.java.bmw.service.Paging;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private JJYItemReviewDao jrd;
	
	@Autowired
	JJYItemReviewService jrs;
	
	@Resource(name = "uploadServerPath")
	private String uploadServerPath;

	@RequestMapping(value="BoardList")
	public String BoardList(Board board, String currentPage, Model model, HttpSession session, String keyword, String search) {
		System.out.println("currentPage=>" + currentPage);
		System.out.println("keyword-> " + keyword);
		System.out.println("search-> " + search);
		
	/*	HashMap<String, String> optionItems = new HashMap<String, String>();
		optionItems.put("title", "제목");
		optionItems.put("내용", "content");
		optionItems.put("글쓴이", "memberId");
		model.addAttribute("optionItems", optionItems);*/
		
		board.setKeyword(keyword);
		board.setSearch(search);
		
		int total = bs.BoardTotal(board);
		System.out.println("total=>" + total);

		Paging pg = new Paging(total, currentPage, 15);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		
		System.out.println("pg.getStart()=>" + pg.getStart());
		System.out.println("pg.getEnd()=>" + pg.getEnd());
		List<Board> list = bs.BoardList(board);
		System.out.println("list.size()=>" + list.size());
		
		System.out.println("board의 세션아이디 -> " + session.getAttribute("sessionId"));
		model.addAttribute("list", list);
		model.addAttribute("pg", pg);
		model.addAttribute("keyword", keyword);
		
		return "board/BoardList";
	}
	
	@RequestMapping(value="BoardContent")
	public String BoardContent(int boardNo, Model model) {
		Board board = bs.BoardContent(boardNo);
		bs.BoardHitPlus(boardNo);	//조회수 증가
		model.addAttribute("SDBBoard", board);
		return "board/BoardContent";
	}
	
	@RequestMapping(value="BoardDelete")
	public String BoardDelete(int boardNo, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		
		List<SaveFiles> DeleteFileList = bs.BoardSaveFilesDeleteList(boardNo);
		System.out.println("DeleteFileList size -> " + DeleteFileList.size());
		
		for(int i = 0; i < DeleteFileList.size(); i++) {

			System.out.println("for문 안으로 들어옴");
			String path = uploadServerPath + "/board/";
			System.out.println("file객체생성전");
			File file = new File(path + DeleteFileList.get(i).getSavedFileName());
			System.out.println("file객체 생성 후");
			System.out.println(file);
			if(file.exists() == true) {
				System.out.println("삭제 파일의 이름-> " + file);
				file.delete();
				System.out.println("파일 삭제 성공");
			}
		}
		
		Comments comment = new Comments();
		comment.setMainNo(boardNo);
		comment.setRef_Table("BOARD");
		comment.setRef(boardNo);
		
		int result = jrd.commentDeleteAll(comment);
		System.out.println("commentDeleteAll result -> " + result);
		
		bs.BoardSaveFilesRealDelete(boardNo);
		bs.BoardDelete(boardNo);
		return "redirect:BoardList.do";
	}
	
	@RequestMapping(value="BoardWriteForm")	//게시판 글쓰기 폼으로 이동
	public String BoardWriteForm(Model model) {
		bs.BoardSaveFilesTempDelete();	//mainno가 0인거 지움 savefiles테이블에서
		return "board/BoardWriteForm";
	}
	
	@RequestMapping(value="BoardWrite", method=RequestMethod.POST)
	public String BoardWrite(Board board, Model model, HttpSession session) {
		System.out.println("BoardWrite 시작");
		HashMap<String, String> hm = (HashMap<String, String>)session.getAttribute("ssMap");
		System.out.println(hm);
		board.setMemberId(hm.get("nickname"));
		
		System.out.println("board cate-> " + board.getBoardCategory());
		System.out.println("board title-> " + board.getTitle());
		System.out.println("board content-> " + board.getContent());
		System.out.println("board nubmer-> " + board.getBoardNo());
		System.out.println("board memId -> " + board.getMemberId());
		
		int result = bs.BoardWrite(board);
		System.out.println("BoardWrite의 result -> " + result);
		if(result > 0){
			bs.BoardSaveFilesUpdate(board.getBoardCategory());
			return "redirect:BoardList.do";
		}
		else{
			bs.BoardSaveFilesTempDelete();
			return "forward:BoardWriteForm.do";
		}	
	}
	
	@RequestMapping(value="BoardUpdateForm")
	public String BoardUpdateForm(int boardNo, Model model) {
		Board board = bs.BoardContent(boardNo);
		model.addAttribute("SDBBoard", board);
		return "board/BoardUpdateForm";
	}
	
	@RequestMapping(value="BoardUpdate", method=RequestMethod.POST)
	public String BoardUpdate(Board board, Model model) {
		int result = bs.BoardUpdate(board);
		System.out.println("BoardUpdate result -> " + result);
		return "redirect:BoardList.do";
	}
	
	@RequestMapping(value="BoardSaveFilesWrite")
	public String BoardSaveFilesWrite(Model model, Board board, String savedFileName, String filePath) {
		board.setSavedFileName(savedFileName);
		board.setFilePath(filePath);
		int result = bs.BoardSaveFilesWrite(board);
		System.out.println("BoardSaveFilesWrite result -> " + result);
		return null;
	}
	
	@RequestMapping(value="BoardSaveFilesTempDelete")	//mainno가 0인거 지움
	public String BoardSaveFilesDelete(Model model) {
		int result = bs.BoardSaveFilesTempDelete();
		System.out.println("BoardSaveFilesTempDelete result -> " + result);
		return null;
	}
	
	@RequestMapping(value="BoardSaveFilesUpdate")	//mainno가 0인 값들을 바꾸는 함수
	public String BoardSaveFilesUpdate(Model model, Board board) {
		int result = bs.BoardSaveFilesUpdate(board.getBoardCategory());
		System.out.println("BoardSaveFilesUpdate result -> " + result);
		return null;
	}
	
	@RequestMapping(value="WebzineList")
	public String WebzineList(Board board, String currentPage, Model model, String boardCategory, SaveFiles sf) {
		boardCategory = "웹진";
		int total = bs.BoardCategoryTotal(boardCategory);
		System.out.println("Webzine total=>" + total);
/*		System.out.println("currentPage=>" + currentPage);

		Paging pg = new Paging(total, currentPage);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		System.out.println("pg.getStart()=>" + pg.getStart());
		System.out.println("pg.getEnd()=>" + pg.getEnd());
*/		List<Board> list = bs.WebzineList(board);
		System.out.println("Webzinelist.size()=> " + list.size());
		List<SaveFiles> saveFiles = bs.BoardSaveFilesList(sf);
		System.out.println("BoardSaveFilesList.size()=> " + saveFiles.size());
		
		for(int i = 0; i < saveFiles.size(); i++) {
			System.out.println(saveFiles.get(i).getSavedFileName());
		}
		
		model.addAttribute("list", list);
		model.addAttribute("saveFiles", saveFiles);
//		model.addAttribute("pg", pg);

		return "board/WebzineList";
	}
	
	/*@RequestMapping(value="SDBCommentContent")
	public String SDBCommentContent(Model model) {
		return "board/SDBCommentContent";
	}*/
	
	@RequestMapping(value="EventList")
	public String EventList(Board board, String currentPage, Model model, String boardCategory, SaveFiles sf) {
		boardCategory = "이벤트";
		int total = bs.BoardCategoryTotal(boardCategory);
		System.out.println("Event total=>" + total);
		System.out.println("currentPage=>" + currentPage);

		Paging pg = new Paging(total, currentPage, boardCategory);
		pg.setRowPage(1);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		System.out.println("pg.getStart()=>" + pg.getStart());
		System.out.println("pg.getEnd()=>" + pg.getEnd());
		List<Board> list = bs.EventList(board);
		System.out.println("Eventlist.size()=> " + list.size());
		List<SaveFiles> saveFiles = bs.BoardSaveFilesEventList(sf);
		System.out.println("BoardSaveFilesEventList.size()=> " + saveFiles.size());
		//board = bs.SDBSelectEvent(Integer.parseInt(currentPage));
		
		model.addAttribute("list", list);
		model.addAttribute("saveFiles", saveFiles);
		model.addAttribute("pg", pg);
		model.addAttribute("currentPage", currentPage);
		
		return "board/EventList";
	}
	
}