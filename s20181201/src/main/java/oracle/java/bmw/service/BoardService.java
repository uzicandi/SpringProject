package oracle.java.bmw.service;

import java.util.List;

import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.SaveFiles;

public interface BoardService {

	List<Board> BoardList(Board board);
	int BoardTotal(Board board);
	Board BoardContent(int boardNo);
	int BoardDelete(int boardNo);
	int BoardWrite(Board board);
	void BoardHitPlus(int boardNo);
	int BoardUpdate(Board board);
	int BoardSaveFilesWrite(Board board);
	int BoardSaveFilesTempDelete();	//mainno 0인 거 지우는 함수
	int BoardSaveFilesUpdate(String boardCategory);
	int BoardCategoryTotal(String boardCategory);
	List<Board> WebzineList(Board board);
	List<SaveFiles> BoardSaveFilesList(SaveFiles sf);
	int BoardSaveFilesRealDelete(int boardNo);
	List<Board> EventList(Board board);
	List<SaveFiles> BoardSaveFilesEventList(SaveFiles sf);
	Board SelectEvent(int boardNo);
	List<SaveFiles> BoardSaveFilesDeleteList(int boardNo);
	int BoardContentClipInsert(Board board);
	int BoardContentClipChk(Board board);
	List<Board> BoardCategoryList(String boardCategory);
	
}
