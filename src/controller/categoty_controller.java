package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import entity.app_category;
import service.categoty.category_service;

@Controller
@RequestMapping("/categoty")
public class categoty_controller {

	@Resource
	category_service categoryimpl;

	@RequestMapping(value = "/type", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String Type(String pid) {
		System.out.println("½øÈëjson===========================================");
		List<app_category> list = categoryimpl.selectTypebyId(pid);
		String count = "";
		count = JSONArray.toJSONString(list);
		System.out.println(count);
		return count;
	}
}
