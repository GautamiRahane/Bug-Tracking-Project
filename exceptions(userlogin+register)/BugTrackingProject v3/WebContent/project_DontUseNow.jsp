<%@ page language="java" import="java.util.List,com.code.bean.Project,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% List<Project> projectList= (ArrayList<Project>)request.getAttribute("projectList"); %>
<!-- <form action="viewproduct">-->
	<!-- <select name="cat"> <!-- the jsp will send this data, with this name 'cat' -->
		<%for(Project p:projectList){ %>
		<a href="DisplayBugServlet?projectId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a><br>
		<%} %>
	
	
<!-- </form>-->

</body>
</html>