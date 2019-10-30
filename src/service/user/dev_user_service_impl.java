package service.user;

import javax.annotation.Resource;

import dao.user.user_dao;
import entity.dev_user;

public class dev_user_service_impl implements dev_user_service {

	@Resource
	user_dao dao;

	@Override
	public dev_user userlogin(String username, String pwd) {
		dev_user user = dao.selectbyUsername(username);
		if (user == null) {
			return null;
		}
		if (pwd.equals(user.getDevPassword())) {

			return user;
		}
		return null;
	}

}
