package controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

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

	@RequestMapping(value = "/selectByname", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object selectByName(String APKName) {
		String yes = appinfoimpl.selectByName(APKName);

		Map<String, String> map = new HashMap<String, String>();
		map.put("APKName", yes);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping("addindex.html")
	public String addindex(@ModelAttribute(value = "appinfp") app_info appinfp) {
		System.out.println("进入添加初始化页面");
		return "developer/appinfoadd";
	}

	@RequestMapping(value = "addappinfo.html", method = RequestMethod.POST)
	public String addinfo(app_info appinfo, HttpSession session,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach, HttpServletRequest request) {
		System.out.println("进入添加APP================================");
		session.removeAttribute("fileUploadError");
		String idPacpath = null;
		String wordpath = null;
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "images");
			wordpath = session.getServletContext().getContextPath();
			System.out.println("path===============================>" + path);
			String oidFileName = attach.getOriginalFilename();
			System.out.println("原文件名===============================>" + oidFileName);
			String preFix = FilenameUtils.getExtension(oidFileName);
			System.out.println("原文件后缀================================>" + preFix);
			int filesize = 500000;
			System.out.println("原文件大小============================>" + attach.getSize());
			if (attach.getSize() > filesize) {
				session.setAttribute("fileUploadError", "上传大小不能超出50KB");
				return "redirect:appinfo/addindex.html";

			} else if (preFix.equalsIgnoreCase("jpg") || preFix.equalsIgnoreCase("png")
					|| preFix.equalsIgnoreCase("jpeg") || preFix.equalsIgnoreCase("pneg")) {

				String fileName = System.currentTimeMillis() + ".jpg";
				System.out.println("保存文件的文件名==================================>" + fileName);
				File file = new File(path, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}

				try {
					attach.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.setAttribute("fileUploadError", "上传失败");
					return "redirect:appinfo/addindex.html";
				}
				idPacpath = path + File.separator + fileName;
				wordpath = wordpath.substring(wordpath.lastIndexOf("\\") + 1);
				wordpath = wordpath.substring(1);
				System.out.println(wordpath);

				wordpath = "\\" + wordpath + "\\statics" + File.separator + fileName;
				System.out.println("绝对路径====================>" + idPacpath);
			} else {
				session.setAttribute("fileUploadError", "上传文件格式不正确");
				return "redirect:appinfo/addindex.html";
			}
		}
		dev_user user = (dev_user) session.getAttribute("devUserSession");

		System.out.println("相对路径" + wordpath);
		appinfo.setCreatedBy(user.getId());
		appinfo.setLogoLocPath(idPacpath);
		appinfo.setLogoPicPath(wordpath);
		appinfoimpl.addinfo(appinfo);
		return "redirect:appinfo/index.html";
	}
}
