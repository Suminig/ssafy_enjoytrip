package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.hotplace.model.Hotplace;

public interface HotplaceService {
	List<Hotplace> listHotplace() throws Exception;

	Hotplace getHotplace(int hotplaceid) throws Exception;
	
	void writeHotplace(Hotplace hotplace) throws Exception;
	
	void deleteHotplace(int hotplaceid) throws Exception;
	
	void modifyHotplace(Map<String, String> map) throws Exception;
	
	List<Hotplace> listRecentHotplace() throws Exception;

}
