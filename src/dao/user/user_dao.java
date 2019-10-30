package dao.user;

import org.apache.ibatis.annotations.Param;

import entity.dev_user;

public interface user_dao {

	public dev_user selectbyUsername(@Param("userinfo") String user);
}
