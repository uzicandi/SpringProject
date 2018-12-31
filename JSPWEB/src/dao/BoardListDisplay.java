package dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListDisplay {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private final int DEFALT_PAGE_SIZE = 10;
	private final int DEFALT_BLOCK_SIZE = 10;
	private int totCnt;
	private int pageSize = 10;
	private int blockSize = 10;
	private int startRow;
	private int endRow;
	private int startNum;
	private int pageCnt;
	private int startPage;
	private int endPage;
	private int currentPage;
	private String pageNum;
	private String loginUserId;
	private String loginUserGrade;
	private String boardCategory;
	private List<BoardDisplay> boardDisplay;

	public BoardListDisplay() {	}
	
	public BoardListDisplay(HttpServletRequest request, HttpServletResponse response, String initJob) throws SQLException {
		// 공통 처리
		this.request = request;
		this.response = response;
		this.loginUserId = (String) request.getSession().getAttribute("loginUserId");
		this.loginUserGrade = UtilityDao.getInstance().getMemberGrade(this.loginUserId);
		this.boardCategory = request.getParameter("boardCategory");
		this.pageNum = request.getParameter("pageNum");
		if (this.boardCategory == null || this.boardCategory.equals("")) { this.boardCategory = "NOTICE"; }
		if (this.pageNum == null || this.pageNum.equals("")) { this.pageNum = "1"; }
		
		switch(initJob.toUpperCase()) {
		case "LIST":
			this.currentPage = Integer.parseInt(this.pageNum);
			this.totCnt = BoardDao.getInstance().getTotalCnt(this.loginUserId, this.loginUserGrade, this.boardCategory);
			this.startRow = (this.currentPage - 1) * this.pageSize + 1;
			this.endRow =  this.startRow + this.pageSize - 1;
			this.startNum = this.totCnt - this.startRow + 1;
	
			// 초기화시 1
			this.pageCnt = (int)Math.ceil((double) this.totCnt / this.pageSize);
			// 초기화시 10
			this.startPage = (int)(this.currentPage-1) / this.blockSize * this.blockSize + 1;
			// 초기화시 10
			this.endPage = this.startPage + this.blockSize - 1;
			// 초기화시 4
			if(this.endPage > this.pageCnt) this.endPage = this.pageCnt;
			break;
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public int getTotCnt() {
		return totCnt;
	}
	
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize > DEFALT_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
	}
	public int getBlockSize() {
		return blockSize;
	}
	
	public void setBlockSize(int blockSize) {
		if(blockSize > DEFALT_BLOCK_SIZE) {
			this.blockSize = blockSize;
		}
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	public int getStartNum() {
		return startNum;
	}
	
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	
	public int getPageCnt() {
		return pageCnt;
	}
	
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getLoginUserId() {
		return loginUserId;
	}
	
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	
	public String getLoginUserGrade() {
		return loginUserGrade;
	}
	
//	public void setLoginUserGrade(String loginUserGrade) {
//		this.loginUserGrade = loginUserGrade;
//	}
	
	public String getBoardCategory() {
		return boardCategory;
	}
	
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	
	public List<BoardDisplay> getBoardDisplay() {
		return boardDisplay;
	}
	
	public void setBoardDisplay(List<BoardDisplay> boardDisplay) {
		this.boardDisplay = boardDisplay;
	}

}
