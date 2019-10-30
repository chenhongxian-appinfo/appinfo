package service.appinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.appinfo.appinfo_dao;

@Service
public class appinfo_service_impl implements appinfo_service {

	@Resource
	appinfo_dao dao;

	@Override
	public int selectInfoCountAll() {
		int num = dao.AppinfocountAll();
		return num;
	}

}
