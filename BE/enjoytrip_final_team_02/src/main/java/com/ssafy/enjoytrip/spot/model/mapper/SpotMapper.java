package com.ssafy.enjoytrip.spot.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.spot.Spot;

public interface SpotMapper {
	// 메인 페이지에서 추천 여행지
	List<Spot> getHotSpotList(String theme, int sido) throws SQLException;

	// 지역별 조회
	List<Spot> getSpotList(Map<String, Integer> map) throws SQLException;

	// 장소 Detail
	Spot getSpot(int spotid) throws SQLException;

	List<Spot> searchSpot(String keyword) throws SQLException;
	
	// 시도 리스트
	// List<Map<Integer,String>> getSidoList() throws SQLException;
	List<String[]> getSidoList() throws SQLException;

	// 구군 리스트
	List<String[]> getGugunList(int sidoid) throws SQLException;

	int countSpot() throws SQLException;

	Spot getThemeTopSpot(String theme) throws SQLException;
}
