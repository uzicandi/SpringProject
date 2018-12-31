package oracle.java.bmw.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.RankingDao;
import oracle.java.bmw.model.ItemInfo;
import oracle.java.bmw.model.Ranking;
import oracle.java.bmw.model.SaveFiles;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	private RankingDao rd;
	
	@Autowired
	private SaveFileService sfs;

	@Override
	public List<ItemInfo> RankingType(Ranking rank) {
		
		List<ItemInfo> itemList = rd.RankingType(rank);
		
		List<ItemInfo> allItemList = new ArrayList<ItemInfo>();
		
		for(ItemInfo itemInfo : itemList) {
			SaveFiles savefile = new SaveFiles();
			savefile.setMainNo(itemInfo.getItemNo());
			savefile.setSubNo(0);
			savefile.setRef_Table("ITEMINFO");
			itemInfo.setSaveFileList(sfs.uploadFileRead(savefile));
			
			allItemList.add(itemInfo);
		}
		
		return allItemList;
	
	}

	@Override
	public List<ItemInfo> LikeRank(Ranking rank) {
		
		List<ItemInfo> itemList = rd.LikeRank(rank);
		List<ItemInfo> allItemList = new ArrayList<ItemInfo>();
		
		for(ItemInfo itemInfo : itemList) {
			SaveFiles savefile = new SaveFiles();
			savefile.setMainNo(itemInfo.getItemNo());
			savefile.setSubNo(0);
			savefile.setRef_Table("ITEMINFO");
			itemInfo.setSaveFileList(sfs.uploadFileRead(savefile));
			
			allItemList.add(itemInfo);
		}
		
		return allItemList;
	}

	//동범
	@Override
	public List<ItemInfo> SellRankList() {
		return rd.SellRankList();
	}

	@Override
	public List<ItemInfo> HitsItemReviewRank() {
		return rd.HitsItemReviewRank();
	}
	
	
	
}
