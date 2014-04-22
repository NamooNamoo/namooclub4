package com.namoo.club.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Community {

	private int id;
	private String name;
	private String description;

	private CommunityManager manager;
	private List<CommunityMember> members;

	private Map<Integer, Club> clubs;

	private List<String> categories;

	private Date date;
	
	//--------------------------------------------------------------------------
	// constructors

	public Community(){
		//
		this.members = new ArrayList<CommunityMember>();
		this.clubs = new HashMap<Integer, Club>();
		this.categories = new ArrayList<String>();
	}
	/**
	 *
	 * @param communityName
	 * @param admin
	 */
	public Community(String communityName, String description){
		//
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.clubs = new HashMap<Integer, Club>();
		this.categories = new ArrayList<String>();

		/*setManager(email);
		addMember(email);*/
	}

	//--------------------------------------------------------------------------
	// getter/setter


	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
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

	public CommunityManager getManager() {
		return manager;
	}

	public List<CommunityMember> getMembers() {
		return members;
	}



	//--------------------------------------------------------------------------
	// public methods

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void addCategory(String category){
		this.categories.add(category);
	}

	public Date getDate() {
		return date;
	}

	public CommunityMember findMember(String email) {
		//
		for (CommunityMember member : members) {
			if(member.getMember().getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}

	/**
	 *
	 * @param rolePerson
	 */
	public void setManager(SocialPerson person){
		//
		CommunityManager manager = new CommunityManager(id, person);
		this.manager = manager;
	}

	/**
	 *
	 * @param rolePerson
	 */
	public void addMember(SocialPerson person){
		//
		CommunityMember member = new CommunityMember(id, person);
		this.members.add(member);
	}

	public void removeMember(String email) {
		//
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getMember().getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}
	//--------------------------------------------------------------------------------------------
	//Club

	public void addClub(Club club) {
		//
		this.clubs.put(club.getId(), club);
	}

	public Club findClub(String clubId){
		//
		return clubs.get(clubId);
	}

	public Map<Integer, Club> getAllClub(){
		//
		return clubs;
	}

	public void removeClub(String clubId) {
		//
		clubs.remove(clubId);
	}

}