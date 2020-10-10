<%@ page language="java" import="com.code.bean.*,java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addproject" method="get">
<table>
  
  <tr><td>Project Name : </td><td><input type="text" name="projectName" id="name" minlength="3"  required placeholder="Enter project name"></td><td></td></tr>
  <tr><td>Start date : </td><td><input type="date" name="startDate" id="dt" min="2020-09-05"  value="2020-09-25" required placeholder="yyyy-mm-dd"></td><td></td></tr>
  <tr><td>Description :</td><td><textarea rows="3" cols="20" name="description" id="desc"></textarea></td></tr>
  <%List<User> ulist=(ArrayList<User>)request.getAttribute("ulist");%>
<table border="2"><br>
<tr><th>UserId</th><th>User Name</th><th>Email Id</th><th>Role</th><th>Select Employee</th>
<%for(User u:ulist){ %>   
<tr><td><%=u.getUserId()%></td>
<td><%=u.getName()%></td>
 <td><%=u.getEmail()%></td>
  <td><%=u.getType()%></td>
  	 <td>
    	<input type="checkbox" id="userId" name="userId" value="<%= u.getUserId()%>">
    	
   </td>
  
</tr>
<%} %>
</table>
<button type="submit">Submit</button>
</form>
</body>
</html>