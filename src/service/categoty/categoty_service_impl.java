package service.categoty;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.category.App_categort_dao;
import entity.app_category;

@Service
public class categoty_service_impl implements category_service {
	@Resource
	App_categort_dao dao;

	@Override
	public List<app_category> selectType1() {
		List<app_category> list = dao.selectType1();
		return list;
	}

	@Override
	public List<app_category> selectTypebyId(String id) {
		List<app_category> list = dao.selectTypebyid(id);
		return list;
	}

}
