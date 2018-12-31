package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
//import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileProcess {
	private static FileProcess instance;
	private FileProcess() {}
	public static FileProcess getInstance() {
		if (instance == null) { instance = new FileProcess(); }
		return instance;
	}

	// 최초 파일 업로드시 Temp폴더에 저장
	public MultipartRequest fileSaveToTemp(HttpServletRequest request, HttpServletResponse response) throws IOException	{
		String realPath = FileManager.getPygicalPath(request);
	
		System.out.println("realpath -> " + realPath);
		System.out.println("temp Path -> " + FileManager.getLogicalPath("temp"));
		String tempPath = realPath + FileManager.getLogicalPath("temp");
		System.out.println("tempPath -> " + tempPath);

		// enctype="multipart/form-data"는 form양식 데이터와 함께 받을때 사용 => MultipartRequest세트로 사용
		return new MultipartRequest(request, tempPath, FileManager.UPLOAD_MAX_SIZE, "utf-8", new DefaultFileRenamePolicy());
	}
	
	// 파일 등록시 처리시 대상 폴더에 저장
	public List<SaveFiles> fileMoveToPart(MultipartRequest multi, String ref_Table, int mainNo, int subNo) throws SQLException {
		List<SaveFiles> list = new ArrayList<>();
		String savedTempPath = FileManager.getLogicalPath("temp");
		String savedNewPath = FileManager.getLogicalPath(ref_Table);
		String tempFilePath = null;
		String newFilePath = null;
		Enumeration en = multi.getFileNames();
System.out.println("파일처리1");			
		while(en.hasMoreElements()) { // input 태그의 속성이 file인 태그의 name 속성값 파라미터이름
			try {
				String tagFileName = (String) en.nextElement(); // tag파일 이름
				System.out.println("tagFileName1 -> " + tagFileName);
	
				File tempFile = multi.getFile(tagFileName); // 전송된 파일 속성이 file인 태그의 name 속성값을 이용해 파일객체 생성
				System.out.println("tempFile1 -> " + tempFile);
	System.out.println("파일처리2");			
				if(tempFile != null){
					String originFileName = multi.getOriginalFileName(tagFileName);
					String savedFileName = multi.getFilesystemName(tagFileName);
					String fileExtend = null;
					String filePath = null;
					long fileSize = 0;
					boolean isUnix = true; 
//System.out.println(tempFile.getAbsolutePath());	
					// 원본파일 실제 폴더 경로와 이동할 곳 경로 설정 (Unix계열 대비)
					if(tempFile.getAbsolutePath().indexOf("\\") > -1) {
						isUnix = false;	// Windows path
						tempFilePath = tempFile.getAbsolutePath().replace(savedFileName, "");
						newFilePath = tempFile.getAbsolutePath().replace(savedTempPath.replace("/", "\\")+"\\"+savedFileName, savedNewPath.replace("/", "\\") + "\\");
					} else {
						tempFilePath = tempFile.getAbsolutePath().replace(savedFileName, "");
						newFilePath = tempFile.getAbsolutePath().replace(savedTempPath+"/"+savedFileName, savedNewPath + "/");
					}
					
					if(originFileName.lastIndexOf(".") > 0) {
						fileExtend = originFileName.substring(originFileName.lastIndexOf(".")).replace(".", ""); // 전송된 파일의 확장자
					}
					
					// 파일 이동 규칙
					// 1. 이동할 곳에 원본파일명과 동일한 파일명이 있는지 확인
					// 2. 동일한 파일명이 존재하면, 파일명 재정의 룰에 의하여 재정의
					// 3. 파일이동(파일 복사 & 파일 삭제)
	System.out.println("파일처리3");			
					String newFileName = FileManager.getInstance().uniqueFileName(isUnix, newFilePath, originFileName);
					File targetFile = new File(newFilePath+(isUnix?"/":"\\")+newFileName);
					System.out.println("targetFile3 -> " + targetFile);
					
					System.out.println("tempFile3 -> " + tempFile.getCanonicalPath());
					System.out.println("tempFile3 -> " + tempFile.getAbsolutePath());
				
					
					FileManager.getInstance().move(tempFile.getAbsolutePath(), targetFile.getAbsolutePath());
	System.out.println("파일처리4");			
					savedFileName = newFileName;
					filePath = savedNewPath;
					fileSize = targetFile.length();
					
					System.out.println("filePath -> " + filePath);
	
					// db처리하기 위해 바인딩
					SaveFiles saveFile = new SaveFiles(mainNo, subNo, ref_Table.toUpperCase(), originFileName, savedFileName, fileExtend, filePath, fileSize);
					list.add(saveFile);
	System.out.println("파일처리5");			
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				
			}

		}
		
		return list;
	}

	// 저장된Temp 파일을 삭제시
	public void tempFileDelete(MultipartRequest multi) {
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) { // input 태그의 속성이 file인 태그의 name 속성값 파라미터이름
			try {
				String tagFileName = (String) en.nextElement(); // tag파일 이름
				File tempFile = multi.getFile(tagFileName); // 전송된 파일 속성이 file인 태그의 name 속성값을 이용해 파일객체 생성
				if(tempFile != null){
					FileManager.getInstance().delete(tempFile.getAbsolutePath());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				
			}
		}
	}
	
	// 저장된 파일을 삭제시
	public int saveFileDelete(String pygicalBasePath, List<SaveFiles> saveFilesList) {
		int result = 0;
		try {
			for (SaveFiles saveFile : saveFilesList) {
				String filePath = String.format("%s%s/%s", pygicalBasePath, saveFile.getFilePath(), saveFile.getSavedFileName());
				FileManager.getInstance().delete(filePath);
				result++;
			}
			if (result == 0) result = 1;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} 
		return result;
	}
	
	public int saveFileDelete(String pygicalBasePath, SaveFiles saveFile) {
		int result = 0;
		try {
			String filePath = String.format("%s%s/%s", pygicalBasePath, saveFile.getFilePath(), saveFile.getSavedFileName());
			System.out.println("delete filename -> "+filePath);
			FileManager.getInstance().delete(filePath);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} 
		return result;
	}

}
