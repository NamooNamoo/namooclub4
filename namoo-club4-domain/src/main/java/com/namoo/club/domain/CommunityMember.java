package com.namoo.club.domain;


public class CommunityMember {

	private int communityNo;
	private SocialPerson member;

	//--------------------------------------------------------------------------
	// constructor

	/**
	 *
	 * @param rolePerson
	 */
	public CommunityMember(int communityNo, SocialPerson member){
		//
		this.communityNo = communityNo;
		this.member = member;
	}

	//--------------------------------------------------------------------------
	// getter/setter

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public SocialPerson getMember() {
		return member;
	}

	public void setMember(SocialPerson member) {
		this.member = member;
	}

}