package dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.app_category;

public interface App_categort_dao {

	public List<app_category> selectType1();

	public List<app_category> selectTypebyid(@Param("id") String id);

}
