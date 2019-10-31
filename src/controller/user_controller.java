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
	dev_user_service userimpl;

	@RequestMapping("/login.html")
	public String login(HttpSession session, @RequestParam(value = "devCode", required = true) String devCode,
			@RequestParam(value = "devPassword", required = true) String devPassword, Model model) {

		System.out.println("进入登录方法");
		dev_user user = userimpl.userlogin(devCode, devPassword);
		if (user == null) {
			return "redirect:jsp/devlogin.jsp";
		}
		model.addAttribute("devUserSession", user);
		return "developer/main";
	}
}
