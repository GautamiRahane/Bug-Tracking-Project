package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.RegisterService;
import com.code.service.RegisterServiceImpl;


public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    RegisterService registerService=new RegisterServiceImpl();
   

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		//taking values from html
		String email=request.getParameter("email");
		String role=request.getParameter("role");
		String pass=request.getParameter("pass");
		System.out.println(role);
		boolean flag=false;
		int userid=registerService.validateEmail(email,role);
		//method to check if the user email and role is imported 
		if(userid!=0) {
			System.out.println("email verfied");
		//create password method called to create new password for imported user
			flag=registerService.createPassword(pass,email,userid);
		

			if(flag) {
				out.println("<br>User Resgistered Sucessfully");
				out.println("<br>Your Password has been Successfully Created");
				//request dispatcher to redirect to login page after successfull registration
				RequestDispatcher rd1=request.getRequestDispatcher("login.jsp");
				rd1.include(request, response);
			}
			
			
		}
		else {
			//request dispatcher to redirect to login page after unsuccessfull registration
			out.println("<br>User not found");
			RequestDispatcher rd1=request.getRequestDispatcher("Registration.html");
			rd1.include(request, response);
		}
		
		
		
	}

}
