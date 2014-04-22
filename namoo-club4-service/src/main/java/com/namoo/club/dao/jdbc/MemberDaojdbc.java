package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.dto.Member;

@Repository
public class MemberDaojdbc implements MemberDao{

	private ReturnResources returnResources = new ReturnResources();
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Member> readAllMembers() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<Member>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT user_id, group_no, group_type, level FROM member";
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				String email = rset.getString("user_id");	// member email
				int groupNo = rset.getInt("group_no");	// group number
				int groupType = rset.getInt("group_type"); // group type
				int level = rset.getInt("level"); // member level


				System.out.println("\n" + "member email : " + email + "\n" + "group number : " + groupNo
						+ "\n" + "group type : " + groupType + "\n" + "member level : " + level);
				Member member = new Member(email, groupNo, groupType, level);
				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("멤버 목록조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return memberList;
	}

	@Override
	public List<Member> readMembersByPerson(String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<Member>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT group_no, group_type, level FROM member WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int groupNo = rset.getInt("group_no");	// group number
				int groupType = rset.getInt("group_type"); // group type
				int level = rset.getInt("level"); // member level


				Member member = new Member(email, groupNo, groupType, level);
				members.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("아이디로 멤버 조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return members;
	}

	@Override
	public List<Member> readMemberByGroup(int groupNo, int groupType) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<Member>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT user_id, level FROM member WHERE group_no = ? AND group_type = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, groupType);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				String email = rset.getString("user_id");	// member email
				int level = rset.getInt("level"); // member level

				Member member = new Member(email, groupNo, groupType, level);
				members.add(member);


			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("그룹으로 멤버 조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return members;
	}

	@Override
	public void createMember(Member member) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO member ( user_id, group_no, group_type, level ) VALUES ( ?, ?, ?, ? )";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getEmail());
			pstmt.setInt(2, member.getGroupNo());
			pstmt.setInt(3, member.getGroupType());
			pstmt.setInt(4, member.getLevel());

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("멤버 생성 중 오류발생", e);

		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void updateMember(Member member) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE member SET level = ? WHERE user_id = ? AND group_no = ? AND group_type = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, member.getLevel());
			pstmt.setString(2, member.getEmail());
			pstmt.setInt(3, member.getGroupNo());
			pstmt.setInt(4, member.getGroupType());

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("멤버 정보 수정 중 오류발생", e);

		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void deleteMember(Member member) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM member WHERE user_id = ? AND group_no = ? AND group_type = ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getEmail());
			pstmt.setInt(2, member.getGroupNo());
			pstmt.setInt(3, member.getGroupType());

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("멤버 삭제 중 오류발생", e);

		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public Member readMember(String email, int groupNo, int groupType) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT level FROM member WHERE group_no = ? AND group_type = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, groupType);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int level = rset.getInt("level"); // member level
				member = new Member(email, groupNo, groupType, level);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("그룹타입, 그룹 아이디로 멤버 조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return member;
	}

	@Override
	public List<Member> findManager(int groupNo, int groupType) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<Member>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT user_id, level FROM member WHERE group_no = ? AND group_type = ? AND level != ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, groupType);
			pstmt.setInt(3, 1);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				String email = rset.getString("user_id");	// member email
				int level = rset.getInt("level"); // member level

				Member member = new Member(email, groupNo, groupType, level);
				members.add(member);


			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("관리자 조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return members;
	}

	@Override
	public List<Member> readMemberByCommunity(String email, int communityNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<Member>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT a.user_id, a.level , b.name, b.club_no FROM member a INNER JOIN club b ON a.GROUP_NO = b.CLUB_NO INNER JOIN community c ON c.COMM_NO = b.COMM_NO WHERE a.group_type = ? AND a.user_id = ? AND c.COMM_NO=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 1);
			pstmt.setString(2, email);
			pstmt.setInt(3, communityNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				String userId = rset.getString("user_id");	// member email
				int level = rset.getInt("level"); // member level
				int clubNo = rset.getInt("club_no");

				Member member = new Member(userId, clubNo, 2, level);
				members.add(member);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("커뮤니티 멤버 목록조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return members;
	}
}