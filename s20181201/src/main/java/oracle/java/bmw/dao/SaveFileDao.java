package oracle.java.bmw.dao;


import java.util.List;

import oracle.java.bmw.model.SaveFiles;

public interface SaveFileDao {

	int uploadFileInsertDB(SaveFiles saveFile);

	List<SaveFiles> uploadFileRead(SaveFiles savefile);

	int uploadFileDelete(SaveFiles savefile);

	int uploadFileDeleteAll(SaveFiles savefile);

	List<SaveFiles> uploadFileListAllwithItem(SaveFiles savefile);

	int uploadFileUpdateDB(SaveFiles saveFile);

	SaveFiles memberPictureRead(SaveFiles savefile);

	int mempicDelete(int mainNo);


}
