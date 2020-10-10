<%@ page language="java" import="java.util.List,com.code.bean.Bug,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% List<Bug> bugList= (ArrayList<Bug>)request.getAttribute("bugList"); %>
<!-- <form action="viewproduct">-->
	<!-- <select name="cat"> <!-- the jsp will send this data, with this name 'cat' -->
	<table>
	<tr><th>Bug ID</th><th>Bug Title</th><th>Description</th><th>Project ID</th><th>Created By</th><th>Open Date</th><th>Assigned To</th>
	<th>Marked For Closing</th><th>Closed By</th><th>Closed On</th><th>Status</th><th>Severity Level</th><th>Task</th></tr>
		<%for(Bug bug:bugList){ %>
		<tr><td><%=bug.getBugId() %></td><td><%=bug.getBugTitle() %></td><td><%=bug.getBugDescription()%></td><td><%=bug.getProjectId()%></td>
		<td><%=bug.getCreatedBy()%></td><td><%=bug.getOpenDate()%></td><td><%=bug.getAssignedTo()%></td><td><%=bug.getMarkedForClosing()%></td>
		<td><%=bug.getClosedBy()%></td><td><%=bug.getClosedOn()%></td><td><%=bug.getStatus()%></td><td><%=bug.getSeverityLevel()%></td>
		<td><a href = "#">Assign</a>/<a href = "#">Close</a></td></tr>
		<%} %>
	</table>
</body>
</html>