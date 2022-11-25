package com.ssafy.enjoytrip.spot;

public class Spot {
	private int spotid;
	private String title;
	private String address;
	private String tel;
	private String image;
	private String hit;
	private String theme;
	private String contentid;
	private String lat;
	private String lang;
	private String sidoid;
	private String gugunid;
	private String desc;
	private String memo;

	public Spot() {

	}

	public int getSpotid() {
		return spotid;
	}

	public void setSpotid(int spotid) {
		this.spotid = spotid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSidoid() {
		return sidoid;
	}

	public void setSidoid(String sidoid) {
		this.sidoid = sidoid;
	}

	public String getGugunid() {
		return gugunid;
	}

	public void setGugunid(String gugunid) {
		this.gugunid = gugunid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Spot [spotid=" + spotid + ", title=" + title + ", address=" + address + ", tel=" + tel + ", image="
				+ image + ", hit=" + hit + ", theme=" + theme + ", contentid=" + contentid + ", lat=" + lat + ", lang="
				+ lang + ", sidoid=" + sidoid + ", gugunid=" + gugunid + ", desc=" + desc + "]";
	}
}