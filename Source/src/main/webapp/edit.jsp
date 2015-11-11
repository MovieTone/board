<%@ page language="java" import="com.board.AdDao, com.board.Ad" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Редактировать объявление | Board</title>
	
	<link href="board.css" rel="stylesheet">
	<script type="text/javascript" src="confirm_editing.js"></script>
</head>
<body>
	
	<%@ include file="headermenu.jsp" %>
	
	<div class="create_wrap">
		<div class="create_ad_container" align="center">
			<h2>Редактировать объявление</h2>
			
			<c:choose>
				<c:when test="${requestScope.loggedIn == 'false'}">
					<div align="center">
						<a href="login">Войдите</a>, чтобы иметь возможность управлять своими объявлениями
					</div>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${requestScope.havingAccess == 'false'}">
							Вы не можете изменить это объявление.
						</c:when>
						<c:otherwise>
							<form method="post" action="edit_checkout" id="create-form" role="form">
								<input type="hidden" name="id" value="${param.id}"/>
								<div class="form-group">
									<label for="user">Имя:</label>
									<input required readonly type="text" maxlength="40" name="name" value="${requestScope.currentUser}" id="user"/>
									<div class="clear">&nbsp;</div>
								</div>
								<div class="form-group">
									<label for="headline">Заголовок:</label>
									<input required type = "text" minlength="10" maxlength="30" name = "headline" id="headline" value="${requestScope.adHeadline}"></input>
									<div class="clear">&nbsp;</div>
								</div>
								<div class="form-group">
									<label for="rubric">Рубрика:</label>
									<select required size = "1" name = "rubric" id="rubric"> 
										<option selected disabled></option>
										${requestScope.selectedRubric0}
										${requestScope.selectedRubric1}
										${requestScope.selectedRubric2}
										${requestScope.selectedRubric3}
										${requestScope.selectedRubric4}
										${requestScope.selectedRubric5}
									</select>
									<div class="clear">&nbsp;</div>
								</div>
								<div class="form-group">
									<label for="text">Текст:</label>
									<textarea required rows = "5" cols = "30" minlength="20" maxlength="400" name = "text" id="text">${requestScope.adText}</textarea>
									<div class="clear">&nbsp;</div>
								</div>
								<div class="form-group">
									<button type="submit" class="send-button" onClick="return confirmEditing()">Редактировать</button>
								</div>
							</form>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>