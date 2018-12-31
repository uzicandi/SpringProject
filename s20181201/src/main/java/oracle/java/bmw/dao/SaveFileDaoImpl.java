package oracle.java.bmw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.bmw.model.SaveFiles;

@Repository
public class SaveFileDaoImpl implements SaveFileDao {

	@Autowired
	private SqlSession session;

	
	@Override
	public int uploadFileInsertDB(SaveFiles saveFile) {
		return session.insert("uploadFileInsertDB", saveFile);
	}

	@Override
	public List<SaveFiles> uploadFileRead(SaveFiles savefile) {
		return session.selectList("uploadFileRead", savefile);
	}

	@Override
	public int uploadFileDelete(SaveFiles savefile) {
		return session.delete("uploadFileDelete", savefile);
	}

	@Override
	public int uploadFileDeleteAll(SaveFiles savefile) {
		return session.delete("uploadFileDeleteAll", savefile);
	}

	@Override
	public List<SaveFiles> uploadFileListAllwithItem(SaveFiles savefile) {
		return session.selectList("uploadFileListAllwithItem", savefile);
	}
	
	// 멤버용
	@Override
	public int uploadFileUpdateDB(SaveFiles saveFile) {
		return session.update("uploadFileUpdateDB", saveFile);
	}
	@Override
	public SaveFiles memberPictureRead(SaveFiles savefile) {
		return session.selectOne("memberPictureRead", savefile);
	}
	@Override
	public int mempicDelete(int mainNo) {
		return session.update("memberPictureDelete", mainNo);
	}
	
}
