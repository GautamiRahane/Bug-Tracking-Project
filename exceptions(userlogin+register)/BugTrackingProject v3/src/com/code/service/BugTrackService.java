//Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut
//Purpose : Interface for business logic layer (service layer)

package com.code.service;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;

public interface BugTrackService {

	//Method to call dao layer and save imported users into db
	int importUsers(List<User> userList);
		
	//Method to get the list of all projects acc. to user ID
	List<Project> getAllProjects(int userid);
	
	//Method to get the list of bugs acc. to user ID and project ID
	List<Bug> getAllBugs(int projectid);

	//Method to get the list of developers and testers under a manager
	List<User> getUsersByManager(Integer managerId);

	List<Project> getAllPMProjects(int managerid);

	boolean closeBug(int bugId);
	
	boolean assignDev(int bugId, int managerId);

	int addBug(Bug bug);

	//Method to get all free employees
	List<User> getAllEmployees();
	
	//To add a new project
	boolean addProject(Project project);
	
	//To update the number of projects under a user
	void updateNoOfProjects(int[] userIdChecked);
	
	//To users to Team
	void addToTeam(String projectName,int[] userIdChecked,int managerid);

	//Method to add remarks to the bug and mark it for closing
		boolean addRemarks(Bug bug);

		List<Bug> getAllDevBugs(int projectId, int userId);

	

}
