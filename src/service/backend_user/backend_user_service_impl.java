package service.backend_user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.backend_user.backend_user_dao;
import entity.backend_user;

@Service
public class backend_user_service_impl implements backend_user_service {

	@Resource
	backend_user_dao dao;

	@Override
	public backend_user selectByCoke(String userCoke, String pwd) {
		
		backend_user user = dao.selectuser(userCoke);
		if (user != null) {
			if (user.getUserPassword().equals(pwd)) {
				return user;
			}
		}
		return null;
	}

}
