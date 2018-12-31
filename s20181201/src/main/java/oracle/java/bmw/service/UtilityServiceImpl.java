package oracle.java.bmw.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import oracle.java.bmw.model.Cart;

@Service
public class UtilityServiceImpl {
	
	
	public String returnSession(String key, HttpSession session) {
		
		HashMap<String, String> hm = (HashMap<String, String>)session.getAttribute("ssMap");
		
		return hm.get(key);
	}
	
	public void pathFix(String key, HttpSession session) {
			
		HashMap<String, String> hm = (HashMap<String, String>)session.getAttribute("ssMap");
		hm.put("filePath", key);
	}
	
	public void cartFix(List<Cart> list, HttpSession session) {
		HashMap<Object, Object> hm = (HashMap<Object, Object>)session.getAttribute("cartMap");
		hm.put("list", list); 
		hm.put("count", list.size());
		
	}
}
