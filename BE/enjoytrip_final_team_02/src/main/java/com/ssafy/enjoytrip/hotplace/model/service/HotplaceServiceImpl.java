package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.model.Hotplace;
import com.ssafy.enjoytrip.hotplace.model.mapper.HotplaceMapper;

@Service
public class HotplaceServiceImpl implements HotplaceService {

	private HotplaceMapper hotplaceMapper;

	@Autowired
	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		this.hotplaceMapper = hotplaceMapper;
	}

	@Override
	public List<Hotplace> listHotplace() throws Exception {
		return hotplaceMapper.listHotplace();
	}

	@Override
	public Hotplace getHotplace(int hotplaceid) throws Exception {
		return hotplaceMapper.getHotplace(hotplaceid);
	}

	@Override
	public void writeHotplace(Hotplace hotplace) throws Exception {
		hotplaceMapper.writeHotplace(hotplace);
	}

	@Override
	public void deleteHotplace(int hotplaceid) throws Exception {
		hotplaceMapper.deleteHotplace(hotplaceid);
	}

	@Override
	public void modifyHotplace(Map<String, String> map) throws Exception {
		hotplaceMapper.modifyHotplace(map);
	}

	@Override
	public List<Hotplace> listRecentHotplace() throws Exception {
		return hotplaceMapper.listRecentHotplace();
	}

}
