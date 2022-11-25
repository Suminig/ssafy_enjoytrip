package com.ssafy.enjoytrip.hotplace.model;

public class Hotplace {
	public int hotplaceid;
	public int uid;
	public String userid;
	public String title;
	public String image;
	public String desc;
	
	public Hotplace() {
	}
	
	public Hotplace(int hotplaceid, int uid, String userid, String title, String image, String desc) {
		super();
		this.hotplaceid = hotplaceid;
		this.uid = uid;
		this.userid = userid;
		this.title = title;
		this.image = image;
		this.desc = desc;
	}

	public int getHotplaceid() {
		return hotplaceid;
	}

	public void setHotplaceid(int hotplaceid) {
		this.hotplaceid = hotplaceid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Hotplace [hotplaceid=" + hotplaceid + ", uid=" + uid + ", title=" + title + ", image=" + image
				+ ", desc=" + desc + "]";
	}
}
