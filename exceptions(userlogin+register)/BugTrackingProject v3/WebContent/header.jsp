<%@page import="com.code.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	/* response.setHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); */
	User user = null;
	String type = (String) session.getAttribute("type");
	if (type != null && type.equals("developer") || type.equals("project manager") || type.equals("tester")) {
		user = (User) session.getAttribute("user");
	} else {
		response.sendRedirect("http://localhost:8080/BugTrackingProject/login.jsp");
	}
	%>
	<h2>user details</h2>
	<div style="width: 100%">
		<h4><%=user.getName()%></h4>
		<p></p>
		<h5><%=user.getEmail()%></h5>
	</div>

</body>
</html>