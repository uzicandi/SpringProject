package oracle.java.bmw.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import oracle.java.bmw.model.Board;
import oracle.java.bmw.service.BoardService;

@Controller
@RequestMapping("/adm")
public class CkeditorFileUploadController {

	@Resource(name = "uploadServerPath")
	private String uploadServerPath;
	
	@Autowired
	private BoardService bs;
	
	@RequestMapping(value="fileupload.do", method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest req, HttpServletResponse resp, 
                 MultipartHttpServletRequest multiFile, Board board, Model model) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multiFile.getFile("upload");
		if(file != null){
				if(file.getContentType().toLowerCase().startsWith("image/")){
					try{
						String fileName = file.getName();
						byte[] bytes = file.getBytes();
						//String uploadPath = req.getServletContext().getRealPath("/img");
						//String uploadPath = req.getSession().getServletContext().getRealPath("/img");
						String uploadPath = uploadServerPath+"/board";
						String upllll = req.getServletPath();

						System.out.println(uploadPath);
						File uploadFile = new File(uploadPath);
						if(!uploadFile.exists()){
							uploadFile.mkdirs();
						}
						fileName = UUID.randomUUID().toString();
						System.out.println("fileName -> " + fileName);
						uploadPath = uploadPath + "/" + fileName;
						out = new FileOutputStream(new File(uploadPath));
                        out.write(bytes);
                        
                        printWriter = resp.getWriter();
                        resp.setContentType("text/html");
                        //String fileUrl = req.getContextPath() + "/img/" + fileName;
                        //String fileUrl = "http://localhost:8181/uploadImg/board" + "/" + fileName;
                        String fileUrl = "/uploadImg/board" + "/" + fileName;
                        System.out.println("fileUrl -> " + fileUrl);

                        board.setSavedFileName(fileName);
                        board.setFilePath(uploadPath);
                        System.out.println(board.getSavedFileName() + "     " + board.getFilePath());
                        bs.BoardSaveFilesWrite(board);
                        
                        // json 데이터로 등록
                        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
                        // 이런 형태로 리턴이 나가야함.
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);
                        
                        
                        printWriter.println(json);
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally{
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null){
                            printWriter.close();
                        }		
					}
				}
			}
		return null;
		}
	}
