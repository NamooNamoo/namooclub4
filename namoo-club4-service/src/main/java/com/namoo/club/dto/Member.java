package com.namoo.club.dto;

public class Member {

	private String email;
	private int groupNo;
	private int groupType;
	private int level;
	
	public Member(String email, int groupNo, int groupType, int level) {
		//
		this.email = email;
		this.groupNo = groupNo;
		this.groupType = groupType;
		this.level = level;
	}
	
	public Member(String email, int groupNo, int groupType) {
		//
		this.email = email;
		this.groupNo = groupNo;
		this.groupType = groupType;
	}
	
	public Member(int groupNo, int groupType) {
		//
		this.groupNo = groupNo;
		this.groupType = groupType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
