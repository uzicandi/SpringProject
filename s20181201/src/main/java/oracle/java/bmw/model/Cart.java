package oracle.java.bmw.model;

public class Cart {
	//ItemInfo
	private int price;
	
	//clip
	private String memberId;
    private int mainNo;	//cart: itemNo
    private int subNo;	//cart: amount
    private String title;	
    private String regDate;
    private String ref_Table; // cart
    private String filePath; // 사진저장경로
    
    
	//가격합산
    private int money;
    
    
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRef_Table() {
		return ref_Table;
	}
	public void setRef_Table(String ref_Table) {
		this.ref_Table = ref_Table;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
    
    
    
    
}
