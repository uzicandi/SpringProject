package oracle.java.bmw.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import oracle.java.bmw.model.Member;

public interface MemberDao {
	
	// ------------------- 원규 --------------------
	int		memberInsert(Member member);
	int		memberIdCheck(String memberId);
	int		memberNickCheck(String nickName);
	int		memberMailTestResult(Member member);
	Member	memberInfo(Member member);
	int		memberWithdrawal(String memberId);
	int		selectSeq(String id);
	
	// ------------------- 동욱 --------------------
	List<Member> memberToListAdmin(Member member);
	int memberListTotal(Member member);
	Member memberToDetailAdmin(String memberId);
	int memberToUpdateAdmin(Member member);
	
	// ------------------- 지우 --------------------
	// 01_01. 회원 로그인체크
	public boolean loginCheck(Member member);
	// 01_02. 회원 로그인 정보
	public Member viewMember(Member member);
	// 02. 회원 로그아웃
	public void logout(HttpSession session);
	// update Form
	public Member updateForm(String memberId);
	public void updateMember(Member member);
	Member memberGetInfo(String memberId);

	// 01. 아이디찾기 (MAPPER와 연동하기 위한 DAO)
	public String findIdCheck(Member member);
		// 02. 비밀번호 변경(임시) 
	int update_pw(Member member);
	public boolean findPwCheck(Member member);
	
	// MyPage Form	
	public Member mypageForm(String memberId);
	Member nickNameToMemberId(String nickName);
	Member nickNameToMemberAll(String nickName);

}
