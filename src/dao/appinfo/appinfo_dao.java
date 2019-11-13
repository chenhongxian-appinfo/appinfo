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

	public int AppinfoByselect(@Param("appinfoselect") AppInfoSelect appinfoselect);

	public app_info selectByid(@Param("id") String id);

	public app_info selectByName(@Param("Name") String Name);

	public int addAppinfo(app_info app);

	public int updateVersionByid(app_info app);

	public app_info selectByidContext(@Param("id") String id);

	public int deleteById(@Param("id") String id);
}