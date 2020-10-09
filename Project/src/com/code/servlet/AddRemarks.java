/* Author : Gautami Ravindra Rahane
	Purpose : Servlet to add remarks when developer marks a bug for closing */
package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.bean.Bug;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

/**
 * Servlet implementation class AddRemarks
 */
//@WebServlet("/AddRemarks")
public class AddRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//To convert String Date to Date Date
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		
		//To get parameters from view layer
		int bugId=Integer.parseInt(request.getParameter("bugId"));
		String bugTitle=request.getParameter("bugTitle");
		String bugDescription=request.getParameter("bugDescription");
		int projectId=Integer.parseInt(request.getParameter("projectId"));
		int createdBy=Integer.parseInt(request.getParameter("createdBy"));
		int assignedTo=Integer.parseInt(request.getParameter("assignedTo")); 
		String markedForClosing=request.getParameter("marked");
		int closedBy=Integer.parseInt(request.getParameter("closedBy"));
		String status=request.getParameter("status");
		String severityLevel=request.getParameter("severityLevel");
		String remarks=request.getParameter("remarks");
		String odt=request.getParameter("openDate");
		String codt=request.getParameter("closedOn");
		
		Date openDate=null;
		Date closedOn=null;
		//To convert String date to Date date
		try {
			//Parsing String date to Date date
			openDate=sdf.parse(odt);
			//To create bugtable object and assign values received from view layer
			Bug bug=new Bug(bugId,bugTitle,bugDescription,projectId,createdBy,openDate,assignedTo,markedForClosing,closedBy,closedOn,status,severityLevel,remarks);
			//To post the changed values to service layer
			boolean flag=bugTrackService.addRemarks(bug);
			if(flag)
			{
				out.println("Bug has been marked for closing");
				out.println("Remarks added :"+bug.getRemarks());
				RequestDispatcher rd=request.getRequestDispatcher("DisplayDeveloperBugs?projectId="+projectId);
				rd.forward(request, response);
			}
			else
			{
				out.println("Couldn't mark bug for closing");
				RequestDispatcher rd=request.getRequestDispatcher("DisplayDeveloperBugs?projectId="+projectId);
				rd.forward(request, response);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
