package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.club.dao.DbCommonTest;
import com.namoo.club.domain.Community;
import com.namoo.club.domain.CommunityMember;

public class CommunityServiceTest extends DbCommonTest {

	private static final String DATASET_XML = "CommunityServiceTest_dataset.xml";
	
	@Autowired
	CommunityService communityService;
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindCommunity() {
		//
		Community community = communityService.findCommunity(1);
		
		assertEquals("커뮤니티1", community.getName());
		assertEquals("첫번째 커뮤니티", community.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testJoinAsMember() {
		//
		communityService.joinAsMember(1, "user4");
		CommunityMember member = communityService.findCommunityMember(1, "user4");
		
		assertNotNull(member);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllCommunities() {
		//
		int size = communityService.findAllCommunities().size();
		
		assertEquals(4, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindCommunityMember() {
		//
		CommunityMember member = communityService.findCommunityMember(1, "user1");
		
		assertNotNull(member);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllCommunityMember() {
		//
		int size = communityService.findAllCommunityMember(1).size();
		
		assertEquals(1, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRemoveCommunity() {
		//
		communityService.removeCommunity(1);
		
		assertNull(communityService.findCommunity(1));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindBelongCommunities() {
		int size = communityService.findBelongCommunities("user1").size();
		
		assertEquals(1, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindManagedCommnities() {
		int size = communityService.findManagedCommnities("user1").size();
		
		assertEquals(1, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testWithdrawalCommunity() {
		communityService.joinAsMember(1, "user2");
		CommunityMember member = communityService.findCommunityMember(1, "user2");
		assertNotNull(member);
		
		communityService.withdrawalCommunity(1, "user2");
		member = communityService.findCommunityMember(1, "user2");
		assertNull(member);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testModifyCommunity() {
		communityService.modifyCommunity(1, "테스트", "테스트 설명");
		Community community = communityService.findCommunity(1);
		
		assertEquals("테스트", community.getName());
		assertEquals("테스트 설명", community.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRegistCommunity() {
		List<String> categories = new ArrayList<String>();
		categories.add("카테고리1");
		categories.add("카테고리2");
		int communityId = communityService.registCommunity("커뮤니티5", "다섯번째 커뮤니티", "user1", categories);
		
		Community community = communityService.findCommunity(communityId);
		
		assertEquals("커뮤니티5", community.getName());
		assertEquals("다섯번째 커뮤니티", community.getDescription());
		assertEquals("user1", community.getManager().getManager().getEmail());
	}
}