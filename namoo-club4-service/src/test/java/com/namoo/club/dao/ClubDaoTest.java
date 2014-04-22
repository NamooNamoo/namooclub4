package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.club.domain.Club;
import com.namoo.club.dto.Member;

public class ClubDaoTest extends DbCommonTest {
	
	private static final String DATASET_XML = "ClubDaoTest_dataset.xml";
	
	@Autowired
	ClubDao clubDao;
	
	@Autowired
	MemberDao memDao;
	
	private String clubName = "테스트"; 
	private String description = "테스트 클럽입니다."; 
	
	Club club = new Club(clubName, description);

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllClubByCommunityId() {
		//
		int size = clubDao.readAllClubByCommunityId(1).size();
		
		assertEquals(4, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateClub() {
		int clubNo = clubDao.createClub(2, club, 5);
		Member member = new Member("user4", clubNo, 2, 3);
		memDao.createMember(member);
		
		club = clubDao.readClub(clubNo);
		assertEquals("테스트", club.getName());
		assertEquals("테스트 클럽입니다.", club.getDescription());
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateClub() {
		Club club = new Club(1, "테스트1", "테스트1 클럽입니다.");
		clubDao.updateClub(club);
		
		club = clubDao.readClub(1);
		
		assertEquals("테스트1", club.getName());
		assertEquals("테스트1 클럽입니다.", club.getDescription());
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadClub() {
		club = clubDao.readClub(1);
		
		assertEquals("클럽1", club.getName());
		assertEquals("첫번째 클럽", club.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllClubMembersById() {
		List<Member> members = clubDao.readAllClubMembersById(1);
		for (Member member : members) {
			if(member.getLevel() == 3) {
				assertEquals("user4", member.getEmail());
			}
		}
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteClub() {
		clubDao.deleteClub(1);
		
		assertNull(clubDao.readClub(1));
	}

}
