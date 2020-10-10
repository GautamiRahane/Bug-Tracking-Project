// Authors : Hrushikesh
// Purpose : Interface for database layer (dao layer)
package com.code.dao;

import com.code.bean.User;

public interface UserDao {

	User validateUser(String userName, String password) ;

}
