package com.code.service;

public interface RegisterService {


	int validateEmail(String email, String role);

	boolean createPassword(String pass,String email,int userid);



}
