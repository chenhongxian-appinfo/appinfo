package service.backend_user;

import entity.backend_user;

public interface backend_user_service {

	public backend_user selectByCoke(String userCoke, String pwd);
}
