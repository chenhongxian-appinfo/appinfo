package service.appinfo;

import java.util.List;

import entity.AppInfoSelect;
import entity.AppInfoToday;
import entity.app_info;

public interface appinfo_service {

	public int selectInfoCountAll();

	public List<app_info> AppinfoTodayPage(AppInfoSelect appinfoselect, AppInfoToday page);
}
