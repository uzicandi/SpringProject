package oracle.java.bmw.service;




import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.ClipDao;
import oracle.java.bmw.dao.MemberDao;
import oracle.java.bmw.dao.PointDao;
import oracle.java.bmw.model.Clip;
import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.Point;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao md;
	
	@Autowired
	private PointDao pd;
	
	@Autowired
	private ClipDao cd;
	
	// ------------------- 원규 --------------------
	@Override
	public int memberInsert(Member member) {
		return md.memberInsert(member);
	}
	
	@Override
	public int memberIdCheck(String memberId) {
		return md.memberIdCheck(memberId);
	}
	@Override
	public int memberNickCheck(String nickName) {
		return md.memberNickCheck(nickName);
	}
	@Override
	public int memberMailTestResult(Member member) {
		return md.memberMailTestResult(member);
	}
	
	@Override
	public Member memberInfo(Member member) {
		return md.memberInfo(member);
	}

	@Override
	public int memberWithdrawal(String memberId) {
		return md.memberWithdrawal(memberId);
	}

	@Override
	public int lastPoint(String memberId) {
		return pd.lastPoint(memberId);
	}
	
	@Override
	public int pointTotal(String memberId) {
		return pd.pointTotal(memberId);
	}
	
	@Override
	public List<Point> pointList(Point point) {
		return pd.pointList(point);
	}

	@Override
	public int pointCharge(Point point) {
		return pd.pointCharge(point);
	}
	
	@Override
	public int reviewClipTotal(String memberId) {
		return cd.reviewClipTotal(memberId);
	}
	
	@Override
	public List<Clip> reviewClipList(Clip clip) {
		return cd.reviewClipList(clip);
	}
	
	@Override
	public int reviewClipDelete(Clip clip) {
		return cd.reviewClipDelete(clip);
	}

	@Override
	public int boardClipTotal(String memberId) {
		return cd.boardClipTotal(memberId);
	}
	
	@Override
	public List<Clip> boardClipList(Clip clip) {
		return cd.boardClipList(clip);
	}
	
	@Override
	public int boardClipDelete(Clip clip) {
		return cd.boardClipDelete(clip);
	}

	@Override
	public int selectSeq(String id) {
		return md.selectSeq(id);
	}


	// ------------------- 동욱 --------------------
	@Override
	public List<Member> memberToListAdmin(Member member) {
		return md.memberToListAdmin(member); 
	}

	@Override
	public int memberListTotal(Member member) { 
		System.out.println("total start - >");
		return md.memberListTotal(member);
	}

	@Override
	public Member memberToDetailAdmin(String memberId) {
		Member member = md.memberToDetailAdmin(memberId);
		System.out.println("memberId 시작 " + memberId);
		return member;
	}

	@Override
	public int memberToUpdateAdmin(Member member) {
	      System.out.println("KDWupdate start.." );
	      System.out.println("KDWupdate grade.." +member.getGrade());
	      return md.memberToUpdateAdmin(member);
	}
	
	
	// ------------------- 지우 --------------------

	// 01_01. 회원 로그인체크
	@Override
	public boolean loginCheck(Member member, HttpSession session) {
		boolean result = md.loginCheck(member);
		System.out.println("MemberServiceImpl LOGINCHECK...result->"+result);
		if(result) {	//true일 경우 세션에 등록
		    Member member1 = viewMember(member);	
			//viewMember = memberMapper.xml(mybatis)에서 select id 로 씀! -- 지금은 필요한지 모르겠음.
			//세션 변수 등록
			session.setAttribute("memberId", member1.getMemberId());
			session.setAttribute("memberName", member1.getName());
		}
		return result;
	}
	// 01_02. 회원 로그인 정보
	@Override
	public Member viewMember(Member member) {
		return md.viewMember(member);
	}
	
	// 02. 회원 로그아웃
	@Override
	public void logout(HttpSession session) {
		session.invalidate();	
	}
	@Override
	public Member updateForm(String memberId) {
		System.out.println("MemberServiceImpl UPDATE CHECK");
		return md.updateForm(memberId);
	}
	@Override
	public void updateMember(Member member) {
		md.updateMember(member);
	}
	@Override
	public Member memberGetInfo(String memberId) {
		return md.memberGetInfo(memberId);
	}
	
	// update form
	
	@Override
	public String findIdCheck(Member member, HttpSession session) {
		return md.findIdCheck(member);
	}

	@Override
	public int update_pw(Member member) {
		System.out.println("KJWFindServiceImpl update_pw Start...");
		return md.update_pw(member);
	}

	@Override
	public boolean findPwCheck(Member member) {
		return md.findPwCheck(member);
	}
	
	
	@Override
	public Member mypageForm(String memberId) {
		System.out.println("MemberServiceImpl MYPAGE FORM CHECK");
		return md.mypageForm(memberId);
	}

	@Override
	public Member nickNameToMemberId(String nickName) {
		System.out.println("service");
		return md.nickNameToMemberId(nickName);
	}

	@Override
	public Member nickNameToMemberAll(String nickName) {
		return md.nickNameToMemberAll(nickName);
	}
	
	
	
}
