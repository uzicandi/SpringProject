package dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemDisplay {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String pageNum;
	private String loginUserId;
	private String loginUserGrade;

	private int itemNo;
	private int categoryNo;
	private String brand;
	private String name;
	private String info;
	private int price;
	private float rating;
	private int likeCnt;
	private String isPublic;
	private String memberId;
	private String regDate;
	private String nickName;
	private String grade;
	private List <SaveFiles> saveFileList;
	private List <CommentsDisplay> commentDisplay;

	public ItemDisplay() {}
	
	public ItemDisplay(HttpServletRequest request, HttpServletResponse response, String initJob) throws SQLException {
		// 공통 처리
		this.request = request;
		this.response = response;
//		this.loginUserId = (String) request.getSession().getAttribute("loginUserId");
		this.loginUserId = (String) request.getSession().getAttribute("sessionId");
		this.loginUserGrade = UtilityDao.getInstance().getMemberGrade(this.loginUserId);
		this.pageNum = request.getParameter("pageNum");
		if (this.pageNum == null || this.pageNum.equals("")) { this.pageNum = "1"; }
System.out.println("this.pageNum"+this.pageNum);		
		switch(initJob.toUpperCase()) {
		case "READ":
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

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public void setLoginUserGrade(String loginUserGrade) {
		this.loginUserGrade = loginUserGrade;
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
