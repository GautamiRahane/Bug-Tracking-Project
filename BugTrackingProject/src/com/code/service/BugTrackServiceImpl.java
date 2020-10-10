//Author : Akanksha Shrivastava, Abhijeet Nitin Raut, Adrija Ghansiyal
// Purpose : 

package com.code.service;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
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
	
	BugTrackDao bugTrackDao = new BugTrackDaoImpl();
	
	// Function to save imported users into database
		@Override
		public int importUsers(List<User> userList) {
			return bugTrackDao.importUsers(userList);
		}
	
	// Function to get project list
		@Override
		public List<Project> getAllProjects(int userid) {
			
			return bugTrackDao.getAllProjects(userid);
		}

	// Function to get the bug List
		@Override
		public List<Bug> getAllBugs(int projectid) {
			
			return bugTrackDao.getAllBugs(projectid);
		}
}