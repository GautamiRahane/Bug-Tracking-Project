//Author Ankit Bhadoriya
package com.code.service;

import com.code.dao.RegisterDao;
import com.code.dao.RegisterDaoImpl;
import com.code.exception.UserNotFoundException;

public class RegisterServiceImpl implements RegisterService{
	RegisterDao ob=new RegisterDaoImpl();

	
	
	@Override
	public boolean createPassword(String pass,String email,int userid) throws UserNotFoundException {
		return ob.storePassword(pass,email,userid);
	}

	@Override
	public int validateEmail(String email, String role) throws UserNotFoundException {
		return ob.emailExistValidation(email,role);
	}
	

}
