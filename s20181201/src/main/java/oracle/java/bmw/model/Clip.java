package oracle.java.bmw.model;

public class Clip {
	private String memberId;
    private int mainNo;
    private int subNo;
    private String title;
    private String ref_Table;
    private String regDate;
    private String name;
    
  
	//조회용
    private String search;
	private String keyword;
	private String pageNum;
	
    private int start;
	private int end;
    
	
	@Override
	public String toString() {
		return "-----Clip-----\n"
				+ "memberId : " + memberId + "\n"
				+ "mainNo : " + mainNo + "\n"
				+ "subNo : " + subNo + "\n"
				+ "title : " + title + "\n"
				+ "ref_Table : " + ref_Table + "\n"
				+ "regDate : " + regDate + "\n"
				+ "name : " + name + "\n";
	}
    public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getMainNo() {
		return mainNo;
	}
	public void setMainNo(int mainNo) {
		this.mainNo = mainNo;
	}
	public int getSubNo() {
		return subNo;
	}
	public void setSubNo(int subNo) {
		this.subNo = subNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRef_Table() {
		return ref_Table;
	}
	public void setRef_Table(String ref_Table) {
		this.ref_Table = ref_Table;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
