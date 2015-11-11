<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="board.css" rel="stylesheet">
</head>
<body>
	<div class="top_container">
		<div class="header_menu_left">
			<a href="filter">Все объявления</a> | 
			<a href="userads">Мой кабинет</a>
		</div>
		<div class="header_menu_right">
			<c:choose>
				<c:when test="${requestScope.loggedIn == 'false'}">
					<a href="login">Вход</a>
				</c:when>
				<c:otherwise>
					Вы вошли как ${sessionScope.user} | <a href="logout">Выйти</a>
				</c:otherwise>
			</c:choose>		
		</div>
	</div>
</body>	
</html>