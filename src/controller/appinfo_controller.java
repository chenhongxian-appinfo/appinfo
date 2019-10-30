package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.appinfo.appinfo_service;

@Controller
@RequestMapping("/appinfo")
public class appinfo_controller {

	@Resource
	appinfo_service impl;

	@RequestMapping("/test.html")
	public String test() {
		System.out.println("Ìúº©º©++++++++++++++++++++++++++++++++++");
		System.out.println(impl.selectInfoCountAll());
		return "devlogin";
	}
}
