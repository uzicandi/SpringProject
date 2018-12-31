package oracle.java.bmw.dao;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.Member;



@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession session;
	
//	@Override
//	public List<Board> SDBBoardList(Board board) {		
//					xml로 가서    id="selectBoard", 인자로 board 보냄 
//		return session.selectList("selectBoard", board);
//	}
	
	// ------------------- 원규 --------------------
	// 회원가입
	@Override
	public int memberInsert(Member member) {	
		int result = 0;
		try {
			member.setBirth(member.getBirth().replace("-", ""));
			result = session.insert("memberInsert", member);		// member 테이블에 insert
			session.insert("memberFilesInsert", member);			// memberfiles 테이블에 insert
			int seq = session.selectOne("memberFilesSel", member);	// memberfiles 테이블에서 seq값 조회
			session.insert("memberPicInsert", seq);					// 조회한 seq값으로 savefiles 테이블에 insert
		} catch(Exception e){ 
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	// 아이디 중복체크
	@Override 
	public int memberIdCheck(String memberId) {
		return session.selectOne("idchk", memberId);
	}
	
	// 닉네임 중복체크
	@Override
	public int memberNickCheck(String nickName) {
		return session.selectOne("nickchk", nickName);
	}

	// 회원가입 후 메일로 승인하면 회원등급변경과 point 테이블에 레코드 생성
	@Override
	public int memberMailTestResult(Member member) {
		int result = 0;
		try {
			result = session.update("mailTestSuccess", member);
			if(result == 1) {
				// 가입하면 point 테이블에 0포인트로 첫레코드 입력
				session.insert("setFirstPoint", member);
			}
		} catch(Exception e){ 
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	@Override
	public Member memberInfo(Member member) {
		return session.selectOne("memberInfo", member);
	}

	@Override
	public int memberWithdrawal(String memberId) {
		return session.update("memberWithdrawal", memberId);
	}

	@Override
	public int selectSeq(String id) {
		return session.selectOne("memberFilesSel", id);
	}
	

	// ------------------- 동욱 --------------------
	@Override
	public List<Member> memberToListAdmin(Member member) {
		System.out.println("KDWAdminDaoImpl keyword -->" + member.getKeyword());
		System.out.println("KDWAdminDaoImpl search -->" + member.getSearch());
		return session.selectList("memberListByAdmin", member); 
	}
 
	@Override
	public int memberListTotal(Member member) {
		System.out.println("KDWAdminDaoImpl KDWAdmintotal keyword -->" + member.getKeyword());
		System.out.println("KDWAdminDaoImpl KDWAdmintotal search -->" + member.getSearch());
		System.out.println("Dao total");
		return session.selectOne("totalByAdmin", member);
	}

	@Override
	public Member memberToDetailAdmin(String memberId) {
		Member member = session.selectOne("detailByAdmin", memberId);
		return member;
	}

	@Override
	public int memberToUpdateAdmin(Member member) {
		System.out.println("Dao KDWupdate member.getMemberId()--->" + member.getMemberId());
		System.out.println("Dao KDWupdate member.getMemberId()--->" + member.getPhone());
		return session.update("updateByAdmin", member);
	}
	
	
	// ------------------- 지우 --------------------
	// 01_01. 회원 로그인체크
		@Override
		public boolean loginCheck(Member member) {
			System.out.println("MemberDaoImpl member.loginCheck Before...");
		//	String memberId = session.selectOne("member.loginCheck", member);
			String memberId = session.selectOne("loginCheck", member);
			System.out.println("MemberDaoImpl member.loginCheck After...");
			return (memberId == null) ? false : true;
		}
		
		// 01_02. 회원 로그인 정보
		@Override
		public Member viewMember(Member member) {
			return session.selectOne("viewMember", member);
		}
		
		// 02. 회원 로그아웃
		@Override
		public void logout(HttpSession session) {	
		}

		// update Form
		@Override
		public Member updateForm(String memberId) {
			System.out.println("MemberDAOImpl UPDATE CHECK");
			return session.selectOne("updateForm",memberId);
		}

		@Override
		public void updateMember(Member member) {
			session.update("updateMember",member);
		}
		
		@Override
		public Member memberGetInfo(String memberId) {
			return session.selectOne("memberGetInfo", memberId);
		}
		
		@Override
		public String findIdCheck(Member member) {
			System.out.println("KJWFindDaoImpl before...");
			System.out.println("1. " + member.getEmail() + "\n2. " + member.getQuestion() + "\n3. " + member.getAnswer());
			//String result = session.selectOne("findIdCheck",member);
			System.out.println("KJWFindDaoImpl after...");
			/*return (result == null) ? false : true;*/
			return session.selectOne("findIdCheck",member);
		}

		@Override
		public int update_pw(Member member) {
			System.out.println("FindServiceImpl update_pw Start memberId->"+member.getMemberId());
			int kkk = 0;
			try {
				kkk =  session.update("update_pw", member);
				kkk = 1;
			} catch (Exception e) {
				System.out.println("FindServiceImpl update_pw error->"+e.getMessage());
				kkk = -1;
			}
			return kkk;
		}

		@Override
		public boolean findPwCheck(Member member) {
			System.out.println("1. " + member.getMemberId() + "\n2. " + member.getEmail() + "\n3. " + member.getQuestion() + "\n4. " + member.getAnswer());
			String result = session.selectOne("findPwCheck",member);
			return (result == null) ? false : true;	
		}

		@Override
		public Member mypageForm(String memberId) {
			Member mem = session.selectOne("mypageForm", memberId);
			String join = mem.getJoinDate();
			mem.setJoinDate(join.substring(0, 4)+"년 "+join.substring(4, 6)+"월 "+join.substring(6,8)+"일");
			return mem; 
		}
		
//		재연
		@Override
		public Member nickNameToMemberId(String nickName) {
//			Member member = new Member();
//			member.setNickname(nickName);
			System.out.println("dao");
			return session.selectOne("nickNameToMemberId", nickName);
		}

		@Override
		public Member nickNameToMemberAll(String nickName) {
//			return session.selectOne("reviewMemberSel", nickName);
			return null;
		}
		
	
	
}
