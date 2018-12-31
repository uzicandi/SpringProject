package dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JJYReviewDisplay {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String pageNum;
//	private String loginUserId;
//	private String loginUserGrade;

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
	private String memberId;
	private String regDate;
	private String content2;
	private String content3;
	private long commentCount;
	private List<SaveFiles> saveFileList;

	public JJYReviewDisplay() {
	}

	public JJYReviewDisplay(HttpServletRequest request, HttpServletResponse response, String initJob)
			throws SQLException {
		// 공통 처리
		this.request = request;
		this.response = response;
//		this.loginUserId = (String) request.getSession().getAttribute("loginUserId");
//		this.loginUserGrade = UtilityDao.getInstance().getMemberGrade(this.loginUserId);
//		this.boardCategory = request.getParameter("boardCategory");
		this.ref_Table = request.getParameter("ref_Table");
		if (this.ref_Table == null || this.ref_Table.equals("")) {
			this.ref_Table = "ITEMINFO";
		}
		this.pageNum = request.getParameter("pageNum");
		if (this.pageNum == null || this.pageNum.equals("")) {
			this.pageNum = "1";
		}
//System.out.println("this.pageNum"+this.pageNum);		
		switch (initJob.toUpperCase()) {
		case "READ":
			this.mainNo = Integer.parseInt(request.getParameter("mainNo"));
			this.subNo = Integer.parseInt(request.getParameter("subNo"));
			break;
		default:
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

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public List<SaveFiles> getSaveFileList() {
		return saveFileList;
	}

	public void setSaveFileList(List<SaveFiles> saveFileList) {
		this.saveFileList = saveFileList;
	}
}
