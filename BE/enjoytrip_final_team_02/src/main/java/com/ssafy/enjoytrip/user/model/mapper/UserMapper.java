package com.ssafy.enjoytrip.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoytrip.user.User;

public interface UserMapper {
	User login(User user) throws SQLException;

	User userInfo(String userid) throws SQLException;

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userid) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;

	int idcheck(String id) throws SQLException;

	void addUser(Map<String, String> map) throws SQLException;

	void updateUser(User user) throws SQLException;

	void removeUser(int uid) throws SQLException;

	String getIdByEmail(String email) throws SQLException;

	User getUidForPw(String id) throws SQLException;

	void changeTempPw(Map<String, String> map) throws SQLException;
}