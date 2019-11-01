package controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.AppInfoSelect;
import entity.AppInfoToday;
import entity.app_category;
import entity.app_info;
import entity.data_dictionary;
import entity.dev_user;
import service.appinfo.appinfo_service;
import service.categoty.category_service;
import service.data_dictionary.data_dictionary_service;
import service.user.dev_user_service;

@Controller
@RequestMapping("/appinfo")
public class appinfo_controller {

	@Resource
	appinfo_service appinfoimpl;
	@Resource
	category_service categotyimpl;

	@Resource
	dev_user_service devuserimpl;
	@Resource
	data_dictionary_service dataimpl;

	@RequestMapping(value = "/index.html")

	public String test(@ModelAttribute("Appinfoselect") AppInfoSelect Appinfoselect, Model model) {
		System.out.println("+++++++++++++++++++++++++++++++++++++把分页查询的信息类传入到页面中");
		List<app_category> listtypr1 = categotyimpl.selectType1();
		List<data_dictionary> listcpType = dataimpl.selectCptype();
		List<data_dictionary> listApptype = dataimpl.selectApptype();
		AppInfoToday page = new AppInfoToday();
		page.setTotalCount(appinfoimpl.selectInfoCountAll());
		List<app_info> applist = appinfoimpl.AppinfoTodayPage(null, page);
		model.addAttribute("categoryLevel1List", listtypr1);
		model.addAttribute("flatFormList", listcpType);
		model.addAttribute("statusList", listApptype);
		model.addAttribute("pages", page);
		model.addAttribute("appInfoList", applist);
		return "developer/appinfolist";
	}
}
