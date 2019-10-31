package service.appinfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.appinfo.appinfo_dao;
import entity.AppInfoSelect;
import entity.AppInfoToday;
import entity.app_info;

@Service
public class appinfo_service_impl implements appinfo_service {

	@Resource
	appinfo_dao dao;

	@Override
	public int selectInfoCountAll() {
		int num = dao.AppinfocountAll();
		return num;
	}

	@Override
	public List<app_info> AppinfoTodayPage(AppInfoSelect appinfoselect, AppInfoToday page) {
		List<app_info> list = dao.selectToday(appinfoselect, page);
		return list;
	}

}
