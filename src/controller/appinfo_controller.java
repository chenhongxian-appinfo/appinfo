package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.type.IntegerTypeHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.version.version_dao;
import entity.AppInfoSelect;
import entity.AppInfoToday;
import entity.app_category;
import entity.app_info;
import entity.app_version;
import entity.data_dictionary;
import entity.dev_user;
import service.appinfo.appinfo_service;
import service.categoty.category_service;
import service.data_dictionary.data_dictionary_service;
import service.user.dev_user_service;
import service.version.version_service;

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
	@Resource
	version_service versionimpl;

	@RequestMapping(value = "/index.html")

	public String test(@ModelAttribute("appinfoselect") AppInfoSelect appinfoselect, HttpSession session) {
		System.out.println("+++++++++++++++++++++++++++++++++++++把分页查询的信息类传入到页面中");
		List<app_category> listtypr1 = categotyimpl.selectType1();
		List<data_dictionary> listcpType = dataimpl.selectCptype();
		List<data_dictionary> listApptype = dataimpl.selectApptype();
		AppInfoToday page = new AppInfoToday();
		page.setTotalCount(appinfoimpl.selectInfoCountAll());
		List<app_info> applist = appinfoimpl.AppinfoTodayPage(null, page);
		session.setAttribute("categoryLevel1List", listtypr1);
		session.setAttribute("flatFormList", listcpType);
		session.setAttribute("statusList", listApptype);
		session.setAttribute("pages", page);
		session.setAttribute("appInfoList", applist);
		return "developer/appinfolist";
	}

	@RequestMapping(value = "page.html")
	public String test(AppInfoSelect appinfoselect, Model model, HttpSession session, HttpServletRequest request) {

		System.out.println("进入查询===========================");
		String pageIndex = request.getParameter("pageIndex");
		AppInfoToday page = (AppInfoToday) session.getAttribute("pages");
		page.setCurrentPageNo(Integer.parseInt(pageIndex));
		List<app_info> list = appinfoimpl.AppinfoTodayPage(appinfoselect, page);
		page.setList(list);
		model.addAttribute("appInfoList", list);
		session.setAttribute("pages", page);
		return "developer/appinfolist";

	}

	@RequestMapping(value = "/selectByid.html")
	public String selectByid(String id, Model model) {
		System.out.println("进入id查询=======================================");
		List<app_version> list = versionimpl.selectById(id);
		System.out.println(list.get(0).getPublishStatusName());
		model.addAttribute("appVersionList", list);
		return "developer/appversionadd";

	}
}
