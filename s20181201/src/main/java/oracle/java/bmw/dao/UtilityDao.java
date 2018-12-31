package oracle.java.bmw.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.Utility;

@Repository
public class UtilityDao {

	@Autowired
	private SqlSession session;
	
	

	public int getMaxNoForTable(String tableName) {
		String sql = "fn_MaxNoForTable(?)";

		return getMaxNoForTable(tableName, null, -1, -1, sql);
	}

	public int getMaxNoForTable(String tableName, String referenceTable, int mainNo) {
		String sql = "fn_MaxNoForTable(?, ?, ?)";

		return getMaxNoForTable(tableName, referenceTable, mainNo, -1, sql);
	}

	public int getMaxNoForTable(String tableName, String referenceTable, int mainNo, int subNo) {
		String sql = "fn_MaxNoForTable(?, ?, ?, ?)";

		return getMaxNoForTable(tableName, referenceTable, mainNo, subNo, sql);
	}

	public int getMaxNoForTable(String tableName, String referenceTable, int mainNo, int subNo, String sql) {
		Utility util = new Utility();
		util.setTableName(tableName);
		util.setReferenceTable(referenceTable);
		util.setMainNo(mainNo);
		util.setSubNo(subNo);
		util.setSql(sql);
		
		System.out.println("getMaxNoForTable DAO\n" + util.toString());

		return session.selectOne("getMaxNoForTable", util);

	}
}
