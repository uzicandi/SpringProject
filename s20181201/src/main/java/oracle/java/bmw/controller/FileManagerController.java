package oracle.java.bmw.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oracle.java.bmw.model.SaveFiles;
import oracle.java.bmw.service.SaveFileService;

@Controller
public class FileManagerController {
	
	@Autowired
	private SaveFileService sfs;
	
	@RequestMapping(value="uploadFileDelete", method= {RequestMethod.POST, RequestMethod.GET}, produces="application/json;charset=utf-8")
	@ResponseBody
	public HashMap<String, Object> uploadFileDelete(SaveFiles savefile, Model model) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		System.out.println("uploadFileDelete\n" + savefile.toString());
		String filePath = "REVIEW";
		
		int result = sfs.uploadFileDelete(savefile, filePath);
		hm.put("result", result);
		
		return hm; 
	}
}
