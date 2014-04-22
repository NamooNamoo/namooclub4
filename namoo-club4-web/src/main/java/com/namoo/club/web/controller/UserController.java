package com.namoo.club.web.controller;

import java.util.ArrayList;
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
import com.namoo.club.domain.SocialPerson;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.web.session.LoginRequired;
import com.namoo.club.web.session.SessionManager;

@Controller
public class UserController {
	
	@Autowired
	private TownerService townerService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "/view/user/login.xhtml")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest req, 
			@RequestParam("userId") String userId, 
			@RequestParam("password") String password, 
			@RequestParam("url") String url) {
		//
		boolean login = townerService.loginAsTowner(userId, password);

		if(login){
			//
			if(url != null) {	// 로그인을 실행한 페이지 정보를 보냈는지 확인해서 로그인을 실행하고 해당 페이지로 돌려보낸다.
				if (url.contains("login.xhtml")) {
					//
					System.out.println("로긴성공");
					SessionManager.getInstance(req).setLoginId(userId);	// 로그인 유저 정보를 세션에 담아서 보냄 설정 
					return "redirect:/main.do";
				} else {
					//
					System.out.println("로긴성공");
					SessionManager.getInstance(req).setLoginId(userId);	// 로그인 유저 정보를 세션에 담아서 보냄 설정 
					return "redirect:" + url;	// 이전 주소로 리다이렉트
				}
			} else {
				//
				System.out.println("로긴성공");
				SessionManager.getInstance(req).setLoginId(userId);
				return "redirect:/main.do";
			}
		} else{
			//
			System.out.println("로긴실패");
			return "redirect:/view/user/login.xhtml";
		}
	}
	
	@RequestMapping("/user/logout.do")
	public String logout(HttpServletRequest req, HttpSession session) {
		//
		String url = req.getHeader("Referer");
		// 로그아웃을 실행한 페이지 정보를 받아서 로그아웃을 실행하고 해당 페이지로 돌려보낸다.
		session.removeAttribute("loginId");
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/view/user/join.xhtml")
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/user/join.do", method = RequestMethod.POST)
	public String join(SocialPerson person) {
		//
		townerService.registTowner(person.getName(), person.getEmail(), 
				person.getPassword());

		return "redirect:/main.do";
	}
	
	@LoginRequired
	@RequestMapping(value = "/view/user/withdrawal.xhtml")
	public String withdrawal(Model model) {
		//
		model.addAttribute("url", "user/withdrawal.do");
		model.addAttribute("message", "나무커뮤니티를 탈퇴하시겠습니까?");
		return "common/info";
		//return "user/withdrawal";
	}
	
	@LoginRequired
	@RequestMapping("/user/withdrawal.do")
	public String withdrawal(HttpSession session) {
		//
		String email = (String) session.getAttribute("loginId");
		
		townerService.removeTowner(email);
		
		session.removeAttribute("loginId");
		
		return "redirect:/main.do";
	}
	
	@LoginRequired
	@RequestMapping("/user/mypage.do")
	public String mypage(HttpSession session, Model model) {
		//
		String loginId = (String) session.getAttribute("loginId");
		
		SocialPerson person = townerService.findTowner(loginId);
		List<Community> myCommunities = communityService.findBelongCommunities(loginId);

		List<Club> myClubs = new ArrayList<Club>();
		for (Community community : myCommunities) {
			for (Club club : clubService.findBelongClubs(community.getId(), loginId)) {
				myClubs.add(club);
			}
		}
		model.addAttribute("person", person);
		model.addAttribute("my_communities", myCommunities);
		model.addAttribute("my_clubs", myClubs);
		
		return "user/mypage";
	}
	
	@LoginRequired
	@RequestMapping(value = "/user/modify.do")
	public String modify(SocialPerson person) {
		//
		townerService.modifyTowner(person.getName(), person.getEmail(), 
				person.getPassword());

		return "common/message";
	}
}