package service.version;

import java.util.List;

import entity.app_info;
import entity.app_version;

public interface version_service {

	public List<app_version> selectByAppId(String id);

	public int addversion(app_version version);

	public app_version selectBydateMax(String appId);

	public boolean updateversion(app_version version);

	public app_version selectById(String id);
}