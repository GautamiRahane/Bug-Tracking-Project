package com.code.service;

import java.sql.SQLException;

import com.code.bean.User;
import com.code.dao.UserDao;
import com.code.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
	@Override
	public User validateUser(String userName, String password) throws SQLException {
		return userDao.validateUser(userName, password);
	}

}
