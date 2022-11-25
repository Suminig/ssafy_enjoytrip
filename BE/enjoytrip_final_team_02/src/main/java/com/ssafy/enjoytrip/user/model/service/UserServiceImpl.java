package com.ssafy.enjoytrip.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.User;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User login(User user) throws Exception {
		return userMapper.login(user);
	}


	@Override
	public User userInfo(String userid) throws Exception {
		return userMapper.userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return userMapper.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

	
	@Override
	public int idcheck(String id) throws Exception {
		return userMapper.idcheck(id);
	}

	@Override
	public void addUser(Map<String, String> map) throws Exception {
		userMapper.addUser(map);
	}

	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateUser(user);
	}

	@Override
	public void removeUser(int uid) throws Exception {
		userMapper.removeUser(uid);
	}

	@Override
	public String getIdByEmail(String email) throws Exception {
		return userMapper.getIdByEmail(email);
	}

	@Override
	public User getUidForPw(String id) throws Exception {
		return userMapper.getUidForPw(id);
	}

	@Override
	public void changeTempPw(Map<String, String> map) throws Exception {
		userMapper.changeTempPw(map);
	}
}
