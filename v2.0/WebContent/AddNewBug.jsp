<%@ page language="java" import="com.code.bean.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addbug" method = "get">
<table>
<%int projectId = (Integer)request.getAttribute("pid"); 
//int createdby = (Integer)request.getAttribute("cby"); 
User user = (User) session.getAttribute("user");%>

<tr><td>Project ID : </td><td><input type="number" name="pid" id="pid" value="<%=projectId %>"  placeholder="Enter Project ID"></td>
<tr><td>Title : </td><td><input type="text" name="title" id="title" required placeholder="Enter Title"></td>
<tr><td>Description : </td><td><textarea rows="5" cols="20" name="description" id="description" required placeholder="Enter Bug description"></textarea></td>

<tr><td>Created by : </td><td><input type="number" name="cby" id="cby" value="<%=user.getUserId() %>"></td>
<tr><td>Open Date : </td><td><input type="date" name="dop" id="dop" required placeholder="Enter Date of opening"></td>

<tr>
<td>Status : </td>
<td><input type="radio" name="status" id="open" value="open">Open
<input type="radio" name="status" id="closed" value="closed">Close
</td>
</tr>
<tr>
<td>Severity : </td>
<td><input type ="radio" name="severity" id="critical" value="critical">Critical
<input type ="radio" name="severity" id="major" value="major">Major
<input type ="radio" name="severity" id="minor" value="minor">Minor
<input type ="radio" name="severity" id="trivial" value="trivial">Trivial
</td>
</tr>
<tr><td></td><td><button type="submit" name ="btn" id="sub" value="reg"> Register Bug</button>
<button type="reset" name ="btn" id="reset" value="cancel"> Cancel</button>
</td></tr>

</table>


</form>
</body>
</html>