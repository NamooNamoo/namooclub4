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

import com.namoo.club.dao.CategoryDao;
import com.namoo.club.dto.Category;

@Repository
public class CategoryDaoJdbc implements CategoryDao{

	private ReturnResources returnResources = new ReturnResources();
	
	@Autowired
	private DataSource dataSource;
	
//	@Override
//	public List<Category> readAllCategories() {
//		//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		List<Category> categoryList = new ArrayList<Category>();
//
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = "SELECT category, comm_no, category_no FROM category";
//			pstmt = conn.prepareStatement(sql);
//
//			rset = pstmt.executeQuery();
//
//			while (rset.next()) {
//				String category = rset.getString("category");	// category
//				int communityNo = rset.getInt("comm_no");	// community number
//				int categoryNo = rset.getInt("category_no");
//
//				System.out.println("\n" + "category : " + category + "\n" + "community number : " + communityNo);
//				Category  categoryObj = new Category(communityNo, category);
//				categoryObj.setCategoryNo(categoryNo);
//				categoryList.add(categoryObj);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException("카테고리 목록조회 중 오류발생", e);
//		} finally {
//			returnResources.returnResources(rset, pstmt, conn);
//		}
//		return categoryList;
//	}

	@Override
	public List<Category> readCategoriesByCommunityNo(int communityNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Category> categoryList = new ArrayList<Category>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT category, category_no FROM category WHERE comm_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);

			rset = pstmt.executeQuery();

			System.out.println("community number : " + communityNo);
			while (rset.next()) {
				String category = rset.getString("category");	// category
				int categoryNo = rset.getInt("category_no");

				System.out.println("\n" + "category : " + category);
				Category categoryObj = new Category(communityNo, category);
				categoryObj.setCategoryNo(categoryNo);
				categoryList.add(categoryObj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("카테고리 목록조회 중 오류발생", e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return categoryList;
	}

	@Override
	public void createCategory(Category category) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO category (category, comm_no) VALUES ( ?, ? )";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCategory());
			pstmt.setInt(2, category.getCommunityNo());

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("카테고리 생성 중 오류발생", e);
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void deleteCategory(int categoryNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM category WHERE category_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, categoryNo);

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("카테고리 삭제 중 오류발생", e);
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}
}
