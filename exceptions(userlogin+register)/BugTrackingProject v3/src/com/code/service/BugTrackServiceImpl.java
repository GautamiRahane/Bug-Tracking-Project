//Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut,Hrishita Bhattacharjee
// Purpose : Provide business logic to the bug tracking service

package com.code.service;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.dao.BugTrackDao;
import com.code.dao.BugTrackDaoImpl;

public class BugTrackServiceImpl implements BugTrackService{

	private BugTrackDao bugTrackDao;
	//initialize the bugTrackDao object
	public BugTrackServiceImpl(){
		super();
		bugTrackDao = new BugTrackDaoImpl();
		
	}
	
		
	//to get all projects under a user id<-----called in DisplayProjectServlet
	@Override
	public List<Project> getAllProjects(int userid) {
		return bugTrackDao.getAllProjects(userid);
	}
	//to get all projects under a manager<-----called in DisplayPMProjectsServlet
	@Override
	public List<Project> getAllPMProjects(int managerid) {
		return bugTrackDao.getAllPMProjects(managerid);
	}

	// Function to save imported users into database
	@Override
	public int importUsers(List<User> userList) {
		return bugTrackDao.importUsers(userList);
	}


	// Function to get the bug List of a project 
	@Override
	public List<Bug> getAllBugs(int projectid) {
		return bugTrackDao.getAllBugs(projectid);
	}

	//to get team under a manager
	@Override
	public List<User> getUsersByManager(Integer managerId) {
		return bugTrackDao.getUsersByManager(managerId);
	}


	//close the bug 
	@Override
	public boolean closeBug(int bugId) {
		return bugTrackDao.closeBug(bugId);
	}


	@Override
	public boolean assignDev(int bugId, int managerId) {
		return bugTrackDao.assignDev(bugId, managerId);
	}


	//function to report/add a bug to database	
	@Override
	public int addBug(Bug bug) {
		// TODO Auto-generated method stub
		return bugTrackDao.addNewBug(bug);
	}


	//To get all free employees
	@Override
	public List<User> getAllEmployees(){
		return bugTrackDao.getAllEmployees();
	}
	
	//To add a new project created by the project manager
	@Override
	public boolean addProject(Project project) {
		return bugTrackDao.addProject(project);
	}
	
	//To update the number of projects of a particular employee
	@Override
	public void updateNoOfProjects(int[] userIdchecked) {
		bugTrackDao.updateNoOfProjects(userIdchecked);
	}


	@Override
	public void addToTeam(String projectName, int[] userIdChecked,int managerid) {
		bugTrackDao.addUsersToProject(projectName,userIdChecked,managerid);
		
	}


	//Function to add remarks to the bug and mark it for closing
		@Override
		public boolean addRemarks(Bug bug) {
			return bugTrackDao.addRemarks(bug);
			
		}


		@Override
		public List<Bug> getAllDevBugs(int projectId,int userId) {
			return bugTrackDao.getAllDevBugs(projectId,userId);
		}
	
	
}
