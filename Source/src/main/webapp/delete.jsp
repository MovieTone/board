<%@ page language="java" import="com.board.AdDao, com.board.Ad" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Удаление объявления | Board</title>
	<link href="board.css" rel="stylesheet">
</head>
<body>

	<%@ include file="headermenu.jsp" %>
	
	<div class="create_wrap">
		<div class="create_ad_container" align="center">
			<h2>Удалить объявление</h2>
			<c:choose>
				<c:when test="${requestScope.loggedIn == 'false'}">
					<div align="center">
						<a href="login">Войдите</a>, чтобы иметь возможность управлять своими объявлениями
					</div>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${requestScope.havingAccess == 'false'}">
							Вы не можете удалить это объявление.
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${requestScope.error == 'true'}">
									<H2>Ошибка.</H2>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${requestScope.isEdited == 'true'}">
											<H2>Спасибо, ${requestScope.adName}! Ваше объявление (№${param.id}) успешно удалено! </H2>
										</c:when>
										<c:otherwise>
											<H2>Попробуйте еще раз, ${requestScope.adName}! К сожалению, Ваше объявление (№${param.id}) не было удалено. <H2>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>