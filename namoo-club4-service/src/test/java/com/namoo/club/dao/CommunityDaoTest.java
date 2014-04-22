package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.club.domain.Community;
import com.namoo.club.dto.Category;
import com.namoo.club.dto.Member;

public class CommunityDaoTest extends DbCommonTest {

	private static final String DATASET_XML = "CommunityDaoTest_dataset.xml";
	
	@Autowired
	CommunityDao dao;
	
	@Autowired
	CategoryDao cateDao;
	
	@Autowired
	MemberDao memDao;
	
	String communityName = "테스트커뮤니티";
	String description ="테스트테스트설명입니다";
	int communityNo;

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllCommunities() {
		int size = dao.readAllCommunities().size();

		assertEquals(4, size);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadCommunity() {
		Community community = dao.readCommunity(1);
		
		assertEquals("커뮤니티1", community.getName());
		assertEquals("첫번째 커뮤니티", community.getDescription());
	}


	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateCommunity() {
		communityNo = createCommunity();
		Community community = dao.readCommunity(communityNo);
		
		assertEquals(communityName, community.getName());
	}

	public int createCommunity() {
		//
		Community community = new Community(communityName, description);
		int communityNo = dao.createCommunity(community);
		Member member = new Member("user1", communityNo, 1, 3);
		memDao.createMember(member);
		Category category = new Category(communityNo, "category");
		category.setCategoryNo(13);
		cateDao.createCategory(category);
		return communityNo;
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateCommunity() {
		communityNo = createCommunity();
		Community community = dao.readCommunity(communityNo);

		if(community ==null){
			System.out.println("null");
		}
		System.out.println(communityNo);
		community.setDescription("바꿈 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
		dao.updateCommunity(community);
		community = dao.readCommunity(communityNo);
		assertEquals("바꿈 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ", community.getDescription());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteCommunity() {
		List<Category> categories = cateDao.readCategoriesByCommunityNo(2);		
		for(Category category : categories) {
			cateDao.deleteCategory(category.getCategoryNo());
		}
		List<Member> members = memDao.readMemberByGroup(communityNo, 1);
		for(Member member : members) {
			memDao.deleteMember(member);
		}
		dao.deleteCommunity(2);
	}

}

