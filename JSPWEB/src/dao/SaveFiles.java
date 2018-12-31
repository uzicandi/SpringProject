package dao;

public class SaveFiles {
	// ---Table 속성-----------------
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
	// ---Table 속성-----------------

	public SaveFiles() {}

	public SaveFiles(int mainNo, String ref_Table) {
		this(mainNo, 0, ref_Table);
//		this.mainNo = mainNo;
//		this.ref_Table = ref_Table;
	}

	public SaveFiles(int mainNo, int subNo, String ref_Table) {
		this(mainNo, subNo, ref_Table, 0);
//		this.mainNo = mainNo;
//		this.subNo = subNo;
//		this.ref_Table = ref_Table;
	}

	public SaveFiles(int mainNo, int subNo, String ref_Table, int filesNo) {
		this(mainNo, subNo, ref_Table, filesNo, null, null, null, null, 0);
//		this.mainNo = mainNo;
//		this.subNo = subNo;
//		this.ref_Table = ref_Table;
//		this.filesNo = filesNo;
	}

	public SaveFiles(int mainNo, int subNo, String ref_Table, String originFileName, String savedFileName, String fileExtend, String filePath, long fileSize) {
		this(mainNo, subNo, ref_Table, 0, originFileName, savedFileName, fileExtend, filePath, fileSize);
//		this.mainNo = mainNo;
//		this.subNo = subNo;
//		this.ref_Table = ref_Table;
//		this.originFileName = originFileName;
//		this.savedFileName = savedFileName;
//		this.fileExtend = fileExtend;
//		this.filePath = filePath;
//		this.fileSize = fileSize;
	}

	public SaveFiles(int mainNo, int subNo, String ref_Table, int filesNo, String originFileName, String savedFileName, String fileExtend, String filePath, long fileSize) {
		this(mainNo, subNo, ref_Table, filesNo, originFileName, savedFileName, fileExtend, filePath, fileSize, null);
//		this.mainNo = mainNo;
//		this.subNo = subNo;
//		this.ref_Table = ref_Table;
//		this.originFileName = originFileName;
//		this.savedFileName = savedFileName;
//		this.fileExtend = fileExtend;
//		this.filePath = filePath;
//		this.fileSize = fileSize;
	}

	public SaveFiles(int mainNo, int subNo, String ref_Table, int filesNo, String originFileName, String savedFileName, String fileExtend, String filePath, long fileSize, String regDate) {
		this.mainNo = mainNo;
		this.subNo = subNo;
		this.ref_Table = ref_Table;
		this.filesNo = filesNo;
		this.originFileName = originFileName;
		this.savedFileName = savedFileName;
		this.fileExtend = fileExtend;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.regDate = regDate;
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
