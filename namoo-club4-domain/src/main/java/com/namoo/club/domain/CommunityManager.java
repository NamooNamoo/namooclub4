package com.namoo.club.domain;


public class CommunityManager {

	private int communityNo;
	private SocialPerson manager;

	//--------------------------------------------------------------------------
	// constructor

	/**
	 *
	 * @param rolePerson
	 */
	public CommunityManager(int communityNo, SocialPerson manager){
		//
		this.communityNo = communityNo;
		this.manager = manager;
	}

	//--------------------------------------------------------------------------
	// getter/setter
	
	

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public SocialPerson getManager() {
		return manager;
	}

	public void setManager(SocialPerson manager) {
		this.manager = manager;
	}

}