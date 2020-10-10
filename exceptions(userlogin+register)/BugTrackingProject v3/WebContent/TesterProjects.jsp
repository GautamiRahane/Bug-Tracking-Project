<!-- Author : Akanksha Shrivastava,Bharadwaj Divate
	Purpose :  -->

<%@ page language="java" import="java.util.List,com.code.bean.Project,java.util.ArrayList,com.code.bean.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%User user = (User) session.getAttribute("user"); %>
 Name = <%=user.getName() %>
 Role = <%=user.getType() %>
 Email = <%=user.getEmail() %>
<% List<Project> projectList= (ArrayList<Project>)request.getAttribute("projectList"); %>
		<%if (projectList ==null){%>
		<p> You are not assigned to any project</p>
		<%}else{ %>
		
		<%for(Project p:projectList){ %>
		
		<a href="DisplayTesterBugs?projectId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a>/
		<a href= "reportbug?projectId=<%=p.getProjectId()%>&createdBy=<%=user.getUserId()%>">Report new bug</a>
		<br></br>
		<%} }%>

</body>
</html>