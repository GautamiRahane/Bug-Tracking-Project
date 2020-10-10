package com.code.dao;

public interface RegisterDao {

<<<<<<< HEAD
	int emailExistValidation(String email, String role);

	boolean storePassword(String pass,String email, int userid);
=======
	boolean emailExistValidation(String email, String role);

	boolean storePassword(String pass,String email);
>>>>>>> e689bf121814b774e8ea0005b583ea56cd1cf23e

}
