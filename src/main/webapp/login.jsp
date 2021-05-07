<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Авторизация</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<form action="${pageContext.request.contextPath}/login" method="post" style="text-align=center;">
		<div class="header">
			<h2>Вход в калькулятор клининговых услуг для квартир</h2>
		</div>
		<div class="container">
			<label for="login">Логин</label><br> <input type="text"name="login" id="login" value="${login}" placeholder="Введите логин">
			<br> <label for="password">Пароль</label><br> <input type="password" name="password" id="password" value="${password}" placeholder="Введите пароль"> <br>
			<p style='color: red;'>${message}</p>
			<button type="submit" name="sign" value="Sign" class="btn">Войти</button>
		</div>
	</form>
</body>
</html>
