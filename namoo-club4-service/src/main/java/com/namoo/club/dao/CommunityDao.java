package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.Community;

public interface CommunityDao {
	List<Community> readAllCommunities();
	Community readCommunity(int communityNo);
	int createCommunity(Community community);
	void updateCommunity(Community community);
	void deleteCommunity(int communityNo);
}
