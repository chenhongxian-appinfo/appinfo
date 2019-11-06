package controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import entity.app_category;
import entity.data_dictionary;
import service.categoty.category_service;
import service.data_dictionary.data_dictionary_service;

@Controller
@RequestMapping("/dictionary")
public class dictionary_controller {

	@Resource
	data_dictionary_service divtionary;

	@Resource
	category_service categoryimpl;

	@RequestMapping(value = "/cptype", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object selectPc() {

		List<data_dictionary> list = divtionary.selectCptype();
		return JSONArray.toJSONString(list);
	}

	@RequestMapping(value = "/type1", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object selectType() {

		List<app_category> list = categoryimpl.selectType1();

		return JSONArray.toJSONString(list);
	}
	
	
	

}
