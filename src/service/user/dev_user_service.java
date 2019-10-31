package service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.user.user_dao;
import entity.dev_user;

public interface dev_user_service {

	public dev_user userlogin(String user, String pwd);

	public List<dev_user> selectAll();
}
