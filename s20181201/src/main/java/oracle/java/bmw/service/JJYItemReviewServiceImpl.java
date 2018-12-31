package oracle.java.bmw.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.ClipDao;
import oracle.java.bmw.dao.JJYItemReviewDao;
import oracle.java.bmw.model.Comments;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.SaveFiles;

@Service
public class JJYItemReviewServiceImpl implements JJYItemReviewService {

	@Autowired
	private JJYItemReviewDao jrd;
	@Autowired
	private ClipDao cd;
	@Autowired
	private SaveFileService sfs;
	@Autowired
	private MemberService ms;

	@Override
	public int JJYItemReviewTotal(Comments comm) {
		return jrd.JJYItemReviewTotal(comm);
	}
	
	// 리뷰 불러오기
	@Override
	public List<Comments> commentList(Comments comm) {
		List<Comments> comms = jrd.commentList(comm);
		List<Comments> newComms = new ArrayList<Comments>();
		
		for(Comments comment : comms) {
			SaveFiles saveFile = new SaveFiles();
			saveFile.setMainNo(comment.getMainNo());
			saveFile.setSubNo(comment.getSubNo());
			saveFile.setRef_Table("REVIEW");
			comment.setSaveFileList(sfs.uploadFileRead(saveFile));
			
			saveFile = sfs.getMemberPicture(comment.getMemberId());
			comment.setMemberFilePath(saveFile.getFilePath());
			Member member = ms.nickNameToMemberId(comment.getMemberId());
			String age = Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(member.getBirth().substring(0,4)));
			member.setAge(age);
			comment.setCommMember(member);
			
			newComms.add(comment);
		}
		return newComms;
	}


	@Override
	public int commentWrite(Comments comm) {
		return jrd.commentWrite(comm);
	}


	@Override
	public Comments commentSelect(Comments comm) {
		return jrd.commentSelect(comm);
	}


	@Override
	public int commentUpdate(Comments comm) {
		return jrd.commentUpdate(comm);
	}


	@Override
	public int commentDelete(Comments comm) {
		return jrd.commentDelete(comm);
	}
	
	@Override
	public int commentDeleteAll(Comments comm) {
		return jrd.commentDeleteAll(comm);
	}


	@Override
	public long commentCount(Comments comm) {
		return jrd.commentCount(comm);
	}


	@Override
	public int reviewDelete(Comments comm) {
		Comments comment = new Comments();
		comment.setMainNo(comm.getSubNo());
		comment.setRef_Table("REVIEW");
		comment.setRef(comm.getMainNo());
		
		int result = commentDeleteAll(comment);
		
		return jrd.commentDelete(comm);
	}


	@Override
	public int reviewDeleteAll(Comments comm) {
		Comments comment = new Comments();
		comment.setRef_Table("REVIEW");
		comment.setRef(comm.getMainNo());
		
		int result = jrd.commentDeleteAllWithItem(comment);
		
		return jrd.commentDeleteAll(comm);
	}

	@Override
	public int reviewContentClipInsert(Comments com) {
		return cd.reviewContentClipInsert(com);
	}


	@Override
	public int reviewContentClipChk(Comments com) {
		return cd.reviewContentClipChk(com);
	}


	@Override
	public int reviewLikeCntUpdateAdd(Comments comm) {
		jrd.reviewLikeCntUpdateAdd(comm);
		Comments comment = jrd.commentSelect(comm);
		System.out.println("좋아요!!!!!!!!!! 추가!!!!!!!\n" + comment.toString());
		return comment.getLikeCnt();
	}


	@Override
	public int reviewLikeCntUpdateSub(Comments comm) {
		jrd.reviewLikeCntUpdateSub(comm);
		Comments comment = jrd.commentSelect(comm);
		System.out.println("좋아요!!!!!!!!!! 제거!!!!!!!\n" + comment.toString());
		return comment.getLikeCnt();
	}

	@Override
	public void replyCntUpdate(Comments comm) {
		jrd.replyCntUpdate(comm);
	}

	@Override
	public void replyCntUpdateSub(Comments comm) {
		jrd.replyCntUpdateSub(comm);
	}

	

}
