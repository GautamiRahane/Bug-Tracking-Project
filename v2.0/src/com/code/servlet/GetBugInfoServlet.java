//Author - Bharadwaj Divate
package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
 * Servlet implementation class AddBugServlet
 */
//@WebServlet("/addbug")
public class GetBugInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static SimpleDateFormat sdf;

	public void init() {
		sdf = new SimpleDateFormat("dd/mm/yyyy");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("type");
		if (role != null && role.equals("tester")) {
			int projId = Integer.parseInt(request.getParameter("pid"));
			String title = request.getParameter("title");
			String descrp = request.getParameter("description");

			int createdBy = Integer.parseInt(request.getParameter("cby"));
			String openDate = request.getParameter("dop");
			//Date openDate=new Date();
			String status = request.getParameter("status");
			String severity = request.getParameter("severity");
			Bug bug = new Bug();
			//bug.setBugId(0);
			bug.setBugTitle(title);
			bug.setBugDescription(descrp);
			bug.setProjectId(projId);
			bug.setCreatedBy(createdBy);
			bug.setStatus(status);
			bug.setSeverityLevel(severity);
			DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
			
			try {
				Date opDate=formatter.parse(openDate);
				bug.setOpenDate(opDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
//			try {
//				opDate = sdf.parse(openDate);
//				bug.setOpenDate(opDate);
//
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}

			BugTrackService bugTrack = new BugTrackServiceImpl();
			int n = bugTrack.addBug(bug);
			if (n > 0) {
				out.println("Bug reported");
			}
			RequestDispatcher rd = request.getRequestDispatcher("DisplayTesterProjectsServlet");
			rd.include(request, response);

		} else {
			out.print("<h4>you are not authorised to view this page</h4>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}

	}
}