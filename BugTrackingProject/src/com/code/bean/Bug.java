//Author : Akanksha Shrivastava
//Purpose : Bug class that stores info about bugs.

package com.code.bean;

import java.util.Date;

public class Bug {

	//data members
	private int bugId;
	private String bugTitle;
	private	String bugDescription;
	private String createdBy;
	private Date openDate;
	private String assignedTo;
	private boolean markedForClosing;
	private String closedBy;
	private Date closedOn;
	private String status;	// open/closed
	private String severityLevel;	// critical/major/minor/trivial
	
	//default constructor
	public Bug() {
		
		bugId = 0;
		bugTitle = null;
		bugDescription = null;
		createdBy = null;
		openDate = null;
		assignedTo = null;
		markedForClosing = false;
		closedBy = null;
		closedOn = null;
		status = "closed";
		severityLevel = "trivial";
	}
	
	//parameterized constructor
	public Bug(int bugId, String bugTitle, String bugDescription, String createdBy, Date openDate, String assignedTo,
			boolean markedForClosing, String closedBy, Date closedOn, String status, String severityLevel) {
		super();
		this.bugId = bugId;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.createdBy = createdBy;
		this.openDate = openDate;
		this.assignedTo = assignedTo;
		this.markedForClosing = markedForClosing;
		this.closedBy = closedBy;
		this.closedOn = closedOn;
		this.status = status;
		this.severityLevel = severityLevel;
	}

	//setter and getter methods
	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isMarkedForClosing() {
		return markedForClosing;
	}

	public void setMarkedForClosing(boolean markedForClosing) {
		this.markedForClosing = markedForClosing;
	}

	public String getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}

	//Overriding toString method
	@Override
	public String toString() {
		return "Bug ID = " + bugId + "\nBug Title = " + bugTitle + "\nBug Description = " + bugDescription + "\nCreated By = "+ createdBy + "\nOpen Date = " + openDate + "\nAssigned To = " + assignedTo + "\nMarked For Closing = "+ markedForClosing + "\nClosed By = " + closedBy + "\nClosed On = " + closedOn + "\nStatus = " + status
				+ "\nSeverity Level = " + severityLevel;
	}	
}
