package oracle.java.bmw.model;

public class SaveFiles {
	private int mainNo;
    private int subNo;
    private String ref_Table;
    private int filesNo;
    private String originFileName;
    private String savedFileName;
    private String fileExtend;
    private String filePath;
    private long fileSize;
    private String regDate;
    
    
    @Override
    public String toString() {
    	return "-----SaveFiles-----\n"
    			+ "mainNo : " + mainNo + "\n"
    			+ "subNo : " + subNo + "\n"
    			+ "ref_Table : " + ref_Table + "\n"
    			+ "filesNo : " + filesNo + "\n"
    			+ "originFileName : " + originFileName + "\n"
    			+ "masavedFileNameinNo : " + savedFileName + "\n"
    			+ "fileExtend : " + fileExtend + "\n"
    			+ "filePath : " + filePath + "\n"
    			+ "fileSize : " + fileSize + "\n"
    			+ "regDate : " + regDate + "\n"
    			;
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
	public String getRef_Table() {
		return ref_Table;
	}
	public void setRef_Table(String ref_Table) {
		this.ref_Table = ref_Table;
	}
	public int getFilesNo() {
		return filesNo;
	}
	public void setFilesNo(int filesNo) {
		this.filesNo = filesNo;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getSavedFileName() {
		return savedFileName;
	}
	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}
	public String getFileExtend() {
		return fileExtend;
	}
	public void setFileExtend(String fileExtend) {
		this.fileExtend = fileExtend;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
    
    
}
