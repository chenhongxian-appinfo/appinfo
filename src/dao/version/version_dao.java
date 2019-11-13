package dao.version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.app_version;

public interface version_dao {

	public List<app_version> selectById(@Param("appId") String appId);

	public int addversion(app_version version);

	public app_version selectByDateMax(@Param("appId") String appId);

	public int updateversion(app_version version);

	public app_version selecrById(@Param("id") String id);

	public int deleteById(@Param("appId") String appid);
}
