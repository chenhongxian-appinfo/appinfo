package dao.appinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppInfoSelect;
import entity.AppInfoToday;
import entity.app_info;

public interface appinfo_dao {

	public int AppinfocountAll();

	public List<app_info> selectToday(@Param("appinfoselect") AppInfoSelect appinfoselect,
			@Param("page") AppInfoToday page);
}
