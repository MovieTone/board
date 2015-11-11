<%@ page language="java" import="com.board.UserDao, com.board.User"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Регистрация | Board</title>
	
	<link href="board.css" rel="stylesheet">
	<script type="text/javascript" src="validation.js" ></script>
</head>
<body>

	<%@ include file="headermenu.jsp" %>
	
	<div class="wrap">
		<div class="login" align="center">
			<h3>Регистрация</h3>
			<div class="login_container">
				<c:choose>
					<c:when test="${requestScope.isUsed == 'false'}">
						<h4>Имя ${requestScope.user} не занято!</h4>
						<c:choose>
							<c:when test="${requestScope.signedUp == 'true'}">
								<h4>Вы успешно зарегистрированы!</h4>
							</c:when>
							<c:otherwise>
								<div class="error">Произошла ошибка. Попробуйте еще раз!</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:if test="${requestScope.isUsed == 'true'}">
							<div class="error">Имя ${requestScope.user} занято. Попробуйте другое.</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<c:if test="${requestScope.signedUp != 'true'}">
					<form name="sign-form" method="post" action="signup" id="login-form" role="form" onsubmit="return isValid()">
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
							<button type="submit" class="send-button">Регистрация</button>
						</div>
					</form>
				</c:if>
			</div>
		</div>
	</div>
	
</body>
</html>