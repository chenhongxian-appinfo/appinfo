package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.dev_user;
import service.user.dev_user_service;

@Controller
@RequestMapping("/user")
public class user_controller {

	@Resource
	dev_user_service impl;

	@RequestMapping("/login.html")
	public String login(HttpSession session, @RequestParam(value = "devCode", required = true) String devCode,
			@RequestParam(value = "devPassword", required = true) String devPassword, Model model) {

		dev_user user = impl.userlogin(devCode, devPassword);
		if (user == null) {
			return "redirct:jsp/devlogin.jsp";
		}
		model.addAttribute("user", user);
		return "developer/main";
	}
}
