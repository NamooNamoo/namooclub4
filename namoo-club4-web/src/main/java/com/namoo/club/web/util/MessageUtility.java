package com.namoo.club.web.util;

import org.springframework.ui.Model;

public class MessageUtility {
	//
	private Model model;

	private MessageUtility (Model model) {
		this.model = model;
	}
	
	public static MessageUtility getInstance(Model model, String msg, String url) {
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		return new MessageUtility(model);
	}
}
