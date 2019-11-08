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

	@Override
	public app_info selectById(String id) {
		app_info app = dao.selectByid(id);
		return app;
	}

	@Override
	public String selectByName(String name) {
		if (name == "" || name == null) {
			return "empty";
		}

		app_info app = dao.selectByName(name);
		if (app != null) {
			return "exist";
		}
		return "noexist";
	}

	@Override
	public int addinfo(app_info app) {
		int num = dao.addAppinfo(app);
		return num;
	}

	@Override
	public boolean updateByinfoapp(app_info app) {
		boolean yes = false;
		if (dao.updateVersionByid(app) >= 1) {
			yes = true;
		}
		return yes;
	}

}
