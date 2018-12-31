package oracle.java.bmw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.BoardDao;
import oracle.java.bmw.dao.ClipDao;
import oracle.java.bmw.model.Board;
import oracle.java.bmw.model.SaveFiles;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao sbd;
	@Autowired
	private ClipDao cd;
	
	@Override
	public List<Board> BoardList(Board board) {
		return sbd.BoardList(board);
	}

	@Override
	public int BoardTotal(Board board) {
		return sbd.BoardTotal(board);
	}

	@Override
	public Board BoardContent(int boardNo) {
		return sbd.BoardContent(boardNo);
	}

	@Override
	public int BoardDelete(int boardNo) {
		return sbd.BoardDelete(boardNo);
	}

	@Override
	public int BoardWrite(Board board) {
		return sbd.BoardWrite(board);
	}

	@Override
	public void BoardHitPlus(int boardNo) {
		sbd.BoardHitPlus(boardNo);		
	}

	@Override
	public int BoardUpdate(Board board) {
		return sbd.BoardUpdate(board);
	}

	@Override
	public int BoardSaveFilesWrite(Board board) {
		return sbd.BoardSaveFilesWrite(board);
	}

	@Override
	public int BoardSaveFilesTempDelete() {
		return sbd.BoardSaveFilesTempDelete();
	}

	@Override
	public int BoardSaveFilesUpdate(String boardCategory) {
		return sbd.BoardSaveFilesUpdate(boardCategory);
	}

	@Override
	public int BoardCategoryTotal(String boardCategory) {
		return sbd.BoardCategoryTotal(boardCategory);
	}

	@Override
	public List<Board> WebzineList(Board board) {
		return sbd.WebzineList(board);
	}

	@Override
	public List<SaveFiles> BoardSaveFilesList(SaveFiles sf) {
		return sbd.BoardSaveFilesList(sf);
	}

	@Override
	public int BoardSaveFilesRealDelete(int boardNo) {
		return sbd.BoardSaveFilesRealDelete(boardNo);
	}

	@Override
	public List<Board> EventList(Board board) {
		return sbd.EventList(board);
	}

	@Override
	public List<SaveFiles> BoardSaveFilesEventList(SaveFiles sf) {
		return sbd.BoardSaveFilesEventList(sf);
	}

	@Override
	public Board SelectEvent(int boardNo) {
		return sbd.SelectEvent(boardNo);
	}

	@Override
	public List<SaveFiles> BoardSaveFilesDeleteList(int boardNo) {
		return sbd.BoardSaveFilesDeleteList(boardNo);
	}

	@Override
	public int BoardContentClipInsert(Board board) {
		return cd.BoardContentClipInsert(board);
	}

	@Override
	public int BoardContentClipChk(Board board) {
		return cd.BoardContentClipChk(board);
	}

	@Override
	public List<Board> BoardCategoryList(String boardCategory) {
		return sbd.BoardCategoryList(boardCategory);
	}
	
}
