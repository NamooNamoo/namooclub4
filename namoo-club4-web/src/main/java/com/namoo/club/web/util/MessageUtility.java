package com.namoo.club.web.util;

import org.springframework.ui.Model;

public class MessageUtility {
	//
	private MessageUtility () {
		
	}
	
	public static MessageUtility getInstance() {
		//
		return new MessageUtility();
	}
	
	public String showInfo(Model model, String msg, String url) {
		//
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		return "common/info";
	}
}
