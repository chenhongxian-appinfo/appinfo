package dao.version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.app_version;

public interface version_dao {

	public List<app_version> selectById(@Param("appId") String appId);
}
