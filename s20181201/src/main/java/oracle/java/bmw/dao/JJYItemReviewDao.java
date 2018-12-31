package oracle.java.bmw.dao;

import java.util.List;

import oracle.java.bmw.model.Comments;

public interface JJYItemReviewDao {

	List<Comments> commentList(Comments comm);

	int JJYItemReviewTotal(Comments comm);

	int commentWrite(Comments comm);

	Comments commentSelect(Comments comm);

	int commentUpdate(Comments comm);

	int commentDelete(Comments comm);

	long commentCount(Comments comm);

	int commentDeleteAll(Comments comm);

	int commentDeleteAllWithItem(Comments comment);

	int reviewLikeCntUpdateAdd(Comments comm);

	int reviewLikeCntUpdateSub(Comments comm);

	void replyCntUpdate(Comments comm);

	void replyCntUpdateSub(Comments comm);

}
