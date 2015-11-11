<%@ page language="java" import="com.board.UserDao, com.board.User" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Главная | Board</title>
	<link href="board.css" rel="stylesheet">
</head>

<body>	
	<% 
		response.sendRedirect("filter");
	%>	
</body>
</html>