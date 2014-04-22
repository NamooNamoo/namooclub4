package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.club.dao.DbCommonTest;
import com.namoo.club.domain.Club;
import com.namoo.club.domain.ClubManager;
import com.namoo.club.domain.ClubMember;

public class ClubServiceTest extends DbCommonTest{

	private static final String DATASET_XML = "ClubServiceTest_dataset.xml";
	
	@Autowired
	ClubService clubService;

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRegistClub() {
		//
		Club club = new Club("테스트", "테스트 설명");
		int clubId = clubService.registClub(2, "user2", club, "카테고리1");
		System.out.println(clubId);
		
		club = clubService.findClub(clubId);
		assertEquals("테스트", club.getName());
		assertEquals("테스트 설명", club.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindClub() {
		//
		Club club = clubService.findClub(2);
		
		assertEquals("클럽2", club.getName());
		assertEquals("두번째 클럽", club.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testJoinAsMember() {
		//
		clubService.joinAsMember(1, "user1");
		
		Club club = clubService.findAllClubMember(1);
		List<ClubMember> members = club.getMembers();
		for(ClubMember member : members) {
			//
			if((member.getMember().getEmail()).equals("user1")){
				assertEquals("유저1", member.getMember().getName());
			}
		}
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllClubMember() {
		//
		clubService.joinAsMember(1, "user1");
		int size = clubService.findAllClubMember(1).getMembers().size();
		ClubManager manager = clubService.findAllClubMember(1).getMainManager();
		
		assertNotNull(manager);
		assertEquals(1, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRemoveClub() {
		//
		clubService.removeClub(1);
		
		List<Club> clubs = clubService.findAllClubs(1);
		for(Club club : clubs) {
			assertNotEquals("클럽1", club.getName());
		}
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindBelongClubs() {
		//
		List<Club> clubs = clubService.findBelongClubs(1, "user4");
		
		assertEquals(1, clubs.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindManagedClubs() {
		//
		List<Club> clubs = clubService.findManagedClubs("user4");
		
		assertEquals(1, clubs.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testWithdrawalClub() {
		//
		
		clubService.joinAsMember(4, "user4");
		clubService.withdrawalClub(4, "user4");
		
		Club club = clubService.findAllClubMember(4);
		List<ClubMember> members = club.getMembers();
		for(ClubMember member : members) {
			//
			assertNotEquals("user4", member.getMember().getEmail());
		}
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllClubs() {
		//
		int size = clubService.findAllClubs(1).size();
		
		assertEquals(4, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testModifyClub() {
		//
		clubService.modifyClub(1, "테스트", "테스트 클럽");
		
		Club club = clubService.findClub(1);
		
		assertEquals("테스트", club.getName());
		assertEquals("테스트 클럽", club.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testModifyManager() {
		//
		Club club = clubService.findClub(1);
		List<ClubManager> managers = club.getManager();
		assertEquals(1, managers.size());
		
		clubService.joinAsMember(1, "user1");
		clubService.modifyManager(1, "user1", 3);
		clubService.modifyManager(1, "user4", 1);
		clubService.joinAsMember(1, "user2");
		clubService.modifyManager(1, "user2", 2);
		club = clubService.findClub(1);
		managers = club.getManager();
		
		assertEquals(2, managers.size());
	}

}
