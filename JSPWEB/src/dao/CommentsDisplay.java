package dao;

import java.util.List;

public class CommentsDisplay {
	private int mainNo;
	private int subNo;
	private String ref_Table;
	private String isPublic;
	private int hits;
	private int ref;
	private int re_step;
	private int re_level;
	private String content;
	private int rating;
	private int likeCnt;
	private int memberId;
	private String regDate;
	private List <SaveFiles> commentFileList;

	public int getMainNo() {
		return mainNo;
	}

	public void setMainNo(int mainNo) {
		this.mainNo = mainNo;
	}

	public int getSubNo() {
		return subNo;
	}

	public void setSubNo(int subNo) {
		this.subNo = subNo;
	}

	public String getRef_Table() {
		return ref_Table;
	}

	public void setRef_Table(String ref_Table) {
		this.ref_Table = ref_Table;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public List<SaveFiles> getCommentFileList() {
		return commentFileList;
	}

	public void setCommentFileList(List<SaveFiles> commentFileList) {
		this.commentFileList = commentFileList;
	}

}
