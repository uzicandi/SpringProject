package oracle.java.bmw.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Ranking;

@Repository
public class RankingDaoImpl implements RankingDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<ItemInfo> RankingType(Ranking rank) {
		return session.selectList("RankingType", rank);
	}

	@Override
	public List<ItemInfo> LikeRank(Ranking rank) {
		return session.selectList("LikeRank", rank);
	}

	//동범
	@Override
	public List<ItemInfo> SellRankList() {
		return session.selectList("SellRankList");
	}

	@Override
	public List<ItemInfo> HitsItemReviewRank() {
		return session.selectList("HitsItemReviewRank");
	}


	


}
