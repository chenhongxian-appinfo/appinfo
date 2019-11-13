package dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.dev_user;

public interface user_dao {

	public dev_user selectbyUsername(@Param("userinfo") String user);

	public List<dev_user> selectAll();
	
	
}
