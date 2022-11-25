package com.ssafy.enjoytrip.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.hotplace.model.Hotplace;

public interface HotplaceMapper {
	List<Hotplace> listHotplace() throws SQLException;

	Hotplace getHotplace(int hotplaceid) throws SQLException;

	void writeHotplace(Hotplace hotplace) throws SQLException;

	void deleteHotplace(int hotplaceid) throws SQLException;

	void modifyHotplace(Map<String, String> map) throws SQLException;

	List<Hotplace> listRecentHotplace() throws SQLException;
}
