<%@ page language="java" import="com.board.UserDao, com.board.User" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Новое объявление | Board</title>
	<link href="board.css" rel="stylesheet">
</head>
<body>

	<%@ include file="headermenu.jsp" %>
		
	<div class="create_wrap">
		<div class="create_ad_container">
			<h2 align="center">Новое объявление</h2>
			<c:choose>
				<c:when test="${requestScope.loggedIn == 'false'}">
					<div align="center">
						<a href="login">Войдите</a>, чтобы иметь возможность управлять своими объявлениями
					</div>
				</c:when>
				<c:otherwise>
					<form method="post" action="create_checkout" id="create-form" role="form">
						<div class="form-group">
							<label for="user">Имя:</label>
							<input required readonly type="text" maxlength="40" name="name" value="${sessionScope.user}" id="user"/>
							<div class="clear">&nbsp;</div>
						</div>
						<div class="form-group">
							<label for="headline">Заголовок:</label>
							<input required type = "text" minlength="10" maxlength="30" name = "headline" id="headline"></input>
							<div class="clear">&nbsp;</div>
						</div>
						<div class="form-group">
							<label for="rubric">Рубрика:</label>
							<select required size = "1" name = "rubric" id="rubric"> 
								<option selected disabled></option>
								<option value = "Продажа">Продажа</option>
								<option value = "Покупка">Покупка</option>
								<option value = "Аренда">Аренда</option>
								<option value = "Услуги">Услуги</option>
								<option value = "Знакомства">Знакомства</option>
								<option value = "etc">etc</option>
							</select>
							<div class="clear">&nbsp;</div>
						</div>
						<div class="form-group">
							<label for="text">Текст:</label>
							<textarea required rows = "5" cols = "30" minlength="20" maxlength="400" name = "text" id="text"></textarea>
							<div class="clear">&nbsp;</div>
						</div>
						<div class="form-group">
							<button type="submit" class="send-button">Создать</button>
						</div>
					</form>
				</c:otherwise>
			</c:choose>		
		</div>
	</div>
</body>
</html>