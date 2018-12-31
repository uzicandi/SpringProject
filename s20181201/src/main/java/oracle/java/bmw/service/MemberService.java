package oracle.java.bmw.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.Point;
import oracle.java.bmw.model.SaveFiles;

public interface MemberService {
	
	// ------------------- 원규 --------------------
	int			memberInsert(Member member);
	int			memberIdCheck(String memberId);
	int			memberNickCheck(String nickName);
	int			memberMailTestResult(Member member);
	Member		memberInfo(Member member);
	int			memberWithdrawal(String memberId);
	int 		lastPoint(String memberId);
	int 		pointTotal(String memberId);
	List<Point> pointList(Point point);
	int			pointCharge(Point point);
	int			reviewClipTotal(String memberId);
	List<Clip> 	reviewClipList(Clip clip);
	int			reviewClipDelete(Clip clip);
	int			boardClipTotal(String memberId);
	List<Clip> 	boardClipList(Clip clip);
	int			boardClipDelete(Clip clip);
	int			selectSeq(String id);
	
	
	// ------------------- 동욱 --------------------
	List<Member> memberToListAdmin(Member member);
	int memberListTotal(Member member); // AdminControl의 total
	Member memberToDetailAdmin(String memberId);
	int memberToUpdateAdmin(Member member);
	
	// ------------------- 지우 --------------------

	// 01_01. 회원 로그인체크
	public boolean loginCheck(Member member, HttpSession session);
	// 01_02. 회원 로그인 정보
	public Member viewMember(Member member);
	// 02. 회원 로그아웃
	public void logout(HttpSession session);
	// 회원정보 수정 화면
	public Member updateForm(String memberId);
	public void updateMember(Member member);
	Member memberGetInfo(String memberId);
	
	//01. 아이디찾기 체크
		public String findIdCheck(Member member, HttpSession session);
	//02. 임시비번 업데이트
		int update_pw(Member member);
	//03. 임시비번 보내기 위한 정보확인
		public boolean findPwCheck(Member member);
		
	// MyPage Form	
	public Member mypageForm(String memberId);
	Member nickNameToMemberId(String nickName);
	
	// Review에 필요
	Member nickNameToMemberAll(String nickName);
	
}
