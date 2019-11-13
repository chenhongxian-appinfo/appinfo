package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.backend_user;
import service.backend_user.backend_user_service;

@RequestMapping("backenduser")
@Controller
public class backend_user_controller {
	@Resource
	backend_user_service userimpl;

	@RequestMapping(value = "selectuser.html")
	public String selectUser(String userCode, String userPassword, HttpSession session) {
		backend_user user = userimpl.selectByCoke(userCode, userPassword);

		if (user == null) {
			session.setAttribute("error", "密码或用户名错误");
			return "backendlogin";
		}

		session.setAttribute("userSession", user);
		return "backend/main";

	}

}
