package com.namoo.club.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.namoo.club.domain.Club;
import com.namoo.club.domain.Community;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.web.session.LoginRequired;
import com.namoo.club.web.session.SessionManager;

@Controller
@LoginRequired
public class ClubController {

	@Autowired
	ClubService clubService;
	
	@Autowired
	CommunityService communityService;
	
	@RequestMapping(value = "/club/main.do")
	@LoginRequired(false)
	public String main(Model model, @RequestParam("club_id") int clubId) {
		//

		Club club = clubService.findClub(clubId);

		model.addAttribute("club", club);

		return "club/main";
	}
	
	@RequestMapping(value = "/club/join.do")
	public String join(Model model, @RequestParam("club_id") String clubId, @RequestParam("community_id") String communityId) {
		//
		model.addAttribute("club_id", clubId);
		model.addAttribute("community_id", communityId);
		
		model.addAttribute("url", "club/join_pro.do");
		model.addAttribute("message", "클럽에 가입하시겠습니까?");
		return "common/info";
	}
	
	@RequestMapping(value = "/club/join_pro.do", method = RequestMethod.POST)
	public String join2(HttpServletRequest req, @RequestParam("club_id") int clubId, @RequestParam("community_id") int communityId) {
		//

		String loginId = SessionManager.getInstance(req).getLoginId();

		clubService.joinAsMember(clubId, loginId);
		
		return "redirect:/community/main.do?community_id=" + communityId;
	}
	
	@RequestMapping(value = "/club/open.do")
	public String open(Model model, @RequestParam("community_id") int communityId) {
		//
		Community community = communityService.findCommunity(communityId);
		List<String> categories = community.getCategories();
		
		model.addAttribute("community", community);
		model.addAttribute("categories", categories);

		return "club/open";
	}
	
	@RequestMapping(value = "/club/open_pro.do", method = RequestMethod.POST)
	public String open2(HttpServletRequest req, Model model, 
			Club club, @RequestParam("community_id") int communityId) {
		//
		String loginId = SessionManager.getInstance(req).getLoginId();
				
		clubService.registClub(communityId, loginId, club, club.getCategory());

		return "redirect:/community/main.do?community_id="+communityId;
	}
	
	
	@RequestMapping(value = "/view/club/remove.xhtml", method = RequestMethod.GET)
	public String removeCommunity(Model model) {
		//return "club/remove";
		model.addAttribute("url", "club/remove.do");
		model.addAttribute("message", "클럽을 삭제하시겠습니까?");
		return "common/info";
	}
	
	@RequestMapping(value = "/club/remove.do", method = RequestMethod.POST)
	public String remove(@RequestParam("community_id") String communityId, @RequestParam("club_id") int clubId) {
		//		
		clubService.removeClub(clubId);
		String url = "";
				
		if (communityId.equals("")) {
			url="../user/mypage";
		} else {
			url="../community/main.do?community_id="+communityId;
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/view/club/withdrawal.xhtml")
	public String withdrawl(Model model) {
		model.addAttribute("url", "club/withdrawal.do");
		model.addAttribute("message", "클럽을 탈퇴하시겠습니까?");
		return "common/info";
	}
	
	@RequestMapping(value = "/club/withdrawal.do", method = RequestMethod.POST)
	public String withdrawl(HttpServletRequest req, @RequestParam("community_id") String communityId, @RequestParam("club_id") int clubId) {
		//
		String email = SessionManager.getInstance(req).getLoginId();

		clubService.withdrawalClub(clubId, email);

		String url = "";
		if (communityId.equals("")) {
			url="../user/mypage";
		} else {
			url="../community/main.do?community_id="+communityId;
		}
		return "redirect:" + url;
	}
}
