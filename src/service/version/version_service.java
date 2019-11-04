package service.version;

import java.util.List;

import entity.app_version;

public interface version_service {

	public List<app_version> selectById(String id);
}
