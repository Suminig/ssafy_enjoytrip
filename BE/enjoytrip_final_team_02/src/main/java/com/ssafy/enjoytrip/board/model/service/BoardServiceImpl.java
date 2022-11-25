package com.ssafy.enjoytrip.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.Board;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import com.ssafy.enjoytrip.spot.Spot;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<Board> listBoard(Map<String, String> map) throws Exception {
		return boardMapper.listBoard(map);
	}

	@Override
	public void writeBoard(Map<String, String> map) throws Exception {
		boardMapper.writeBoard(map);
	}

	@Override
	public void writeScheduleSpot(Map<String, String> map) throws Exception {
		boardMapper.writeScheduleSpot(map);
	}
	
	@Override
	public Board getBoard(int boardid) throws Exception {
		return boardMapper.getBoard(boardid);
	}

	@Override
	public List<Board> getTopBoards() throws Exception {
		return boardMapper.getTopBoards();
	}

	@Override
	public List<Map<String, String>> getTopBoardSpots(int boardid) throws Exception {
		return boardMapper.getTopBoardSpots(boardid);
	}

	@Override
	public void updateBoardHit(int boardid) throws Exception {
		boardMapper.updateBoardHit(boardid);
	}

	@Override
	public void modifyBoard(Map<String, String> map) throws Exception {
		boardMapper.modifyBoard(map);
	}

	@Override
	public void modifySpots(Map<String, String> map) throws Exception {
		boardMapper.modifySpots(map);
	}

	@Override
	public void deleteBoard(int boardid) throws Exception {
		boardMapper.deleteBoard(boardid);
	}
	
	@Override
	public void deleteSpots(int boardid) throws Exception {
		boardMapper.deleteSpots(boardid);
	}
	
	@Override
	public int countBoard() throws Exception {
		return 0;
	}

	@Override
	public int getLastBoardid(int uid) throws Exception {
		return boardMapper.getLastBoardid(uid);
	}

	@Override
	public List<Map<String, String>> getSpots(int boardid) throws Exception {
		return boardMapper.getSpots(boardid);
	}


}
