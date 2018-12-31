package oracle.java.bmw.model;

public class Coupon {
	private int seq;
    private String memberId;
    private String cpCode;
    private String cpName;
    private int discount;
    private String cpReg;
    private String cpExp;
    private String used;
    
    
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCpCode() {
		return cpCode;
	}
	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCpReg() {
		return cpReg;
	}
	public void setCpReg(String cpReg) {
		this.cpReg = cpReg;
	}
	public String getCpExp() {
		return cpExp;
	}
	public void setCpExp(String cpExp) {
		this.cpExp = cpExp;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
    
    
}
