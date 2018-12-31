package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.SDBBoardDao;
import dao.SaveFiles;
import dao.SaveFilesDao;

public class SDBWebzineMainAct implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SDBBoardDao bd = SDBBoardDao.getInstance();
		String boardCategory = "웹진";
		
		SaveFilesDao fd = SaveFilesDao.getInstance();
		int count = 1;
		
		System.out.println("boardCategory -> " + boardCategory);
		try {
			int totCnt = bd.getTotalCnt(boardCategory);

			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum); // 초기화시 페이지 넘버가 1
			int pageSize = 10, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1; // 초기화시 스타트로우도 1
			int endRow = startRow + pageSize - 1;
			int startNum = totCnt - startRow + 1;

			// 초기화시 1 10
			List<Board> list = bd.list(startRow, endRow, boardCategory);
			System.out.println("list 크기 -> " + list.size());
			
			List<SaveFiles> saveFiles = new ArrayList<SaveFiles>();
			for(int i = 0; i < list.size(); i++) {
				SaveFiles saveFile = new SaveFiles();
				saveFile = fd.webList(list.get(i).getBoardNo(), "BOARD");
				saveFiles.add(saveFile);
				System.out.println("savefile의 메인넘버 -> " + saveFiles.get(i).getMainNo());
				System.out.println("savefile의 파일패스 -> " + saveFiles.get(i).getFilePath());
				System.out.println("savefile의 세이브드네임 -> " + saveFiles.get(i).getSavedFileName());
			}
						
			// 38/10 , pageCnt는 page 갯수
			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			// 초기화시 1

			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			// 초기화시 10

			int endPage = startPage + blockSize - 1;
			// 초기화시 4
			if (endPage > pageCnt)
				endPage = pageCnt;

			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("boardCategory", boardCategory);
			request.setAttribute("savefiles", saveFiles);

			System.out.println("------------------------------------------------");
			System.out.println("startNum-->" + startNum);
			System.out.println("totCnt-->" + totCnt);
			System.out.println("currentPage-->" + currentPage);
			System.out.println("blockSize-->" + blockSize);
			System.out.println("pageSize-->" + pageSize);
			System.out.println("pageCnt-->" + pageCnt);
			System.out.println("startPage-->" + startPage);
			System.out.println("endPage-->" + endPage);
			System.out.println("boardCategory-->" + boardCategory);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "KJWwebzineMain.jsp";
		/*return "SDBwebzineContent.jsp";*/
	}

}
