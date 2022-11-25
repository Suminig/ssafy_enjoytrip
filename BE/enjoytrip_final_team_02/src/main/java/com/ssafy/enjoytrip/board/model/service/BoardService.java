package com.ssafy.enjoytrip.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.Board;
import com.ssafy.enjoytrip.spot.Spot;

public interface BoardService {
	List<Board> listBoard(Map<String, String> map) throws Exception;

	void writeBoard(Map<String, String> map) throws Exception;
	
	void writeScheduleSpot(Map<String, String> map) throws Exception;

	Board getBoard(int boardid) throws Exception;

	List<Board> getTopBoards() throws Exception;

	List<Map<String, String>> getTopBoardSpots(int boardid) throws Exception;
	
	void updateBoardHit(int boardid) throws Exception;

	void modifyBoard(Map<String, String> map) throws Exception;

	void modifySpots(Map<String, String> map) throws Exception;
	
	void deleteBoard(int boardid) throws Exception;
	
	void deleteSpots(int boardid) throws Exception;

	int countBoard() throws Exception;
	
	int getLastBoardid(int uid) throws Exception;
	
	List<Map<String, String>> getSpots(int boardid) throws Exception;

}
