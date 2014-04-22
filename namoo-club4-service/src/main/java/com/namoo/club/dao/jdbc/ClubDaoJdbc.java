package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.domain.Club;
import com.namoo.club.dto.Member;

@Repository
public class ClubDaoJdbc implements ClubDao {

	private ReturnResources returnResources = new ReturnResources();

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Club> readAllClubByCommunityId(int communityId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Club> clubList = new ArrayList<Club>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT a.club_no, a.name, a.description, b.group_type, c.category, a.reg_date, b.user_id"
					+ " FROM club a"
					+ " INNER JOIN member b"
					+ " ON a.club_no = b.group_no"
					+ " INNER JOIN category c"
					+ " ON a.category_no = c.category_no"
					+ " WHERE a.comm_no = ? AND b.level = 3 AND b.group_type = 2";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, communityId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int id = rset.getInt("club_no");	// club number
				String clubName = rset.getString("name");	// club name
				String description = rset.getString("description"); // club description
				String category = rset.getString("category");	// club category number
				Date date = rset.getDate("reg_date");	// club registration date


				Club club = new Club(id, clubName, description, category, date);
				club.setCommunityId(communityId);
				clubList.add(club);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클럽 목록조회 중 오류발생", e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return clubList;
	}

	@Override
	public Club readClub(int id) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Club club = null;

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT a.comm_no, a.name, a.description, c.category, a.reg_date, b.user_id"
					+ " FROM club a"
					+ " INNER JOIN member b"
					+ " ON a.club_no = b.group_no"
					+ " INNER JOIN category c"
					+ " ON a.category_no = c.category_no"
					+ " WHERE a.club_no = ? AND b.level = 3";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int commId = rset.getInt("comm_no");	// community number
				String clubName = rset.getString("name");	// club name
				String description = rset.getString("description"); // club description
				String category = rset.getString("category");	// club category
				Date date = rset.getDate("reg_date");	// club registration date


				club = new Club(id, clubName, description, category, date);
				club.setCommunityId(commId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클럽 조회 중 오류발생", e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return club;
	}

	@Override
	public List<Member> readAllClubMembersById(int id) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<Member>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT user_id, level FROM member WHERE group_no = ? AND group_type = 2";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				String email = rset.getString("user_id");	// user email
				int level = rset.getInt("level"); // user level

				Member member = new Member(email, id, 2, level);

				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클럽 멤버 목록조회 중 오류발생", e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return memberList;
	}

	@Override
	public int createClub(int communityNo, Club club, int categoryNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int id = 0;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO club ( comm_no, name, description, category_no ) VALUES ( ?, ?, ?, ? )";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, communityNo);
			pstmt.setString(2, club.getName());
			pstmt.setString(3, club.getDescription());
			pstmt.setInt(4, categoryNo);

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				id = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클럽 생성 중 오류발생", e);
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
		return id;
	}

	@Override
	public void updateClub(Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE club SET name = ? ,description = ? WHERE club_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, club.getName());
			pstmt.setString(2, club.getDescription());
			pstmt.setInt(3, club.getId());

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클럽 정보 수정 중 오류발생", e);
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void deleteClub(int id) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM club WHERE club_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클럽 삭제 중 오류발생", e);
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}
}
