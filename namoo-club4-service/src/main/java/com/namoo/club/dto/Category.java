package com.namoo.club.dto;

public class Category {

	private int categoryNo;
	private String category;
	private int communityNo;

	public Category(int communityNo, String category) {
		//
		this.category = category;
		this.communityNo = communityNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
}
