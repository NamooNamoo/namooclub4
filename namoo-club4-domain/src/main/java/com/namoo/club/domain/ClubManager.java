package com.namoo.club.domain;


public class ClubManager {

	private int clubNo;
	private SocialPerson manager;
	private int level;

	//--------------------------------------------------------------------------
	// constructor

	/**
	 *
	 * @param rolePerson
	 */
	public ClubManager(int clubNo, SocialPerson manager, int level){
		//
		this.clubNo = clubNo;
		this.manager = manager;
		this.level = level;
	}

	//--------------------------------------------------------------------------
	// getter/setter
	
	public int getClubNo() {
		return clubNo;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SocialPerson getManager() {
		return manager;
	}

	public void setManager(SocialPerson manager) {
		this.manager = manager;
	}
	
}
