<%@ page language="java" import="com.board.UserDao, com.board.User"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Вход | Board</title>
	
	<link href="board.css" rel="stylesheet">
	<script type="text/javascript" src="validation.js" ></script>
</head>
<body>

	<%@ include file="headermenu.jsp" %>
	
	<div class="wrap">
		<div class="login">
			<h3 align="center">Вход на сайт</h3>
			<div class="login_container">
				<c:choose>
					<c:when test="${requestScope.isValid == 'true'}">
						<div>${requestScope.user}, добро пожаловать!</div>
					</c:when>
					<c:otherwise>
						<c:if test="${requestScope.isValid == 'false'}">
							<div class="error">Неверное имя пользователя или пароль. Проверьте правильность введенных данных.</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<form name="sign-form" method="post" action="login" id="login-form" role="form" onsubmit="return isValid()">
					<div class="form-group">
						<label for="login_user">Имя:</label>
						<input required minlength="5" maxlength="40" type="text" name="user" id="login_user"></input>	
						<div class="clear">&nbsp;</div>
					</div>
					<div class="form-group">
						<label for="login_pass">Пароль:</label>
						<input required minlength="6" type="password" name="password" id="login_pass"></input>
					</div>
					<div class="clear">&nbsp;</div>
					<div class="form-group">
						<button type="submit" class="send-button">Вход</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>