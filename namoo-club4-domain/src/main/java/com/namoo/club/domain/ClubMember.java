package com.namoo.club.domain;


public class ClubMember {

	private int clubNo;
	private SocialPerson member;

	//--------------------------------------------------------------------------
	// constructor

	/**
	 *
	 * @param rolePerson
	 */
	public ClubMember(int clubNo, SocialPerson member){
		//
		this.clubNo = clubNo;
		this.member = member;
	}

	//--------------------------------------------------------------------------
	// getter/setter

	public int getClubNo() {
		return clubNo;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}

	public SocialPerson getMember() {
		return member;
	}

	public void setMember(SocialPerson member) {
		this.member = member;
	}
	
}