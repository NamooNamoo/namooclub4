package com.namoo.club.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	//
	private final static String loginInfo = "loginId";
	
	private HttpSession session;

	private SessionManager (HttpServletRequest req) {
		this.session = req.getSession();
	}
	
	public static SessionManager getInstance(HttpServletRequest req) {
		return new SessionManager(req);
	}

	public String getLoginId() {
		//
		return (String) session.getAttribute(loginInfo);
	}

	public void setLoginId(String loginId) {
		//
		session.setAttribute(loginInfo, loginId);
	}
	
}
