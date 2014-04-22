package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.club.domain.SocialPerson;

public class UserDaoTest extends DbCommonTest {
	
	private static final String DATASET_XML = "UserDaoTest_dataset.xml";
	
	@Autowired
	UserDao userDao;

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllUsers() {
		//
		List<SocialPerson> towners = userDao.readAllUsers();
		
		assertEquals(4, towners.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadUser() {
		//
		SocialPerson person = userDao.readUser("user1");
		
		assertEquals("유저1", person.getName());
		assertEquals("1234", person.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateUser() {
		//
		SocialPerson person = new SocialPerson("유저5", "user5", "1234");
		userDao.createUser(person);
		
		person = userDao.readUser("user5"); 
		assertEquals("유저5", person.getName());
		assertEquals("1234", person.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateUser() {
		//
		SocialPerson person = new SocialPerson("유저1", "user1", "4321");
		userDao.updateUser(person);
		

		person = userDao.readUser("user1");
		assertEquals("4321", person.getPassword());
		
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteUser() {
		//
		userDao.deleteUser("user1");
		
		assertNull(userDao.readUser("user1"));
	}

}
