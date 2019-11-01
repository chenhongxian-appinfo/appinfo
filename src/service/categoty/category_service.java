package service.categoty;

import java.util.List;

import entity.app_category;

public interface category_service {

	public List<app_category> selectType1();

	public List<app_category> selectTypebyId(String id);

}
