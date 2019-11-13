package dao.backend_user;

import org.apache.ibatis.annotations.Param;

import entity.backend_user;

public interface backend_user_dao {

	public backend_user selectuser(@Param("UserCoke") String UserCoke);
}
