package com.code.dao;

public interface RegisterDao {

	boolean storePassword(String pass,String email, int userid);

	int emailExistValidation(String email, String role);



}
