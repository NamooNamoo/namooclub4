package com.namoo.club.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.UserDao;
import com.namoo.club.domain.SocialPerson;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.logic.exception.NamooRuntimeException;

@Service
public class TownerServiceLogic implements TownerService {

@Autowired
private UserDao userDao;

@Autowired
private MemberDao memberDao;

	@Override
	public boolean loginAsTowner(String email, String password) {
		//
		SocialPerson towner = userDao.readUser(email);
		if (towner != null && towner.getPassword().equals(password)) {
			return true;
		}

		return false;
	}

	@Override
	public void registTowner(String name, String email, String password) {
		//
		if (userDao.readUser(email) != null) {
			throw new RuntimeException("해당 주민이 이미 존재합니다. 등록할 수 없습니다.");
		}

		SocialPerson towner = new SocialPerson(name, email, password);
		userDao.createUser(towner);
	}
	
	

	@Override
	public void modifyTowner(String name, String email, String password) {
		// 
		SocialPerson towner = new SocialPerson(name, email, password);
		userDao.updateUser(towner);
	}

	@Override
	public void removeTowner(String email) {
		//
		// 커뮤니티의 회원으로 가입된 경우 삭제하지 못한다.
//		List<Community> communities = communityDao.readAllCommunities();
//		if (communities != null) {
//			for (Community community : communities) {
//				if (community.findMember(email) != null) {
//					throw new NamooRuntimeException(
//							"커뮤니티에 가입된 회원은 삭제할 수 없습니다. 먼저 커뮤니티를 탈퇴하세요. ["+community.getName()+"]");
//				}
//			}
//		}
		if (memberDao.readMembersByPerson(email).size() != 0) {
			throw new NamooRuntimeException(
					"커뮤니티에 가입된 회원은 삭제할 수 없습니다. 먼저 커뮤니티를 탈퇴하세요.");
		}

		userDao.deleteUser(email);
	}

	@Override
	public SocialPerson findTowner(String email) {
		//
		return userDao.readUser(email);
	}

	@Override
	public List<SocialPerson> findAllTowner() {
		//
		return userDao.readAllUsers();
	}
}
