package com.code.service;

import com.code.dao.RegisterDao;
import com.code.dao.RegisterDaoImpl;

public class RegisterServiceImpl implements RegisterService{
	RegisterDao ob=new RegisterDaoImpl();
<<<<<<< HEAD
	
	
	@Override
	public int validateEmail(String email, String role) {
		return ob.emailExistValidation(email,role);
	}
	
	
	@Override
	public boolean createPassword(String pass,String email,int userid) {
		return ob.storePassword(pass,email,userid);
=======
	@Override
	public boolean validateEmail(String email, String role) {
		return ob.emailExistValidation(email,role);
	}
	@Override
	public boolean createPassword(String pass,String email) {
		return ob.storePassword(pass,email);
>>>>>>> e689bf121814b774e8ea0005b583ea56cd1cf23e
	}

}
