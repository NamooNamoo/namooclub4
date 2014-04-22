package com.namoo.club.service.facade;

import java.util.List;

import com.namoo.club.domain.SocialPerson;

public interface TownerService {
	
	boolean loginAsTowner(String email, String password);
	void registTowner(String name, String email, String password);
	void modifyTowner(String name, String email, String password);
	void removeTowner(String email);
	SocialPerson findTowner(String email);
	List<SocialPerson> findAllTowner();
	
}
