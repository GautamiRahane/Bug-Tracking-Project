// Authors : Hrushikesh
// Purpose : class for service layer
package com.code.service;

import com.code.bean.User;
import com.code.dao.UserDao;
import com.code.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
	//authenticate user using userName and password
	@Override
	public User validateUser(String userName, String password)  {
		return userDao.validateUser(userName, password);
	}

}
