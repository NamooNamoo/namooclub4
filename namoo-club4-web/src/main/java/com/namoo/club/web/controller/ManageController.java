package com.namoo.club.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namoo.club.domain.Club;
import com.namoo.club.domain.Community;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.web.session.LoginRequired;
import com.namoo.club.web.session.SessionManager;

@Controller
@LoginRequired
public class ManageController {
	
	@Autowired
	private CommunityService communityService;
	@Autowired
	private ClubService clubService;

	@RequestMapping("/manage/community.do")
	public String communityManage(HttpServletRequest req, Model model) {
		//
		String email = SessionManager.getInstance(req).getLoginId();

		List<Community> communityList = communityService.findManagedCommnities(email);

		model.addAttribute("community_list", communityList);
		
		return "manage/community";
	}
	
	@RequestMapping("/manage/community_detail.do")
	public String communityDetail(Model model, String community_id) {
		//
		int communityId = Integer.parseInt(community_id);

		Community community = communityService.findCommunity(communityId);

		model.addAttribute("community", community);

		return "manage/community_detail";
	}
	
	@RequestMapping("/manage/community_modify.do")
	public String communityModify(Community community, String community_id) {
		//
		int commuityId = Integer.parseInt(community_id);

		communityService.modifyCommunity(commuityId, community.getName(), 
				community.getDescription());

		return "redirect:/manage/community.do";
	}
	
	@RequestMapping("/manage/club.do")
	public String clubManage(HttpServletRequest req, Model model) {
		//
		String email = SessionManager.getInstance(req).getLoginId();

		List<Community> myCommunityList = communityService.findBelongCommunities(email);
		List<Club> clubList = clubService.findManagedClubs(email);

		model.addAttribute("community_list", myCommunityList);
		model.addAttribute("club_list", clubList);

		return "manage/club";
	}
	
	@RequestMapping("/manage/club_detail.do")
	public String clubDetail(Model model, String club_id) {
		//
		int clubId = Integer.parseInt(club_id);

		Club club = clubService.findClub(clubId);

		model.addAttribute("club", club);
		
		return "manage/club_detail";
	}
	
	@RequestMapping("/manage/club_modify.do")
	public String clubModify(Club club, String club_id) {
		//
		int clubId = Integer.parseInt(club_id);

		clubService.modifyClub(clubId, club.getName(), club.getDescription());

		return "redirect:/manage/club.do";
	}
	
	@RequestMapping("/manage/club_mem")
	public String clubMember(Model model, String club_id) {
		//
		int clubId = Integer.parseInt(club_id);

		Club clubMembers = clubService.findAllClubMember(clubId); 

		model.addAttribute("club", clubMembers);

		return "manage/club_mem";
	}
	
	@RequestMapping("/manage/club_mem_detail.do")
	public String clubMemberDetail(HttpServletRequest req, Model model) {
		//
		String email = SessionManager.getInstance(req).getLoginId();

		List<Community> myCommunityList = communityService.findBelongCommunities(email);
		List<Club> clubList = clubService.findManagedClubs(email);

		model.addAttribute("community_list", myCommunityList);
		model.addAttribute("club_list", clubList);

		return "manage/club_mem_detail";
	}
	
	@RequestMapping("/manage/club_mem.do")
	public String ClubMemberModify(HttpServletRequest req, String club_id, 
			String id, String level) {
		//
		int clubId = Integer.parseInt(club_id);
		int intLevel = Integer.parseInt(level);

		clubService.modifyManager(clubId, id, intLevel);

		if(intLevel==3) {
			clubService.modifyManager(
					clubId, SessionManager.getInstance(req).getLoginId(), 1);
			return "redirect:/main.do";
		} else {
			return "redirect:/manage/club_mem?club_id=" + clubId;
		}
	}
}