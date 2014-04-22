package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.SocialPerson;

public interface UserDao {

	 List<SocialPerson> readAllUsers();
	 
	 SocialPerson readUser(String email);
	 
	 void createUser(SocialPerson person);
	 
	 void updateUser(SocialPerson person);
	 
	 void deleteUser(String email);
	
}
