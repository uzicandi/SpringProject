package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.ItemInfo;
import dao.SDBBoardDao;
import dao.SDBItemDao;
import dao.SaveFiles;
import dao.SaveFilesDao;

public class SDBmainFormAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			SDBItemDao id = SDBItemDao.getInstance();
			List<ItemInfo> list = id.list();
			
			SDBBoardDao bd = SDBBoardDao.getInstance();
			String boardCategory = "웹진";
			
			SaveFilesDao fd = SaveFilesDao.getInstance();
			
			List<Board> list2 = bd.mainWebzineList();

			List<SaveFiles> saveFiles = new ArrayList<SaveFiles>();
			
			for(int i = 0; i < list2.size(); i++) {
				SaveFiles saveFile = new SaveFiles();
				saveFile = fd.webList(list2.get(i).getBoardNo(), "BOARD");
				saveFiles.add(saveFile);
			}
			
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("boardCategory", boardCategory);
			request.setAttribute("savefiles", saveFiles);
		}catch(Exception e) {System.out.println(e.getMessage());	}
		return "mainForm2.jsp";
	}

}
