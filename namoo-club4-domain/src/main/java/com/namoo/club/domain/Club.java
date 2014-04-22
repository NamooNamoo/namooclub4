package com.namoo.club.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Club {

	private int communityId;
	private int id;
	private String name;
	private String description;

	private List<ClubManager> managers;
	private List<ClubMember> members;

	private ClubManager mainManager;

	private String category;
	private Date date;

	//--------------------------------------------------------------------------
	// constructors

	/**
	 *
	 * @param clubName
	 * @param admin
	 */
	public Club(int id, String name, String description, String category, Date date){
		//
		this.id = id;
		this.name = name;
		this.description = description;
		this.members = new ArrayList<ClubMember>();
		this.managers = new ArrayList<ClubManager>();
		this.category = category;
		this.date = date;

		/*setManager(email);
		addMember(email);*/

	}

	public Club(int id, String name, String description) {
		//
		this.id = id;
		this.name = name;
		this.description = description;
		this.members = new ArrayList<ClubMember>();
		this.managers = new ArrayList<ClubManager>();

	}

	public Club(String name, String description) {
		//
		this.name = name;
		this.description = description;
		this.members = new ArrayList<ClubMember>();
		this.managers = new ArrayList<ClubManager>();

	}
	
	public Club() {
		
	}

	//--------------------------------------------------------------------------
	// getter/setter

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ClubManager> getManager() {
		return managers;
	}

	public ClubManager getMainManager() {
		for (ClubManager manager : managers){
			if (manager.getLevel() == 3){
				mainManager = manager;
				return mainManager;
			}
		}
		return null;
	}

	public List<ClubMember> getMembers() {
		return members;
	}

	//--------------------------------------------------------------------------
	// public methods

	public ClubMember findMember(String email) {
		//
		for (ClubMember member : members) {
			if(member.getMember().getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}


	public void addManager(SocialPerson person, int level){
		//
		ClubManager manager = new ClubManager(id, person, level);
		this.managers.add(manager);
	}



	public void addMember(SocialPerson person){
		//
		ClubMember member = new ClubMember(id, person);
		this.members.add(member);
	}


	public void removeMember(String email) {
		//
		ClubMember found = null;
		for (ClubMember member : members) {
			if (member.getMember().getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}

}
