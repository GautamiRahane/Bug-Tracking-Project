//Author Ankit Bhadoriya
package com.code.service;

import com.code.exception.UserNotFoundException;

public interface RegisterService {


	int validateEmail(String email, String role) throws UserNotFoundException;

	boolean createPassword(String pass,String email,int userid) throws UserNotFoundException;



}
