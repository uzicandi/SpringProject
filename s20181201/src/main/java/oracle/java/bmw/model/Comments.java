package oracle.java.bmw.model;

import java.util.List;

public class Comments {
	private int mainNo;
	private int subNo;
	private String ref_Table;
	private String isPublic;
	private int hits;
	private int ref;
	private int re_Step;
	private int re_Level;
	private String content;
	private int rating;
	private int likeCnt;
	private String memberId;
	private String content2;
	private String content3;
	private String htag;
	private String regDate;
	private int replyCnt;

	// 조회용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	private String currentPage;
	private String currentPageRv;
	private int commPageNum;
	private long commentCount;

	// 사진
	private List<SaveFiles> saveFileList;
	private String memberFilePath;

	private Member commMember;

	// resultMap에 필요
	private int ratingSum;
	private float ratingCnt;

	@Override
	public String toString() {
		String str = "-------Comments info-------\n";
		str += "mainNo : " + getMainNo() + "\n";
		str += "subNo : " + getSubNo() + "\n";
		str += "ref_Table : " + getRef_Table() + "\n";
		str += "isPublic : " + getIsPublic() + "\n";
		str += "hits : " + getHits() + "\n";
		str += "ref : " + getRef() + "\n";
		str += "re_Step : " + getRe_Step() + "\n";
		str += "re_Level : " + getRe_Level() + "\n";
		str += "content : " + getContent() + "\n";
		str += "rating : " + getRating() + "\n";
		str += "likeCnt : " + getLikeCnt() + "\n";
		str += "memberId : " + getMemberId() + "\n";
		str += "content2 : " + getContent2() + "\n";
		str += "content3 : " + getContent3() + "\n";
		str += "htag : " + getHtag() + "\n";
		str += "regDate : " + getRegDate() + "\n";
		str += "replyCnt : " + getReplyCnt() + "\n";
		str += "//조회용\n";
		str += "commentCount : " + getCommentCount() + "\n";
		str += "commPageNum : " + getCommPageNum() + "\n";
		str += "//사진확인\n";
		str += "saveFileList : " + getSaveFileList() + "\n";
		str += "memberFilePath : " + getMemberFilePath() + "\n";
		str += "commMember : " + getCommMember() + "\n";

		return str;
	}

	public Member getCommMember() {
		return commMember;
	}

	public void setCommMember(Member commMember) {
		this.commMember = commMember;
	}

	public String getMemberFilePath() {
		return memberFilePath;
	}

	public void setMemberFilePath(String memberFilePath) {
		this.memberFilePath = memberFilePath;
	}

	public List<SaveFiles> getSaveFileList() {
		return saveFileList;
	}

	public void setSaveFileList(List<SaveFiles> saveFileList) {
		this.saveFileList = saveFileList;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public int getCommPageNum() {
		return commPageNum;
	}

	public void setCommPageNum(int commPageNum) {
		this.commPageNum = commPageNum;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPageRv() {
		return currentPageRv;
	}

	public void setCurrentPageRv(String currentPageRv) {
		this.currentPageRv = currentPageRv;
	}

	public int getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(int ratingSum) {
		this.ratingSum = ratingSum;
	}

	public float getRatingCnt() {
		return ratingCnt;
	}

	public void setRatingCnt(float ratingCnt) {
		this.ratingCnt = ratingCnt;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
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

	public int getRe_Step() {
		return re_Step;
	}

	public void setRe_Step(int re_Step) {
		this.re_Step = re_Step;
	}

	public int getRe_Level() {
		return re_Level;
	}

	public void setRe_Level(int re_Level) {
		this.re_Level = re_Level;
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

	public String getHtag() {
		return htag;
	}

	public void setHtag(String htag) {
		this.htag = htag;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
