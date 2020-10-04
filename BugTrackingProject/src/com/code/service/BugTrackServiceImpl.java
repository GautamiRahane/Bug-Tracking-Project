//Author : Akanksha Shrivastava,
// Purpose : 

package com.code.service;

import java.util.List;

import com.code.bean.User;
import com.code.dao.BugTrackDao;
import com.code.dao.BugTrackDaoImpl;

public class BugTrackServiceImpl implements BugTrackService{
	BugTrackDao bugTrackDao;
	public BugTrackServiceImpl() {
		bugTrackDao = new BugTrackDaoImpl();
	}

	// Function to save imported users into database
	@Override
	public int importUsers(List<User> userList) {
		return bugTrackDao.importUsers(userList);
	}
	
}
