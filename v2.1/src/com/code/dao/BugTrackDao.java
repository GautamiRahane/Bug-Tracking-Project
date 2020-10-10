// Authors : Akanksha Shrivastava,Adrija Ghansiyal, Abhijeet Nitin Raut,dharampreet
// Purpose : Interface for database layer (dao layer)
package com.code.dao;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;

public interface BugTrackDao {

	//function to import users from a JSON file
	int importUsers(List<User> userList);

	//function to list all projects under a user
	List<Project> getAllProjects(int userid);

	//function to list the bugs in a project acc. to user
	List<Bug> getAllBugs(int projectid);
	
	//function to list users under a manager
	List<User> getUsersByManager(Integer managerId);

	List<Project> getAllPMProjects(int managerid);

	boolean closeBug(int bugId);

	boolean assignDev(int bugId, int managerId);

	int addNewBug(Bug bug);

	

}
