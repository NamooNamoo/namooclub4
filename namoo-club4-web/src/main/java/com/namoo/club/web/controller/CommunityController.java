package com.namoo.club.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namoo.club.domain.Club;
import com.namoo.club.domain.Community;
import com.namoo.club.domain.SocialPerson;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.web.session.LoginRequired;
import com.namoo.club.web.session.SessionManager;

@Controller
@LoginRequired
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	@Autowired
	private ClubService clubService;
	@Autowired
	private TownerService townerService;

	@RequestMapping("/main.do")
	@LoginRequired(false)
	public String main(HttpServletRequest req, Model model) {
		//
		String email = SessionManager.getInstance(req).getLoginId();

		List<Community> communityList = communityService.findAllCommunities();
		if (email == null) {

			model.addAttribute("community_list", communityList);
		} else {

			List<Community> myCommunityList = communityService.findBelongCommunities(email);
			List<Community> removes = new ArrayList<Community>();

			for(Community community : communityList){
				for (Community myCommunity : myCommunityList) {
					if (community.getId() == myCommunity.getId()) {
						removes.add(community);
					}
				}
			}
			communityList.removeAll(removes);
			model.addAttribute("my_community_list", myCommunityList);
			model.addAttribute("not_my_community_list", communityList);
		}
		return "main";
	}
	
	@RequestMapping("/community/main.do")
	@LoginRequired(false)
	public String communityMain(HttpServletRequest req, Model model, 
			String community_id) {
		//
		String email = SessionManager.getInstance(req).getLoginId();

		int communityId = Integer.parseInt(community_id);

		Community community = communityService.findCommunity(communityId);
		List<Club> clubList = clubService.findAllClubs(communityId);
		
		if (email == null) {
			//
			model.addAttribute("club_list", clubList);
		} else {
			//
			List<Club> myClubList = clubService.findBelongClubs(communityId, email);
			List<Club> removes = new ArrayList<Club>();

			for(Club club : clubList){
				for (Club myClub : myClubList) {
					if (club.getId() == myClub.getId()) {
						removes.add(club);
					}
				}
			}
			clubList.removeAll(removes);
			
			model.addAttribute("community_id", communityId);
			model.addAttribute("community", community);
			model.addAttribute("my_club_list", myClubList);
			model.addAttribute("not_my_club_list", clubList);
		}
		return "/community/main";
	}
	
	@RequestMapping(value = "/view/community/open.xhtml")
	public String openCommunity() {
		return "community/open";
	}
	
	@RequestMapping("/community/open.do")
	public String openCommunityProcess(HttpServletRequest req, Community community,
			String[] categories) {
		//
		String loginId = SessionManager.getInstance(req).getLoginId();
		SocialPerson person = townerService.findTowner(loginId);

		String email = person.getEmail();
		List<String> categoryList = new ArrayList<String>();
		
		for (int i = 0; i < categories.length; i++) {
			//
			if(!categories[i].equals("")) {
				//
				categoryList.add(categories[i]);
			}
		}
		communityService.registCommunity(community.getName(), 
				community.getDescription(), email, categoryList);

		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/view/community/join.xhtml")
	public String joinCommunity(Model model) {
		//
		model.addAttribute("url", "community/join.do");
		model.addAttribute("message", "커뮤니티에 가입하시겠습니까?");
		return "common/info";
	}
	
	@RequestMapping("/community/join.do")
	public String joinCommunity(HttpServletRequest req, String community_id) {
		//
		int communityId = Integer.parseInt(community_id);

		String loginId =  SessionManager.getInstance(req).getLoginId();

		communityService.joinAsMember(communityId, loginId);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/view/community/remove.xhtml")
	public String removeCommunity(Model model) {
		//
		model.addAttribute("url", "community/remove.do");
		model.addAttribute("message", "커뮤니티를 삭제하시겠습니까?");
		return "common/info";
		//return "community/remove";
	}
	
	@RequestMapping("/community/remove.do")
	public String removeCommunity(String community_id, String mypage) {
		//
		int communityId = Integer.parseInt(community_id);
		
		communityService.removeCommunity(communityId);
		
		if (mypage != null) {
			return "redirect:/user/mypage.do";
		} else {
			return "redirect:/main.do";
		}
	}
	
	@RequestMapping(value = "/view/community/withdrawal.xhtml")
	public String withdrawalCommunity(Model model) {
		//
		model.addAttribute("url", "community/withdrawal.do");
		model.addAttribute("message", "커뮤니티를 탈퇴하시겠습니까?");
		return "common/info";
	}
	
	@RequestMapping("/community/withdrawal.do")
	public String withdrawalCommunity(HttpServletRequest req, 
			String community_id, String mypage) {
		//
		int communityId = Integer.parseInt(community_id);
		String email =  SessionManager.getInstance(req).getLoginId();

		communityService.withdrawalCommunity(communityId, email);
		
		if (mypage != null) {
			return "redirect:/user/mypage.do";
		} else {
			return "redirect:/main.do";
		}
	}
}
