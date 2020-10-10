//Author Ankit Bhadoriya
package com.code.dao;

import com.code.exception.UserNotFoundException;

public interface RegisterDao {

	boolean storePassword(String pass,String email, int userid) throws UserNotFoundException;

	int emailExistValidation(String email, String role) throws UserNotFoundException;



}
