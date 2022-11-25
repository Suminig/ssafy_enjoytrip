package com.ssafy.enjoytrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.Board;
import com.ssafy.enjoytrip.spot.Spot;

public interface BoardMapper {
	List<Board> listBoard(Map<String, String> map) throws SQLException;

	void writeBoard(Map<String, String> map) throws SQLException;
	
	void writeScheduleSpot(Map<String, String> map) throws SQLException;

	Board getBoard(int boardid) throws SQLException;
	
	List<Board> getTopBoards() throws SQLException;

	List<Map<String, String>> getTopBoardSpots(int boardid) throws SQLException;

	List<Map<String, String>> getSpots(int boardid) throws SQLException;
	
	void updateBoardHit(int boardid) throws SQLException;

	void modifyBoard(Map<String, String> map) throws SQLException;

	void modifySpots(Map<String, String> map) throws SQLException;

	void deleteBoard(int boardid) throws SQLException;
	
	void deleteSpots(int boardid) throws SQLException;

	int countBoard() throws SQLException;

	int getLastBoardid(int uid) throws SQLException;

}
