package oracle.java.bmw.model;

public class Utility {
	String tableName;
	String referenceTable;
	int mainNo;
	int subNo;
	String sql;

	
	@Override
	public String toString() {
		return "------Utility-------\n"
				+ "tableName : " + tableName + "\n"
				+ "referenceTable : " + referenceTable + "\n"
				+ "mainNo : " + mainNo + "\n"
				+ "subNo : " + subNo + "\n"
				+ "sql : " + sql + "\n"
				;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getReferenceTable() {
		return referenceTable;
	}

	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
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

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
