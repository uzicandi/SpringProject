package dao;

import java.util.List;

public class Board {
	private int boardNo;
	private String boardCategory;
	private String title;
	private String content;
	private String viewGrade;
	private String isPublic;
	private int hits;
	private String memberId;
	private String regDate;
	private long commentCount;
	private List<SaveFiles> saveFileList;
	
	public Board() {
		
	}
	
	public Board(String boardCategory, String title, String content, String viewGrade, String isPublic, String memberId) {
		this.boardCategory = boardCategory;
		this.title = title;
		this.content = content;
		this.viewGrade = viewGrade;
		this.isPublic = isPublic;
		this.memberId = memberId;
	}
	
	public Board(int boardNo, String boardCategory, String title, String content, String viewGrade, String isPublic, String memberId) {
		this.boardNo = boardNo;
		this.boardCategory = boardCategory;
		this.title = title;
		this.content = content;
		this.viewGrade = viewGrade;
		this.isPublic = isPublic;
		this.memberId = memberId;
	}

	public Board(int boardNo, String boardCategory, String title, String content, String viewGrade, String isPublic, int hits, String memberId, String regDate) {
		this.boardNo = boardNo;
		this.boardCategory = boardCategory;
		this.title = title;
		this.content = content;
		this.viewGrade = viewGrade;
		this.isPublic = isPublic;
		this.hits = hits;
		this.memberId = memberId;
		this.regDate = regDate;
	}
	
	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
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
}
