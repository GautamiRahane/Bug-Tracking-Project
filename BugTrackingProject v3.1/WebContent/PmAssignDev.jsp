<!-- Author : Adrija, Dharampreet-->
<%@ page language="java" import="java.util.List,com.code.bean.User,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<% List<User> userlist= (ArrayList<User>)request.getAttribute("userlist"); 
	int bugId=(int)request.getAttribute("bugId");%>
<table>
<tr><th>Developer Name</th><th></th></tr>
<%for(User user:userlist){ %>

<tr><td><a href="AssignDev?userId=<%=user.getUserId()%>&bugId=<%=bugId%>"><%=user.getName()%></a></td></tr>
<%} %>

</table>


</body>
</html>