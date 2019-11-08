package service.version;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.version.version_dao;
import entity.app_info;
import entity.app_version;

@Service
public class version_service_impl implements version_service {
	@Resource
	version_dao dao;

	@Override
	public List<app_version> selectByAppId(String id) {
		List<app_version> list = dao.selectById(id);
		return list;
	}

	@Override
	public int addversion(app_version version) {
		int num = dao.addversion(version);
		return num;
	}

	@Override
	public app_version selectBydateMax(String appId) {
		app_version version = dao.selectByDateMax(appId);
		return version;
	}

	@Override
	public boolean updateversion(app_version version) {
		boolean yes = false;
		if (dao.updateversion(version) >= 1) {
			yes = true;
		}
		return yes;
	}

	@Override
	public app_version selectById(String id) {
		app_version version = dao.selecrById(id);
		return version;
	}

}
