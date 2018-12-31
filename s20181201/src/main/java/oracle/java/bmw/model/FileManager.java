package oracle.java.bmw.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileManager {
	private String serverPath;	// common
	private String filePath; // common
	private String formName;
	private MultipartHttpServletRequest multipartRequest; // upload
	private Map<String, List<String>> fileNames;
	

	public FileManager() {

	}

	// 파일 업로드를 위한 생성자
	public FileManager(String serverPath, String filePath, String formName, MultipartHttpServletRequest multipartRequest) {
		this.serverPath = serverPath;
		this.formName = formName;
		this.filePath = filePath;
		this.multipartRequest = multipartRequest;
		fileNames = new HashMap<String, List<String>>();
	}

	// 임시 저장을 위한 생성자
	public FileManager(String formName, MultipartHttpServletRequest multipartRequest) {
		this.formName = formName;
		this.filePath = multipartRequest.getSession().getServletContext().getRealPath("/resource/uploadTemp/");
		this.multipartRequest = multipartRequest;
		fileNames = new HashMap<String, List<String>>();
	}
	
	// 파일 삭제를 위한 생성자
	public FileManager(String serverPath, String filePath) {
		this.serverPath = serverPath;
		this.filePath = filePath;
	}


	// ***** Getter & Setter ******//
	// Real 파일업로드 Setter
	public FileManager setUpload(String serverPath, String filePath, String formName, MultipartHttpServletRequest multipartRequest) {
		this.serverPath = serverPath;
		this.formName = formName;
		this.filePath = filePath;
		this.multipartRequest = multipartRequest;
		fileNames = new HashMap<String, List<String>>();
		return this;
	}

	
	// Temp 파일업로드 Setter
	public FileManager setUpload(String formName, MultipartHttpServletRequest multipartRequest) {
		this.formName = formName;
		this.filePath = multipartRequest.getSession().getServletContext().getRealPath("/resource/uploadTemp/");
		this.multipartRequest = multipartRequest;
		fileNames = new HashMap<String, List<String>>();
		return this;
	}
	
	// 파일업로드 Method
	public List<SaveFiles> fileUpload(SaveFiles saveFiles, MultipartHttpServletRequest multipartRequest) {
		List<MultipartFile> mf = multipartRequest.getFiles(formName);
//		Map<String, List<String>> fileNames = new HashMap<String, List<String>>();
		List<String> originNames = new ArrayList<String>();
		List<String> savedNames = new ArrayList<String>();
		List<SaveFiles> saveFileList = new ArrayList<SaveFiles>();
		StringBuffer sb = null;
		
		for(int i=0; i<mf.size(); i++) {
			System.out.println("==================== "+ mf.size() +"중 반복실행 " + i + "번째 ==================================");
			if(mf.get(i).getOriginalFilename().equals("")) {
				System.out.println(i + "번째 첨부파일 없음\n");
			} else {
				sb = new StringBuffer();
				String originFileName = mf.get(i).getOriginalFilename();
				String fileExtend = originFileName.substring(originFileName.lastIndexOf("."));
//				originFileName = originFileName.substring(0, originFileName.lastIndexOf("."));
				String savedFileName = sb.append(UUID.randomUUID().toString())
									     .append("_")
									     .append(originFileName).toString();
				
				String targetDirPath = serverPath + filePath;
				
				try {
					// 경로에 디렉토리 없으면 생성
					File targetDir = new File(targetDirPath);
					if(!targetDir.exists()) {
						targetDir.mkdirs();
						System.out.println("업로드용 디렉토리 생성 : " + targetDir.getPath());
					}
					// 파일 저장
					File fileFull = new File(targetDirPath, savedFileName);
//					mf.get(i).transferTo(targetDir);
					FileCopyUtils.copy(mf.get(i).getBytes(), fileFull);
					originNames.add(originFileName);
					savedNames.add(savedFileName);
					
					// DB로 넘길 값 저장
					saveFiles.setOriginFileName(originFileName);
					saveFiles.setSavedFileName(savedFileName);
					saveFiles.setFileExtend(fileExtend);
					saveFiles.setFilePath("/uploadImg" + filePath + "/" + savedFileName);
					saveFiles.setFileSize(mf.get(i).getSize());
					
					System.out.println("saveFiles 0" + ++i + " .toString() -------\n" + saveFiles.toString());
					saveFileList.add(saveFiles);
					
				} catch (Exception e) {
					System.out.println("fileUpload error -> " + e.getMessage());
				}
			}
		}
		fileNames.put("originNames", originNames);
		fileNames.put("savedNames", savedNames);
//		files.setFileNames(fileNames);
		
		return saveFileList;
	}
	
	public void fileRealDelete(SaveFiles saveFile) {
		String fileFullPath = serverPath + filePath + "/" + saveFile.getSavedFileName();
		System.out.println("Delete file -> " + fileFullPath);
		try {
			File del_file = new File(fileFullPath);
			del_file.delete();
		} catch (Exception e) {
			System.out.println("fileRealDelete error -> " + e.getMessage());
		}
	}
	
	
	
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public MultipartHttpServletRequest getMultipartRequest() {
		return multipartRequest;
	}

	public void setMultipartRequest(MultipartHttpServletRequest multipartRequest) {
		this.multipartRequest = multipartRequest;
	}

	public Map<String, List<String>> getFileNames() {
		return fileNames;
	}

	public void setFileNames(Map<String, List<String>> fileNames) {
		this.fileNames = fileNames;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

}
