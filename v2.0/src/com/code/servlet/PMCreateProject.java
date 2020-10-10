//author-hrishita,dharampreet
//for creating a new project
package com.code.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.code.bean.User;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

//@WebServlet("/createProject")
public class PMCreateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session=request.getSession();
			PrintWriter out=response.getWriter();
			String role=(String) session.getAttribute("type");
			if(role!=null && role.equals("project manager")) {
				User user = (User) session.getAttribute("user");
				Integer managerId=user.getUserId();
				BugTrackService bService=new BugTrackServiceImpl();
				List<User> ulist=new ArrayList<>();
				ulist=bService.getUsersByManager(managerId);
				request.setAttribute("ulist",ulist);
				//call to create project
				RequestDispatcher rd=request.getRequestDispatcher("PMcreateproject.jsp");
				rd.forward(request, response);
				
				
			}
			
			else {
				out.println("you are not autherized user");
				//RequestDispatcher rd=request.getRequestDispatcher(homepageaddress);
				session.invalidate();
				//rd.include(request, response);
				//out.println("testing");
				
				
			}

	}
}
