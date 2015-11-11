<%@ page language="java" import="com.board.AdDao" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> | Board</title>
	<link href="board.css" rel="stylesheet">
</head>
<body>
	<%@ include file="headermenu.jsp" %>
	<div class="create_wrap">
		<div class="create_ad_container">
			<div align="center">
				<c:choose>
					<c:when test="${requestScope.error == 'true'}">
						Ошибка.
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${requestScope.published == 'true'}">
								<H2>Спасибо, ${param.name}! Ваше объявление (№${requestScope.genId}) успешно опубликовано! </H2>
							</c:when>
							<c:otherwise>
								<H2>Попробуйте еще раз, ${param.name}! К сожалению, Ваше объявление не было опубликовано. <H2>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>