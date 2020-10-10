<%@ page language="java" import="com.code.bean.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
		String type = (String) session.getAttribute("type");
		if (!type.equals("project manager")) {
			response.sendRedirect("http://localhost:8080/BugTrackingProject/login.jsp");
		}
		User user = (User) session.getAttribute("user");
	%>
	<h2>Display project manager page here (links etc.)</h2>
	<a href="CreateProject">Create Project  </a>&nbsp;
	<a href="DisplayPMProjects?managerid=<%= user.getUserId() %>"> Project Details </a>

	<a href="#">Log Out</a>
</body>
</html>