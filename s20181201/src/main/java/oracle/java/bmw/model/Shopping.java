package oracle.java.bmw.model;

public class Shopping {
	private int orderNo;
	private String memberId;
	private String itemNo;
	private int amount;
	private String receiver;
	private String recvTel;
	private String address;
	private String status;
	private String memo;
	private String regDate;
	private String name;
	private String price;
	private String filePath;
	
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "------Shopping------\n" + "orderNo : " + orderNo + "\n" + "memberId : " + memberId + "\n" + "itemNo : "
				+ itemNo + "\n" + "amount : " + amount + "\n" + "receiver : " + receiver + "\n" + "recvTel : " + recvTel
				+ "\n" + "address : " + address + "\n" + "status : " + status + "\n" + "memo : " + memo + "\n"
				+ "regDate : " + regDate + "\n";

	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRecvTel() {
		return recvTel;
	}

	public void setRecvTel(String recvTel) {
		this.recvTel = recvTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
