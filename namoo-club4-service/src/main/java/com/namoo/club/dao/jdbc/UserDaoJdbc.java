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

import com.namoo.club.dao.UserDao;
import com.namoo.club.domain.SocialPerson;

@Repository
public class UserDaoJdbc implements UserDao{
	
	private ReturnResources returnResources = new ReturnResources();
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<SocialPerson> readAllUsers() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SocialPerson> personList = new ArrayList<SocialPerson>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT name, email, passwd FROM user";
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String name = rset.getString("name");	// name
				String email = rset.getString("email");	// email
				String password = rset.getString("passwd"); // user password
				
				System.out.println("\n" + "name : " + name + "\n" + "email : " + email);
				SocialPerson person = new SocialPerson(name, email, password);
				personList.add(person);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 목록조회 중 오류발생", e);
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return personList;
	}

	@Override
	public SocialPerson readUser(String email) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SocialPerson person = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT name, passwd FROM user WHERE email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				String name = rset.getString("name");	// name
				String password = rset.getString("passwd");;	// user password
				
				System.out.println("\n" + "name : " + name + "\n" + "email : " + email);
				person = new SocialPerson(name, email, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 조회 중 오류발생", e);

		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return person;
	}

	@Override
	public void createUser(SocialPerson person) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO user ( email, name, passwd ) VALUES ( ?, ?, ? )";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, person.getEmail());
			pstmt.setString(2, person.getName());
			pstmt.setString(3, person.getPassword());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 생성 중 오류발생", e);

		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void updateUser(SocialPerson person) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE user SET name = ?, passwd = ? WHERE email = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getPassword());
			pstmt.setString(3, person.getEmail());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 정보 수정 중 오류발생", e);

		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void deleteUser(String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM user WHERE email = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 삭제 중 오류발생", e);

		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}
}
