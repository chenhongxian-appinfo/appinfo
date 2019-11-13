package controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

	/**
	 * ��ҳ��ʼҳ��
	 * 
	 * @param appinfoselect
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/index.html")
	public String test(@ModelAttribute("appinfoselect") AppInfoSelect appinfoselect, HttpSession session) {
		System.out.println("+++++++++++++++++++++++++++++++++++++�ѷ�ҳ��ѯ����Ϣ�ഫ�뵽ҳ����");
		List<app_category> listtypr1 = categotyimpl.selectType1();
		List<data_dictionary> listcpType = dataimpl.selectCptype();
		List<data_dictionary> listApptype = dataimpl.selectApptype();
		AppInfoToday page = new AppInfoToday();
		page.setTotalCount(appinfoimpl.selectInfoCountAll());
		System.out.println(page.getPageSize());
		List<app_info> applist = appinfoimpl.AppinfoTodayPage(null, page);
		session.setAttribute("categoryLevel1List", listtypr1);
		session.setAttribute("flatFormList", listcpType);
		session.setAttribute("statusList", listApptype);
		session.setAttribute("pages", page);
		session.setAttribute("appInfoList", applist);
		return "developer/appinfolist";
	}

	/**
	 * ��������ѯ��ҳ
	 */
	@RequestMapping(value = "page.html")
	public String test(AppInfoSelect appinfoselect, Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("�����ѯ===========================");
		String pageIndex = request.getParameter("pageIndex");
		AppInfoToday page = (AppInfoToday) session.getAttribute("pages");
		page.setCurrentPageNo(Integer.parseInt(pageIndex));
		List<app_info> list = appinfoimpl.AppinfoTodayPage(appinfoselect, page);
		page.setList(list);
		model.addAttribute("appInfoList", list);
		session.setAttribute("pages", page);
		return "developer/appinfolist";

	}

	@RequestMapping(value = "/selectByname", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object selectByName(String APKName) {
		String yes = appinfoimpl.selectByName(APKName);
		Map<String, String> map = new HashMap<String, String>();
		map.put("APKName", yes);
		return JSONArray.toJSONString(map);
	}

	/**
	 * ��Ӳ�ѯ��ģ�Ͳ���ǰ̨
	 * 
	 * @param appinfp
	 * @return
	 */
	@RequestMapping("addindex.html")
	public String addindex(@ModelAttribute(value = "appinfp") app_info appinfp) {
		System.out.println("������ӳ�ʼ��ҳ��");
		return "developer/appinfoadd";
	}

	/**
	 * ���APPinfo
	 * 
	 * @param appinfo
	 * @param session
	 * @param attach
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addappinfo.html", method = RequestMethod.POST)
	public String addinfo(app_info appinfo, HttpSession session,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach, HttpServletRequest request) {
		System.out.println("�������APP================================");
		session.removeAttribute("fileUploadError");
		String idPacpath = null;
		String wordpath = null;

		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "images");
			wordpath = session.getServletContext().getContextPath();
			System.out.println("path===============================>" + path);
			String oidFileName = attach.getOriginalFilename();
			System.out.println("ԭ�ļ���===============================>" + oidFileName);
			String preFix = FilenameUtils.getExtension(oidFileName);
			System.out.println("ԭ�ļ���׺================================>" + preFix);
			int filesize = 500000;
			System.out.println("ԭ�ļ���С============================>" + attach.getSize());
			if (attach.getSize() > filesize) {
				session.setAttribute("fileUploadError", "�ϴ���С���ܳ���50KB");
				return "redirect:../appinfo/addindex.html";

			} else if (preFix.equalsIgnoreCase("jpg") || preFix.equalsIgnoreCase("png")
					|| preFix.equalsIgnoreCase("jpeg") || preFix.equalsIgnoreCase("pneg")) {

				String fileName = System.currentTimeMillis() + ".jpg";
				System.out.println("�����ļ����ļ���==================================>" + fileName);
				File file = new File(path, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}

				try {
					attach.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.setAttribute("fileUploadError", "�ϴ�ʧ��");
					return "redirect:../appinfo/addindex.html";
				}
				idPacpath = path + File.separator + fileName;
				wordpath = wordpath.substring(wordpath.lastIndexOf("\\") + 1);
				wordpath = wordpath.substring(1);
				wordpath = "/" + wordpath + "/statics" + File.separator + fileName;
				System.out.println("����·��====================>" + idPacpath);
			} else {
				session.setAttribute("fileUploadError", "�ϴ��ļ���ʽ����ȷ");
				return "redirect:../appinfo/addindex.html";
			}
		}
		dev_user user = (dev_user) session.getAttribute("devUserSession");

		System.out.println("���·��" + wordpath);
		appinfo.setCreatedBy(user.getId());
		appinfo.setLogoLocPath(idPacpath);
		appinfo.setLogoPicPath(wordpath);
		appinfoimpl.addinfo(appinfo);
		return "redirect:../appinfo/index.html";
	}

	/**
	 * ��ѯ�汾��¼
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "selectAppinfoById.html")
	public String selectAppinfoById(String id, Model model) {
		app_info app = appinfoimpl.selectByIdContext(id);
		model.addAttribute("appInfo", app);
		List<app_version> list = versionimpl.selectByAppId(id);
		model.addAttribute("appVersionList", list);
		return "developer/appinfoview";
	}

	/**
	 * ɾ��APPinfo
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteById", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object deleteById(String id) {

		app_info app = appinfoimpl.selectByIdContext(id);
		Map<String, Object> map = new HashMap<String, Object>();

		if (app == null) {
			map.put("delResult", "notexist");
		} else if (versionimpl.deleteById(id) && appinfoimpl.deleteById(id)) {
			map.put("delResult", "true");
		} else {
			map.put("delResult", "false");
		}

		return JSONArray.toJSONString(map);
	}

	/**
	 * �ϼ��¼�
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateAppType")
	public Object updateAppType(String Appid) {

		app_info appinfo = appinfoimpl.selectById(Appid);
		Map<String, String> map = new HashMap<>();

		map.put("errorCode", "0");
		try {

			if (appinfo.getStatus().toString().equals("4")) {
				appinfo.setStatus(BigInteger.valueOf(5));

			} else if (appinfo.getStatus().toString().equals("5")) {
				appinfo.setStatus(BigInteger.valueOf(4));

			} else {
				map.put("errorCode", "param000001");
				return JSONArray.toJSONString(map);
			}

			if (appinfoimpl.updateByinfoapp(appinfo)) {
				map.put("resultMsg", "success");
			} else {
				map.put("errorCode", "param000001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorCode", "exception000001");
			return JSONArray.toJSONString(map);
		}

		return JSONArray.toJSONString(map);

	}

	@RequestMapping(value = "updataAppinfpIndex.html")
	public String updataindex(HttpSession session, String id) {
		app_info appinfo = appinfoimpl.selectById(id);
		session.setAttribute("appInfo", appinfo);
		return "developer/appinfomodify";

	}

	@RequestMapping(value = "updatainfo.html")
	public String updataAppinfo(app_info appinfo, HttpSession session,
			@RequestParam(value = "attach", required = false) MultipartFile attach, HttpServletRequest request) {
		session.removeAttribute("fileUploadError");
		String idPacpath = null;
		String wordpath = null;

		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "images");
			wordpath = session.getServletContext().getContextPath();
			System.out.println("path===============================>" + path);
			String oidFileName = attach.getOriginalFilename();
			System.out.println("ԭ�ļ���===============================>" + oidFileName);
			String preFix = FilenameUtils.getExtension(oidFileName);
			System.out.println("ԭ�ļ���׺================================>" + preFix);
			int filesize = 500000;
			System.out.println("ԭ�ļ���С============================>" + attach.getSize());
			if (attach.getSize() > filesize) {
				session.setAttribute("fileUploadError", "�ϴ���С���ܳ���50KB");
				return "redirect:../appinfo/updataAppinfpIndex.html";

			} else if (preFix.equalsIgnoreCase("jpg") || preFix.equalsIgnoreCase("png")
					|| preFix.equalsIgnoreCase("jpeg") || preFix.equalsIgnoreCase("pneg")) {

				String fileName = System.currentTimeMillis() + ".jpg";
				System.out.println("�����ļ����ļ���==================================>" + fileName);
				File file = new File(path, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}

				try {
					attach.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.setAttribute("fileUploadError", "�ϴ�ʧ��");
					return "redirect:../appinfo/updataAppinfpIndex.html";
				}
				idPacpath = path + File.separator + fileName;
				wordpath = wordpath.substring(wordpath.lastIndexOf("\\") + 1);
				wordpath = wordpath.substring(1);
				wordpath = "/" + wordpath + "/statics" + File.separator + fileName;
				System.out.println("����·��====================>" + idPacpath);
			} else {
				session.setAttribute("fileUploadError", "�ϴ��ļ���ʽ����ȷ");
				return "redirect:../appinfo/updataAppinfpIndex.html";
			}
		}
		dev_user user = (dev_user) session.getAttribute("devUserSession");

		System.out.println("���·��" + wordpath);
		appinfo.setLogoLocPath(idPacpath);
		appinfo.setLogoPicPath(wordpath);
		appinfo.setModifyBy(user.getId());
		appinfo.setModifyDate(new Date());
		appinfoimpl.updateByinfoapp(appinfo);
		return "redirect:../appinfo/index.html";
	}

	@RequestMapping(value = "deleteAppinfo")
	@ResponseBody
	public Object deleteApplogo(String id) {
		System.out.println("����ɾ��url========================");
		app_info appinfo = appinfoimpl.selectById(id);
		String path = appinfo.getLogoLocPath();
		Map<String, String> map = new HashMap<String, String>();
		File file = new File(path);
		if (!file.exists()) {
			map.put("result", "success");
			return JSONArray.toJSONString(map);
		} else if (file.delete()) {
			System.out.println("ɾ���ɹ�");
			map.put("result", "success");
			return JSONArray.toJSONString(map);
		}

		map.put("result", "failed");
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "close.html")
	public String dve_user_close(HttpSession session) {
		session.removeAttribute("devUserSession");
		return "devlogin";
	}
}
