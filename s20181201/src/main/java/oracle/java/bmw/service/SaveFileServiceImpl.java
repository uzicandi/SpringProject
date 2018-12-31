package oracle.java.bmw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import oracle.java.bmw.dao.SaveFileDao;
import oracle.java.bmw.model.FileManager;
import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.SaveFiles;

@Service
public class SaveFileServiceImpl implements SaveFileService{

	@Autowired
	private SaveFileDao sfd;
	@Autowired
	private MemberService ms;
	
	@Resource(name = "uploadServerPath")
	private String uploadServerPath;
	
	

	@Override
	public int uploadFileWithServer(SaveFiles saveFiles, String filePath, String formName, MultipartHttpServletRequest multipartRequest) {
		FileManager files = new FileManager(uploadServerPath, filePath, formName, multipartRequest);
		System.out.println("ServerRealPath --> " + uploadServerPath);
		System.out.println("ServerLogicalPath --> /uploadImg" + files.getFilePath());
		
		List<SaveFiles> saveFileList = files.fileUpload(saveFiles, multipartRequest);
		System.out.println("uploadFileNames -> " + files.getFileNames().toString());
		int result = 0;
		for(SaveFiles saveFile : saveFileList) {
			System.out.println("\n----------확인용-------------");
			System.out.println(saveFile.toString());
			result = sfd.uploadFileInsertDB(saveFile);
		}

		return result;
	}
	
	@Override
	public int uploadFileWithLocal(SaveFiles saveFiles, String formName, MultipartHttpServletRequest multipartRequest) {
		FileManager files = new FileManager(formName, multipartRequest);
		System.out.println("LocalRealPath --> " + files.getFilePath());
		
		List<SaveFiles> saveFileList = files.fileUpload(saveFiles, multipartRequest);
		System.out.println("uploadFileNames -> " + files.getFileNames().toString());
		int result = 0;
		for(SaveFiles saveFile : saveFileList) {
			System.out.println("\n----------확인용-------------");
			System.out.println(saveFile.toString());
			result = sfd.uploadFileInsertDB(saveFile);
		}

		return result;
	}

	@Override
	public List<SaveFiles> uploadFileRead(SaveFiles savefile) {
		return sfd.uploadFileRead(savefile);
	}

	@Override
	public int uploadFileDelete(SaveFiles savefile, String filePath) {
		FileManager file = new FileManager(uploadServerPath, filePath);

		file.fileRealDelete(savefile);

		return sfd.uploadFileDelete(savefile);
	}

	@Override
	public int uploadFileDeleteAll(SaveFiles savefile, String filePath) {
		FileManager file = new FileManager(uploadServerPath, filePath);
		List<SaveFiles> saveFileList = sfd.uploadFileRead(savefile);
		for(SaveFiles saveFile : saveFileList) {
			file.fileRealDelete(saveFile);
		}
		
		return sfd.uploadFileDeleteAll(savefile);
	}

	@Override
	public List<SaveFiles> uploadFileListAllwithItem(SaveFiles savefile) {
		return sfd.uploadFileListAllwithItem(savefile);
	}
	
	// 멤버
	@Override
	public int uploadFileWithServerOfMember(SaveFiles saveFiles, String filePath, String formName, MultipartHttpServletRequest multipartRequest) {
		FileManager files = new FileManager(uploadServerPath, filePath, formName, multipartRequest);
		System.out.println("ServerRealPath --> " + uploadServerPath);
		System.out.println("ServerLogicalPath --> /uploadImg" + files.getFilePath());
		
		List<SaveFiles> saveFileList = files.fileUpload(saveFiles, multipartRequest);
		System.out.println("uploadFileNames -> " + files.getFileNames().toString());
		int result = 0;
		for(SaveFiles saveFile : saveFileList) {
			System.out.println("\n----------확인용-------------");
			System.out.println(saveFile.toString());
			result = sfd.uploadFileUpdateDB(saveFile);
		}

		return result;
	}
	@Override
	public SaveFiles memberPictureRead(SaveFiles savefile) {
		return sfd.memberPictureRead(savefile);
	}
	@Override
	public int mempicDelete(int mainNo) {
		return sfd.mempicDelete(mainNo);
	}
	
	@Override
	public SaveFiles getMemberPicture(String nickName) {
		SaveFiles savefile = new SaveFiles();
		System.out.println("nickName ---> " + nickName);
		Member member = ms.nickNameToMemberId(nickName);
		System.out.println("여기됨??");
		String memberId = member.getMemberId();
		System.out.println("savefile memberId -> " + memberId);
		if(memberId == null || memberId.equals("")) {
			savefile.setFilePath("0");
			return savefile;
		} else {
			System.out.println("memberId ---> " + memberId);
			savefile.setMainNo(ms.selectSeq(memberId));
			System.out.println("savefille.getMainNO() ---> " + savefile.getMainNo());
			return memberPictureRead(savefile);
		}
	}
	
	
}
