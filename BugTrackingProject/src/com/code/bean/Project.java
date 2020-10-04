//Author : Akanksha Shrivastava
//Purpose : Project class containing details about the project.

package com.code.bean;

import java.util.Date;

public class Project {

	//Data members
	private int projectId;
	private String projectName;
	private String description;
	private Date startDate;
	private String status;	//In-progress/completed
	//team members column
	
	//default constructor
	public Project() {
		
		projectId = 0;
		projectName = null;
		description = null;
		startDate = null;
		status = null;
		//team members
	}
	
	//parameterized constructor
	public Project(int projectId, String projectName, String description, Date startDate, String status) {
		
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.status = status;
		//team members
	}

	//setters and getter methods
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//Overriding toString method
	@Override
	public String toString() {
		return "Project ID = " + projectId + "\nProject Name = " + projectName + "\nDescription = " + description
				+ "\nStart Date = " + startDate + "\nStatus = " + status;
	}
	
	
}
