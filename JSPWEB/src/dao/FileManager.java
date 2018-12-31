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

public class FileManager {
	//-- Init일때 Properties에서 1회 가져와서 사용
	public final static int UPLOAD_MAX_SIZE = 5 * 1024 * 1024;
	private static FileManager instance;
	private FileManager() {}
	public static FileManager getInstance() {
		if (instance == null) { instance = new FileManager(); }
		return instance;
	}
	
	public static String getPygicalPath(HttpServletRequest request) {
		return request.getServletContext().getRealPath("/");
	}
	
	public static String getLogicalPath(String target) {
		//-- Init일때 Properties에서 1회 가져와서 사용
		String basePath = "/fileSave";
		String tempPath = "/temp";
		String boardPath = "/board";
		String iteminfoPath = "/iteminfo";
		String commentsPath = "/comments";
		String memberPath = "/member";
		
		switch(target.toLowerCase()) {
			case "board":
				target = basePath + boardPath;
				break;
			case "iteminfo":
				target = basePath + iteminfoPath;
				break;
			case "comments":
				target = basePath + commentsPath;
				break;
			case "member":
				target = basePath + memberPath;
				break;
			case "temp":
				target = basePath + tempPath;
				break;
			case "root":
				target = basePath;
				break;
		}		
		return target;
	}
	
	public String uniqueFileName(boolean isUnix, String directoryPath, String filename) {
		File targetFile = new File(directoryPath+(isUnix?"/":"\\")+filename);
		String file = filename;
		String ext = "";
		if(filename.lastIndexOf(".") > 0) {
			file = filename.substring(0, filename.lastIndexOf('.'));
			ext = filename.substring(filename.lastIndexOf(".")); // 전송된 파일의 확장자
		}

		String newFileName = filename; 
		int seq = 0;
		while (targetFile.exists()) {
			newFileName = String.format("%s_%d%s", file, ++seq, ext);
			targetFile = new File(directoryPath+(isUnix?"/":"\\")+newFileName);
		}

		return newFileName;
	}
	
	public void move(String originPath, String targetPath) {
		File originFile = new File(originPath);
		File targetFile = new File(targetPath);
		copy(originFile.getAbsolutePath(), targetFile.getAbsolutePath());
		delete(originFile.getAbsolutePath());
	}

	public void delete(String targetPath) {
		File file = new File(targetPath);
		if(file.exists()) {
			file.delete();
		}
	}

	public void copy(String source, String target) {

		// 복사 대상이 되는 파일 생성
		File sourceFile = new File(source);

		// 스트림, 채널 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		try {

			// 스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(target);

			// 채널 생성
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();

			// 채널을 통한 스트림 전송
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 자원 해제
			try {
				fcout.close();
			} catch (IOException ioe) {
			}
			try {
				fcin.close();
			} catch (IOException ioe) {
			}
			try {
				outputStream.close();
			} catch (IOException ioe) {
			}
			try {
				inputStream.close();
			} catch (IOException ioe) {
			}
		}
	}

}
