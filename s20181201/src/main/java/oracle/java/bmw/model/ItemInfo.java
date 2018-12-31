package oracle.java.bmw.model;

import java.util.List;

public class ItemInfo {
	private int itemNo;
	private int categoryNo;
	private String brand;
	private String name;
	private int itemStock;
	private String info;
	private int price;
	private float rating;
	private int hits;
	private String memberId;
	private String isPublic;
	private int likeCnt;
	private String htag;
	private String regDate;
	
	//동범 추가
	private String filePath;
	private String savedFileName;

	// 카테고리
	private int cateNum;
	private String cateName;
	private String parent;
	private String cateIsPublic;
	private String division;

	// 조회용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	
	// 랭킹 번호
	private int rankNo;

	// 사진
	private List<SaveFiles> saveFileList;

	// rating 처리
	private float ratingSum;
	private float ratingCnt;

	
	@Override
	public String toString() {
		return "------itemInfo------\n"
				+ "itemNo : " + itemNo + "\n"
				+ "categoryNo : " + categoryNo + "\n"
				+ "brand : " + brand + "\n"
				+ "name : " + name + "\n"
				+ "itemStock : " + itemStock + "\n"
				+ "info : " + info + "\n"
				+ "price : " + price + "\n"
				+ "rating : " + rating + "\n"
				+ "hits : " + hits + "\n"
				+ "memberId : " + memberId + "\n"
				+ "isPublic : " + itemNo + "\n"
				+ "likeCnt : " + likeCnt + "\n"
				+ "htag : " + htag + "\n"
				+ "regDate : " + regDate + "\n"
				+ "// rating 확인용 \n"
				+ "ratingSum : " + ratingSum + "\n"
				+ "ratingCnt : " + ratingCnt + "\n";
				
		
	}
	
	public float calculationAvg(float rating) {
		float sum = this.ratingSum * (this.ratingCnt - 1) + rating;
		System.out.println("----calculationAvg----");
		System.out.println("originRating = " + this.ratingSum);
		System.out.println("newRating = " + rating);
		System.out.println("ratingSum = " + sum);
		System.out.println("avg = " + sum / this.ratingCnt);
		System.out.println();
		
		return sum / this.ratingCnt;
	}
	
	public int getRankNo() {
		return rankNo;
	}

	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}

	public List<SaveFiles> getSaveFileList() {
		return saveFileList;
	}

	public void setSaveFileList(List<SaveFiles> saveFileList) {
		this.saveFileList = saveFileList;
	}

	public float getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(float ratingSum) {
		this.ratingSum = ratingSum;
	}

	public float getRatingCnt() {
		return ratingCnt;
	}

	public void setRatingCnt(float ratingCnt) {
		this.ratingCnt = ratingCnt;
	}

	public int getCateNum() {
		return cateNum;
	}

	public void setCateNum(int cateNum) {
		this.cateNum = cateNum;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCateIsPublic() {
		return cateIsPublic;
	}

	public void setCateIsPublic(String cateIsPublic) {
		this.cateIsPublic = cateIsPublic;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getHtag() {
		return htag;
	}

	public void setHtag(String htag) {
		this.htag = htag;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	
}
