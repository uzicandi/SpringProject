package oracle.java.bmw.service;


import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import oracle.java.bmw.model.SaveFiles;

public interface SaveFileService {

	int uploadFileWithServer(SaveFiles saveFiles, String filePath, String formName, MultipartHttpServletRequest multipartRequest);
	int uploadFileWithLocal(SaveFiles saveFiles, String formName, MultipartHttpServletRequest multipartRequest);
	List<SaveFiles> uploadFileRead(SaveFiles savefile);
	int uploadFileDelete(SaveFiles savefile, String filePath);
	int uploadFileDeleteAll(SaveFiles savefile, String filePath);
	List<SaveFiles> uploadFileListAllwithItem(SaveFiles savefile);
	int uploadFileWithServerOfMember(SaveFiles saveFiles, String filePath, String formName,
			MultipartHttpServletRequest multipartRequest);
	SaveFiles memberPictureRead(SaveFiles savefile);
	int mempicDelete(int mainNo);
	SaveFiles getMemberPicture(String nickName);

}
