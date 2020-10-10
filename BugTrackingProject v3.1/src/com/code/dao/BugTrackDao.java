// Authors : Akanksha Shrivastava,Adrija Ghansiyal, Abhijeet Nitin Raut,dharampreet
// Purpose : Interface for database layer (dao layer)
package com.code.dao;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.exception.*;

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

	boolean closeBug(int bugId) throws BugNotFoundException;

	boolean assignDev(int bugId, int managerId)throws UserNotFoundException;

	void addNewBug(Bug bug) throws ErrorInExecution;

	void updateNoOfProjects(int[] userIdChecked) ;

	List<User> getAllEmployees(int mgrId);

	boolean addProject(Project p) throws ErrorInExecution;

	void addUsersToProject(String projName, int[] userIdChecked, int managerid) throws ErrorInExecution;

	//Function to add remarks to the bug and mark it for closing
	boolean addRemarks(Bug bug) throws ErrorInExecution;

	List<Bug> getAllDevBugs(int projectId, int userId) throws ErrorInExecution;

	

}
