package com.code.service;

import com.code.dao.RegisterDao;
import com.code.dao.RegisterDaoImpl;

public class RegisterServiceImpl implements RegisterService{
	RegisterDao ob=new RegisterDaoImpl();

	
	
	@Override
	public boolean createPassword(String pass,String email,int userid) {
		return ob.storePassword(pass,email,userid);
	}

	@Override
	public int validateEmail(String email, String role) {
		return ob.emailExistValidation(email,role);
	}
	

}
