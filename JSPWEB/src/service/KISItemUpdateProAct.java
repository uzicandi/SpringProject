package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import dao.Category;
import dao.FileManager;
import dao.FileProcess;
import dao.ItemInfo;
import dao.KISItemDao;

public class KISItemUpdateProAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	System.out.println("Item Update Pro 시작...");
			
			MultipartRequest multi = FileProcess.getInstance().fileSaveToTemp(request, response);
			
			String pageNum = multi.getParameter("pageNum");
			
	System.out.println("-------------TEST---------------------");
			String savefiles[] = new String[3];
			savefiles[0] = multi.getFilesystemName("saveFiles1");
	System.out.println("saveFiles1 --> " + savefiles[0]);
			if(savefiles[0] == null || savefiles[0] == "") {
				savefiles[0] = null;
			}
	System.out.println("saveFiles1 --> " + savefiles[0]);
			savefiles[1] = multi.getFilesystemName("saveFiles2");
	System.out.println("savefile2 --> " + savefiles[1]);
			if(savefiles[1] == null || savefiles[1] == "") {
				savefiles[1] = null;
			}
	System.out.println("savefile2 --> " + savefiles[1]);
			savefiles[2] = multi.getFilesystemName("saveFiles3");
			if(savefiles[2] == null || savefiles[2] == "") {
				savefiles[2] = null;
			}
			
			int itemNo = Integer.parseInt(multi.getParameter("itemNo"));
			String strCate = multi.getParameter("category2");
			if (strCate == null || strCate == "")
				strCate = request.getParameter("categoryNo");
			int category2 = Integer.parseInt(strCate);
			String brand = multi.getParameter("brand");
			String name = multi.getParameter("name");
			String info = multi.getParameter("info");
			String strPrice = multi.getParameter("price");
			if (strPrice == null || strPrice == "")
				strPrice = "0";
			int price = Integer.parseInt(strPrice);
			String isPublic = multi.getParameter("isPublic");
			String memberId = multi.getParameter("memberId");

			KISItemDao kd = KISItemDao.getInstance();
			// 카테고리번호
			List<Category> list2 = kd.list();
			request.setAttribute("list2", list2);
			request.setAttribute("categoryNo", category2);

			ItemInfo item = new ItemInfo();
			item.setItemNo(itemNo);
			item.setCategoryNo(category2);
			item.setBrand(brand);
			item.setName(name);
			item.setInfo(info);
			item.setPrice(price);
			item.setIsPublic(isPublic);
			item.setMemberId(memberId);

//			int result = kd.update(item);
			String phsyicalPath = FileManager.getPygicalPath(request);
			int result = kd.updateIteminfo(multi, item, savefiles, phsyicalPath);

			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("itemNo", itemNo);			
			
			// IngtList 테이블에 성분번호 배열값으로 받아서 저장
			String[] receiveValue = multi.getParameter("receiveValue").split(",");
			String strArr = receiveValue.toString();
			if (strArr == null || strArr == "") {
				int result3 = 1; // 1일때 성공
				request.setAttribute("result3", result3);
			} else {
				int result2 = kd.mappingDelete(itemNo);
				int result3 = 0;
				for (int i = 0; i < receiveValue.length; i++) {
					int ingredientNo = Integer.parseInt(receiveValue[i]);
					// 성분 체크한 값이 있다면 디비에 등록된 값 삭제 후 저장
					result3 = kd.mappingInsert(itemNo, ingredientNo);
				}
				if (result2 > 0 && result3 > 0) {
					result3 = 1;
				}
				request.setAttribute("result3", result3);
				
			}
			
		} catch (Exception e) {
			System.out.println("제품 UpdateProAction error -> " + e.getMessage());
		}
		return "KISItemUpdatePro.jsp";
	}

}