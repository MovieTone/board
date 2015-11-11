<%@ page language="java" import="com.board.AdDao, com.board.Ad, java.util.*" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Фильтрация объявлений | Board</title>
	
	<link href="board.css" rel="stylesheet">
	<script type="text/javascript" src="author_filter_disable.js"></script>
	<script type="text/javascript" src="confirm_deleting.js"></script>
</head>
<body>
	<div class="wrap">
	
		<%@ include file="headermenu.jsp" %>
		
		<div class="messages_block">
			<div class="left_part">
				<h2 align="center">Объявления</h2>
				<c:forEach items="${requestScope.listAdsWithDates}" var="current">
					<div class="message_line_container">
						<div class="one_message_container">
							<div class="one_message">
								<div class="one_message_top">
									<div class="one_message_title">
										<h3>${current.headline}</h3>
									</div>
									<div class="one_message_author">
										${current.name}
									</div>
									<div class="clear"></div>
									<div class="one_message_title">${current.text}</div>
									<div class="clear"></div>
								</div>
								<div class="one_message_bottom">
									<div class="one_message_breadcrumbs">
										${current.rubric}
									</div>
									<div class="one_message_date">
										${current.date}
									</div> 
									<c:if test="${current.name == requestScope.currentUser}">
										<div class="clear"></div>
										<div class="one_message_date">
										<a href="edit?id=${current.id}">Редактировать</a> | 
										<a href="delete?id=${current.id}" onClick="return confirmDeleting()">Удалить</a> 
									</div> 
									</c:if>
								</div>
							</div>	
						</div>
					</div>
				</c:forEach>
				<div class="clear_bottom_message"></div>
				<c:if test="${empty requestScope.listAdsWithDates}">
					<div align="center">
						По данному запросу ничего не найдено
					</div>
				</c:if>
			</div>
			<div class="right_part">
				<div class="filter_container">
					<div class="filter_options">
						<h2 align="center">Фильтрация объявлений</h2>
						
						<form method="get" action="filter" id="filter-form" role="form">
							<div class="form-group">
								<label for="rubric_filter">Рубрика:</label>
								<select size = "1" name = "rubric" id="rubric_filter"> 
										<option selected></option>
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
								<label for="author_filter">Автор:</label>
								<input type="text" maxlength="40" name="author" id="author_filter"></input>
								<div class="clear">&nbsp;</div>
							</div>
							<div class="form-group">
								<input type="checkbox" name="onlyUser" value="true" id="checkbox_filter" onclick="disableAuthor(checkbox_filter, author_filter)">Только мои объявления</input>
								<div class="clear">&nbsp;</div>
							</div>
							<div class="form-group">
								<button type="submit" class="send-button">Найти</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>