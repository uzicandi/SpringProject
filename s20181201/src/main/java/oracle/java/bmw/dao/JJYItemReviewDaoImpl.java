package oracle.java.bmw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.Comments;

@Repository
public class JJYItemReviewDaoImpl implements JJYItemReviewDao {

	@Autowired
	private SqlSession session;

	@Override
	public int JJYItemReviewTotal(Comments comm) {
		return session.selectOne("JJYCommentsTotal", comm);
	}
	
	@Override
	public List<Comments> commentList(Comments comm) {
		return session.selectList("JJYCommentList", comm);
	}

	@Override
	public int commentWrite(Comments comm) {
		return session.insert("JJYCommentWrite", comm);
	}

	@Override
	public Comments commentSelect(Comments comm) {
		return session.selectOne("JJYCommentSelect", comm);
	}

	@Override
	public int commentUpdate(Comments comm) {
		return session.update("JJYCommentUpdate", comm);
	}

	@Override
	public int commentDelete(Comments comm) {
		return session.delete("JJYCommentDelete", comm);
	}

	@Override
	public long commentCount(Comments comm) {
		return session.selectOne("JJYCommentCount", comm);
	}

	@Override
	public int commentDeleteAll(Comments comm) {
		return session.delete("JJYCommentDeleteAll", comm);
	}

	@Override
	public int commentDeleteAllWithItem(Comments comment) {
		return session.delete("JJYCommentDeleteAllWithItem", comment);
	}

	@Override
	public int reviewLikeCntUpdateAdd(Comments comm) {
		return session.update("reviewLikeCntUpdateAdd", comm);
	}

	@Override
	public int reviewLikeCntUpdateSub(Comments comm) {
		return session.update("reviewLikeCntUpdateSub", comm);
	}

	@Override
	public void replyCntUpdate(Comments comm) {
		session.update("replyCntUpdate", comm);
	}

	@Override
	public void replyCntUpdateSub(Comments comm) {
		session.update("replyCntUpdateSub", comm);
	}

}
