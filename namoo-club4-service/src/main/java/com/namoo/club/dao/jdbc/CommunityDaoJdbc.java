package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.domain.Community;
import com.namoo.club.dto.Category;

@Repository
public class CommunityDaoJdbc implements CommunityDao {

	private ReturnResources returnResources = new ReturnResources();
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Community> readAllCommunities() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> communities = new ArrayList<Community>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT a.comm_no, a.name, a.description, a.reg_date, b.category_no, b.category, c.user_id FROM community a INNER JOIN category b ON a.COMM_NO = b.COMM_NO INNER JOIN member c ON a.COMM_NO = c.GROUP_NO WHERE c.LEVEL = 3";
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			Map<Integer, Category> categories = new HashMap<Integer, Category>();

			while (rset.next()) {

				int id = rset.getInt("comm_no");	// name

				String communityName = rset.getString("name");	// email
				String description = rset.getString("description"); // user password
				Date date = rset.getDate("reg_date");
				int categoryNo = rset.getInt("category_no");
				String category = rset.getString("category");


				Category categoryObj = new Category(id, category);
				categoryObj.setCategoryNo(categoryNo);
				categories.put(id, categoryObj);

				boolean check = true;
				for(Community community : communities){
					if(community.getId() == id){
						check = false;
					}
				}
				if(check == true){

					Community community = new Community(communityName, description);
					community.setDate(date);
					community.setId(id);
					communities.add(community);
				}
			}

			Set<Entry<Integer, Category>> entrySet = categories.entrySet();
			Iterator<Entry<Integer, Category>> iter = entrySet.iterator();

			while(iter.hasNext()){
				Category category = iter.next().getValue();

				for(Community community : communities){
					if(community.getId() == category.getCommunityNo()){
						System.out.println("카테고리 : "+category.getCategory());
						System.out.println("커뮤니티  : "+community.getName());

						community.addCategory(category.getCategory());
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("커뮤니티 목록조회 중 오류발생",e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return communities;
	}

	@Override
	public Community readCommunity(int communityNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Community community = null;
		try {
			conn = dataSource.getConnection();

			String sql = "SELECT a.comm_no, a.name, a.description, a.reg_date, b.category_no, b.category, c.user_id FROM community a INNER JOIN category b ON a.COMM_NO = b.COMM_NO INNER JOIN member c ON a.COMM_NO = c.GROUP_NO WHERE c.LEVEL = 3 AND a.comm_no = ?";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			pstmt.setInt(1, communityNo);
			rset = pstmt.executeQuery();
			
			int id = 0;
			String communityName  = null;
			String description = null;
			Date date = null;
			community = new Community();

//			Map<Integer, Category> categories = new HashMap<Integer, Category>();
			
			if(!rset.next()) {
				return null;
			} else {
				rset.previous();
			}
			
			while (rset.next()) {

				id = rset.getInt("comm_no");	// name

				communityName = rset.getString("name");	// email
				description = rset.getString("description"); // user password
				date = rset.getDate("reg_date");
				String category = rset.getString("category");
				community.addCategory(category);
//				if(rset.getString("b.category") != null){
//					String category = rset.getString("b.category");
//					Category categoryObj = new Category(id, category);
//					categoryObj.setCategoryNo(categoryNo);
//					categories.put(id, categoryObj);
//				}
			}
			community.setName(communityName);
			community.setDescription(description);
			community.setDate(date);
			community.setId(id);

//			if(categories != null){
//
//				Set<Entry<Integer, Category>> entrySet = categories.entrySet();
//				Iterator<Entry<Integer, Category>> iter = entrySet.iterator();
//
//				while (iter.hasNext()) {
//					Category category = iter.next().getValue();
//					if (community.getId() == category.getCommunityNo()) {
//						System.out.println("카테고리 : " + category.getCategory());
//						System.out.println("커뮤니티  : " + community.getName());
//
//						community.addCategory(category.getCategory());
//					}
//				}
//			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("커뮤니티 조회 중 오류발생",e);
			
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return community;
	}

	@Override
	public int createCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int communityNo = 0;
		try {
			conn = dataSource.getConnection();

			String sql = "INSERT INTO community ( name, description ) VALUES ( ?, ? )";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());

			int count = pstmt.executeUpdate();

			rset = pstmt.getGeneratedKeys();
			if(rset.next()){
				communityNo = rset.getInt(1);
				community.setId(communityNo);
			}
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("커뮤니티 생성 중 오류발생",e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return communityNo;
	}

	@Override
	public void updateCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = dataSource.getConnection();

			String sql = "UPDATE community SET name = ? ,description = ? WHERE comm_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());
			pstmt.setInt(3, community.getId());


			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("커뮤니티 정보 수정 중 오류발생",e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
	}

	@Override
	public void deleteCommunity(int communityNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = dataSource.getConnection();

			String sql = "DELETE FROM community WHERE comm_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, communityNo);


			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("커뮤니티 삭제 중 오류발생",e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
	}

}
