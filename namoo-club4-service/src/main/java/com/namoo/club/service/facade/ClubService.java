package com.namoo.club.service.facade;

import java.util.List;

import com.namoo.club.domain.Club;
import com.namoo.club.service.logic.exception.NamooRuntimeException;

public interface ClubService {

	/**
	 *
	 * @param clubName
	 * @param adminName
	 * @param email
	 * @param password
	 */

	/**
	 * [주민으로 등록되지 않은 경우] 클럽 개설
	 *
	 * 주민 가입을 처리하고 나서 클럽를 개설한다.
	 * 이미 존재하는 주민인 경우 예외가 발생한다.
	 * @param clubName
	 * @param description
	 * @param adminName
	 * @param email
	 * @param password
	 */


	//public void registClub(String communityName, String clubName, String description, String adminName, String email, String password);

	/**
	 * [주민으로 등록된 경우] 클럽 개설
	 *
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다.
	 *
	 * @param clubName
	 * @param description
	 * @param email
	 *
	 * @throws NamooRuntimeException
	 */
	public int registClub(int communityNo, String email, Club club, String category);

	/**
	 *
	 * @param clubName
	 */
	public Club findClub(int clubNo);




	/**
	 * [주민으로 등록되지 않은 경우] 클럽 가입
	 *
	 * 주민 가입을 처리하고 나서 클럽에 가입한다.
	 * 이미 존재하는 주민인 경우 예외가 발생한다.
	 *
	 * @param clubName
	 * @param name
	 * @param email
	 * @param password
	 *
	 * @throws NamooRuntimeException
	 */
//	public void joinAsMember(String comm_no, String clubName, String name, String email, String password);

	/**
	 * [주민으로 등록된 경우] 클럽 가입
	 *
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다.
	 *
	 * @param clubName
	 * @param email
	 *
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(int clubNo, String email);

	/**
	 * @return
	 */


//	/**
//	 * 이메일로 클럽 회원 찾기
//	 *
//	 * @param clubName
//	 * @param email
//	 * @return
//	 */
//	public ClubMember findClubMember(String communityName, String clubName, String email);


	/**
	 * 클럽 회원목록 조회
	 *
	 * @param clubName
	 * @return
	 */
//	public List<Member> findAllClubMember(int clubNo);
	public Club findAllClubMember (int clubNo);

	/**
	 *
	 * @param clubName
	 */
	//public int countMembers(int clubNo);

	/**
	 * @param clubName
	 */
	public void removeClub(int clubNo);

	/**
	 * 자신이 회원으로 있는 클럽 목록조회
	 *
	 * @param email
	 * @return
	 */
	public List<Club> findBelongClubs(int communityNo, String email);

	/**
	 * 자신이 관리하는 클럽 목록조회
	 *
	 * @param email
	 * @return
	 */
	public List<Club> findManagedClubs(String email);
//	public List<Club> findMadeClubs(String email);

	/**
	 * 클럽에서 탈퇴하기
	 *
	 * @param clubName
	 * @param email
	 */
	public void withdrawalClub(int clubNo, String email);

	List<Club> findAllClubs(int communityNo);

	public void modifyClub(int clubNo, String name, String description);

	public void modifyManager(int clubNo, String email, int level);


}
