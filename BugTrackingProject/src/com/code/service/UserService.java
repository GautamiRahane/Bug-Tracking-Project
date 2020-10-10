// Authors : Hrushikesh
// Purpose : Interface for service layer 
package com.code.service;

import com.code.bean.User;

public interface UserService {

	User validateUser(String userName, String password) ;

}
