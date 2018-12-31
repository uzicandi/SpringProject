package dao;

public class MemberFiles {
	private String memberId;
	private int seq;
	private SaveFiles saveFile;
	
	public MemberFiles() { }
	
	public MemberFiles(String memberId) {
		this.memberId = memberId;
	}

	public MemberFiles(String memberId, int seq, SaveFiles saveFile) {
		this.memberId = memberId;
		this.seq = seq;
		this.saveFile = saveFile;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public SaveFiles getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(SaveFiles saveFile) {
		this.saveFile = saveFile;
	}
}
