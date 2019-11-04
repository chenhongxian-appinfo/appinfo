package service.version;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.version.version_dao;
import entity.app_version;

@Service
public class version_service_impl implements version_service {
	@Resource
	version_dao dao;

	@Override
	public List<app_version> selectById(String id) {
		List<app_version> list = dao.selectById(id);
		return list;
	}

}
