package com.ssafy.enjoytrip.user;

public class User {
	private int uid;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private int isadmin;
	private String token;
	private String image;

	public User() {
	}

	public User(int uid, String id, String pwd, String name, String email, int isadmin, String token, String image) {
		this.uid = uid;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.isadmin = isadmin;
		this.token = token;
		this.image = image;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", isadmin="
				+ isadmin + "]";
	}
}