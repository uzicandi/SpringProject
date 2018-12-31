package service;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemInfo;
import dao.SDBItemDao;
import dao.SaveFiles;
import dao.SaveFilesDao;

public class SDBBrandList1Act implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SDBItemDao sd = SDBItemDao.getInstance();
		try {
			String brand = request.getParameter("brand");

			int totCnt = sd.getTotalCnt1(brand);
			List<ItemInfo> list = sd.list1(brand);

			request.setAttribute("brand", brand);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("list", list);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "SDBBrandList1.jsp";
	}

}
