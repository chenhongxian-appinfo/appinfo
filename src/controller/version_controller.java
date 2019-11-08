package controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import entity.app_info;
import entity.app_version;
import entity.dev_user;
import service.appinfo.appinfo_service;
import service.version.version_service;

@Controller
@RequestMapping("/version")
public class version_controller {

	@Resource
	version_service versionimpl;

	@Resource
	appinfo_service appinfoimpl;

	@RequestMapping("/verindex.html")
	public String addindex(@ModelAttribute(value = "version") app_version version, String id, HttpSession session) {
		System.out.println("吧version传入到前台");
		System.out.println("进入id查询=======================================");
		List<app_version> list = versionimpl.selectByAppId(id);
		if (list.size() <= 0) {
			list = new ArrayList<app_version>();
			app_version versions = new app_version();
			versions.setAppId(BigInteger.valueOf(Integer.valueOf(id)));
			list.add(versions);
		}
		session.setAttribute("appVersion", list.get(0));
		session.setAttribute("appVersionList", list);
		return "developer/appversionadd";
	}

	/**
	 * 添加新的版本信息
	 * 
	 * @param version
	 * @param attach
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/addversion.html")
	public String addversion(app_version version, @RequestParam(value = "a_downloadLink") MultipartFile attach,
			HttpSession session, HttpServletRequest request) {
		System.out.println("进入version添加=========================");
		session.removeAttribute("fileUploadError");
		version.setAppId(BigInteger.valueOf(Integer.valueOf(request.getParameter("appId"))));
		String idPacpath = null;
		String wordpath = null;
		String pathfile = null;
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "images");
			wordpath = session.getServletContext().getContextPath();
			System.out.println("path===============================>" + path);
			String oidFileName = attach.getOriginalFilename();
			System.out.println("原文件名===============================>" + oidFileName);
			String preFix = FilenameUtils.getExtension(oidFileName);
			System.out.println("原文件后缀================================>" + preFix);
			int filesize = 500000000;
			System.out.println("原文件大小============================>" + attach.getSize());
			if (attach.getSize() > filesize) {
				session.setAttribute("fileUploadError", "上传大小不能超出50MB");
				return "redirect:../jsp/developer/appversionadd.jsp";
			} else if (preFix.equalsIgnoreCase("apk")) {

				String fileName = System.currentTimeMillis() + ".apk";
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
					return "redirect:../jsp/developer/appversionadd.jsp";
				}
				idPacpath = path + File.separator + fileName;
				pathfile = fileName;
				wordpath = wordpath.substring(wordpath.lastIndexOf("\\") + 1);
				wordpath = wordpath.substring(1);
				wordpath = "/" + wordpath + "/statics" + File.separator + fileName;
				System.out.println("绝对路径====================>" + idPacpath);
			} else {
				session.setAttribute("fileUploadError", "上传文件格式不正确");
				return "redirect:../jsp/developer/appversionadd.jsp";
			}
		}
		dev_user user = (dev_user) session.getAttribute("devUserSession");
		version.setCreatedBy(user.getId());
		version.setCreationDate(new Date());
		version.setApkLocPath(idPacpath);
		version.setApkFileName(pathfile);
		version.setDownloadLink(wordpath);
		versionimpl.addversion(version);
		app_info app_info = appinfoimpl.selectById(version.getAppId().toString());
		if (app_info.getVersionId() == null || app_info.getVersionId().equals("")) {
			app_version versionbyId = versionimpl.selectBydateMax(Integer.toString(app_info.getId()));
			app_info.setId(versionbyId.getAppId().intValue());
			app_info.setVersionId(BigInteger.valueOf(versionbyId.getId()));
			System.out.println("vetsionID=============>" + app_info.getVersionId());
			appinfoimpl.updateByinfoapp(app_info);

		}

		return "redirect:verindex.html?id=" + version.getAppId();

	}

	/**
	 * 修改初始页面
	 * 
	 * @param aid
	 * @param vid
	 * @param version
	 * @param session
	 * @return
	 */
	@RequestMapping("updataVersionindex.html")
	public String udateVersionindex(String aid, String vid, @ModelAttribute(value = "version") app_version version,
			HttpSession session) {
		System.out.println("进入修改version==================================");
		System.out.println("进入id查询=======================================");
		List<app_version> list = versionimpl.selectByAppId(aid);
		session.setAttribute("appVersionList", list);
		app_version versionby = versionimpl.selectBydateMax(aid);
		session.setAttribute("appVersion", versionby);
		return "developer/appversionmodify";
	}

	/**
	 * 删除apk文件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "versionDeletePath", produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.GET)
	@ResponseBody
	public Object versionDeletePath(String id) {
		System.out.println("进入删除url========================");
		app_version version = versionimpl.selectBydateMax(id);
		String path = version.getApkLocPath();
		Map<String, String> map = new HashMap<String, String>();
		File file = new File(path);
		if (!file.exists()) {
			map.put("result", "success");
			return JSONArray.toJSONString(map);
		} else if (file.delete()) {
			System.out.println("删除成功");
			map.put("result", "success");
			return JSONArray.toJSONString(map);
		}

		map.put("result", "failed");
		return JSONArray.toJSONString(map);
	}

	/*
	 * 修改version
	 */
	@RequestMapping(value = "updateversion.html")
	public String updateversion(app_version version, @RequestParam(value = "attach") MultipartFile attach,
			HttpSession session, HttpServletRequest request) {
		session.removeAttribute("fileUploadError");
		String idPacpath = null;
		String wordpath = null;
		String pathfile = null;
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "images");
			wordpath = session.getServletContext().getContextPath();
			System.out.println("path===============================>" + path);
			String oidFileName = attach.getOriginalFilename();
			System.out.println("原文件名===============================>" + oidFileName);
			String preFix = FilenameUtils.getExtension(oidFileName);
			System.out.println("原文件后缀================================>" + preFix);
			int filesize = 500000000;
			System.out.println("原文件大小============================>" + attach.getSize());
			if (attach.getSize() > filesize) {
				session.setAttribute("fileUploadError", "上传大小不能超出50MB");
				return "redirect:../jsp/developer/appversionmodify.jsp";
			} else if (preFix.equalsIgnoreCase("apk")) {

				String fileName = System.currentTimeMillis() + ".apk";
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
					return "redirect:../jsp/developer/appversionmodify.jsp";
				}
				idPacpath = path + File.separator + fileName;
				pathfile = fileName;
				wordpath = wordpath.substring(wordpath.lastIndexOf("\\") + 1);
				wordpath = wordpath.substring(1);
				wordpath = "/" + wordpath + "/statics" + File.separator + fileName;
				System.out.println("绝对路径====================>" + idPacpath);
			} else {
				session.setAttribute("fileUploadError", "上传文件格式不正确");
				return "redirect:../jsp/developer/appversionmodify.jsp";
			}
		}
		dev_user user = (dev_user) session.getAttribute("devUserSession");

		version.setModifyBy(user.getId());
		version.setModifyDate(new Date());
		version.setApkLocPath(idPacpath);
		version.setApkFileName(pathfile);
		version.setDownloadLink(wordpath);
		versionimpl.updateversion(version);
		System.out.println("修改成功");
		return "redirect:../version/updataVersionindex.html?aid=" + version.getAppId() + "&vid=" + version.getId();
	}
}
