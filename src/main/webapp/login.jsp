<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Авторизация</title>

<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

input[type=text], input[type=password] {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 20%;
}

button: hover {
	background-color:;
}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login" method="post"
		align="center">
		<div class="header">
			<h1 align="center">Авторизация</h1>
		</div>
		<div class="container">
			<label for="login">Login</label><br> <input type="text"
				name="login" id="login" value="${login}" placeholder="Enter login">
			<br> <label for="password">Password</label><br> <input
				type="password" name="password" id="password" value="${password}"
				placeholder="Enter password"> <br>
			<p style='color: red;'>${message}</p>
			<button type="submit" name="sign" value="Sign">Sign</button>
		</div>
	</form>
	<div></div>
</body>
</html>