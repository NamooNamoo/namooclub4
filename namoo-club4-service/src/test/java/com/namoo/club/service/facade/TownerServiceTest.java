package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.club.dao.DbCommonTest;
import com.namoo.club.domain.SocialPerson;

public class TownerServiceTest extends DbCommonTest{

	private static final String DATASET_XML = "TownerServiceTest_dataset.xml";
	
	@Autowired
	TownerService townerService;

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testLoginAsTowner() {
		boolean login = townerService.loginAsTowner("user1", "1234");
		
		assertEquals(true, login);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRegistTowner() {
		townerService.registTowner("유저5", "user5", "1234");
		SocialPerson person = townerService.findTowner("user5");
		
		assertEquals("유저5", person.getName());
		assertEquals("1234", person.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testModifyTowner() {
		townerService.modifyTowner("테스트", "user1", "4321");
		SocialPerson person = townerService.findTowner("user1");
		
		assertEquals("테스트", person.getName());
		assertEquals("4321", person.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRemoveTowner() {
		//
		townerService.removeTowner("user99");
		
		assertNull(townerService.findTowner("user99"));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindTowner() {
		//
		SocialPerson person = townerService.findTowner("user1");
		
		assertEquals("유저1", person.getName());
		assertEquals("1234", person.getPassword());
		
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllTowner() {
		int size = townerService.findAllTowner().size();
		
		assertEquals(5, size);
	}

}
