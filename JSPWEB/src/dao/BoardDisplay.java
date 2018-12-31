package dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDisplay {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String pageNum;
	private String loginUserId;
	private String loginUserGrade;

	private int boardNo;
	private String boardCategory;
	private String title;
	private String content;
	private String viewGrade;
	private String isPublic;
	private int hits;
	private String memberId;
	private String regDate;
	private String nickName;
	private String grade;
	private List <SaveFiles> saveFileList;
	private List <CommentsDisplay> commentDisplay;

	public BoardDisplay() {}
	
	public BoardDisplay(HttpServletRequest request, HttpServletResponse response, String initJob) throws SQLException {
		// 공통 처리
		this.request = request;
		this.response = response;
//		this.loginUserId = (String) request.getSession().getAttribute("loginUserId");
		this.loginUserId = (String) request.getSession().getAttribute("sessionId");
		this.loginUserGrade = UtilityDao.getInstance().getMemberGrade(this.loginUserId);
		this.boardCategory = request.getParameter("boardCategory");
		this.pageNum = request.getParameter("pageNum");
		if (this.boardCategory == null || this.boardCategory.equals("")) { this.boardCategory = "NOTICE"; }
		if (this.pageNum == null || this.pageNum.equals("")) { this.pageNum = "1"; }
System.out.println("this.pageNum"+this.pageNum);		
		switch(initJob.toUpperCase()) {
		case "READ":
			this.boardNo = Integer.parseInt(request.getParameter("boardNo"));
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getViewGrade() {
		return viewGrade;
	}

	public void setViewGrade(String viewGrade) {
		this.viewGrade = viewGrade;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public List<SaveFiles> getSaveFileList() {
		return saveFileList;
	}

	public void setSaveFileList(List<SaveFiles> saveFileList) {
		this.saveFileList = saveFileList;
	}

	public List<CommentsDisplay> getCommentDisplay() {
		return commentDisplay;
	}

	public void setCommentDisplay(List<CommentsDisplay> commentDisplay) {
		this.commentDisplay = commentDisplay;
	}

}
