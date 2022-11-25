package com.ssafy.enjoytrip.board;

public class Board {
	private int boardid;
	private int uid;
	private String title;
	private String content;
	private String author;
	private String profileImg;
	private String date;
	private int hit;

	public Board() {
	}

	public Board(int boardid, int uid, String title, String content, String author, String profileImg, String date, int hit) {
		this.boardid = boardid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.author = author;
		this.date = date;
		this.hit = hit;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "Board [boardid=" + boardid + ", uid=" + uid + ", title=" + title + ", content=" + content + ", author="
				+ author + ", date=" + date + ", hit=" + hit + "]";
	}
}