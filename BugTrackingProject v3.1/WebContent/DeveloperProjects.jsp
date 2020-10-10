<!-- Author : Akanksha Shrivastava,Gautami Ravindra Rahane
	Purpose :  -->

<%@ page language="java" import="java.util.List,com.code.bean.Project,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%-- <% List<Project> projectList= (ArrayList<Project>)request.getAttribute("projectList"); %>

		<%for(Project p:projectList){ %>
		<a href="DisplayDeveloperBugs?projectId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a>
		<br></br>
		<%} %> --%>
		
<% List<Project> projectList= (ArrayList<Project>) request.getAttribute("projectList"); 
if(projectList!=null){ 
	for(Project p:projectList){ %>
		<table border="2">
			<tr>
				<th>Project Name </th>
				<th>Manager </th>
				<th>Start Date </th>
<!-- 				<th>Team Members </th> -->
				<th>Bugs</th>
			</tr>
			<tr>
				<td><%=p.getProjectName() %></td>
				<td><%=p.getManagerId() %></td>
				<td><%=p.getStartDate() %></td>
				<!-- <td></td> -->
				<td><a href="DisplayDeveloperBugs?projectId=<%=p.getProjectId()%>" >View Bugs</a></td>
			</tr>
		</table>
		<br>
		<br>
		<br></br>
<%}}else { %>
	<>h1>You are not assigned to a Project.</h1>
<% } %>

</body>
</html>