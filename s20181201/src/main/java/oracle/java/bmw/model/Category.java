package oracle.java.bmw.model;

public class Category {
	private int categoryNo;
    private String name;
    private String parent;
    private String isPublic;
    private String division;
    
	// 조회용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	
	@Override
	public String toString() {
		return "-----category-----\n"
				+ "categoryNo : " + categoryNo + "\n"
				+ "name : " + name + "\n"
				+ "parent : " + parent + "\n"
				+ "isPublic : " + isPublic + "\n"
				+ "division : " + division + "\n";
	}
	
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
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
	
	
}
