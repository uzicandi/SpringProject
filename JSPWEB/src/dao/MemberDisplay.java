package dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDisplay {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String loginUserId;
	private String loginUserGrade;
	
	private String memberId;
	private String passwd;
	private String nickName;
	private String name;
	private String birth;
	private String sex;
	private String skinType;
	private String skinComplex;
	private String grade;
	private String email;
	private String isEmail;
	private String phone;
	private String address;
	private String question;
	private String answer;
	private String joinDate;
	private String regDate;
	private List <MemberFiles> memberFileList;


	public MemberDisplay() {}
	
	public MemberDisplay(HttpServletRequest request, HttpServletResponse response, String initJob) throws SQLException {
		// 공통 처리
		this.request = request;
		this.response = response;
//		this.loginUserId = (String) request.getSession().getAttribute("loginUserId");
		this.loginUserId = (String) request.getSession().getAttribute("sessionId");
		this.loginUserGrade = UtilityDao.getInstance().getMemberGrade(this.loginUserId);
	}
	
	public MemberDisplay(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// 공통 처리
		this.request = request;
		this.response = response;
//		this.loginUserId = (String) request.getSession().getAttribute("loginUserId");
		this.loginUserId = (String) request.getSession().getAttribute("sessionId");
		this.loginUserGrade = UtilityDao.getInstance().getMemberGrade(this.loginUserId);
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

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginUserGrade() {
		return loginUserGrade;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public String getSkinComplex() {
		return skinComplex;
	}

	public void setSkinComplex(String skinComplex) {
		this.skinComplex = skinComplex;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public List<MemberFiles> getMemberFileList() {
		return memberFileList;
	}

	public void setMemberFileList(List<MemberFiles> memberFileList) {
		this.memberFileList = memberFileList;
	}


}
