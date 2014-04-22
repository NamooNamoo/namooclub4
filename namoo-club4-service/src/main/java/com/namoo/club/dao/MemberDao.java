package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.dto.Member;

public interface MemberDao {

	 List<Member> readAllMembers();

	 List<Member> readMembersByPerson(String email);

	 List<Member> readMemberByGroup(int groupNo, int groupType);

	 void createMember(Member member);

	 void updateMember(Member member);

	 void deleteMember(Member member);

	 Member readMember(String email, int groupNo, int groupType);

	 List<Member> findManager(int groupNo, int groupType);

	 List<Member> readMemberByCommunity(String email, int communityNo);
}
