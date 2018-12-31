package oracle.java.bmw.service;

import java.util.List;

import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Ranking;

public interface RankingService {

	List<ItemInfo> RankingType(Ranking rank);

	List<ItemInfo> LikeRank(Ranking rank);

	//동범
	List<ItemInfo> SellRankList();
	List<ItemInfo> HitsItemReviewRank();
}
