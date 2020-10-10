package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;
import java.util.*;
import com.code.bean.User;


//@WebServlet("/createproject")
public class CreateProject_not_using extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//HttpSession session=request.getSession();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();	
		List<User> userList=bugTrackService.getAllEmployees();
		request.setAttribute("userList", userList);
		RequestDispatcher rd=request.getRequestDispatcher("CreateProject.jsp"); //to display the list of team members in html
		rd.forward(request, response);
			
			/*else {
				out.println("you are not autherized user");
				RequestDispatcher rd=request.getRequestDispatcher(homepageaddress);
				session.invalidate();
				rd.include(request, response);
				out.println("testing");
				
				
			}*/
	}

}
