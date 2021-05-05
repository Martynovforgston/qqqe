<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Админ-панель</title>

<style>
body {font-family: Arial, Helvetica, sans-serif;}

.btn {
	background-color: #3775dd;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}
.btn: hover {
	background-color: #002fed;
}

input[type="number"]{
	background-color: #eee;
	vertical-align: top;
	outline: none;
	padding: 0;
	height: 40px;
	line-height: 40px;
	text-indent: 10px;
	display: inline-block;
	width: 100%;
	box-sizing: border-box;
	border: 1px solid #ddd;
	font-size: 14px;
	border-radius: 3px;
}

input[type="number"]:focus {
	outline: 2px solid blue;
}
</style>
</head>
<body>
<form action = "${pageContext.request.contextPath}/admin" method="post" id="Admin">
<div class="container">
	<table border=0 cellpadding=5% >
		<tr>
			<td>Район города</td>
			<td>Коэффициент</td>
		</tr>
		<tr>
			<td><label for="Kalininsky">Калининский</label></td>
			<td><input type="number" id="Kalininsky" name="Kalininsky" min="1" max="3" step="0.1" value=${Kalininsky}> </td>
		</tr>
		<tr>
			<td><label for="Kirovsky">Кировский</label></td>
			<td><input type="number" id="Kirovsky" name="Kirovsky" min="1" max="3" step="0.1" value=${Kirovsky}> </td>
		</tr>
		<tr>
			<td><label for="Octobersky">Октябрьский</label></td>
			<td><input type="number" id="Octobersky" name="Octobersky" min="1" max="3" step="0.1" value=${Octobersky}> </td>
		</tr>

		<tr>
			<td><label for="Leninsky">Ленинский</label></td>
			<td><input type="number" id="Leninsky" name="Leninsky" min="1" max="3" step="0.1" value=${Leninsky}> </td>
		</tr>
		<tr>
			<td><label for="Ordzhonikidzevskiy">Орджоникидзевский</label></td>
			<td><input type="number" id="Ordzhonikidzevskiy" name="Ordzhonikidzevskiy" min="1" max="3" step="0.1" value=${Ordzhonikidzevskiy}> </td>
		</tr>

		<tr>
			<td><label for="Demsky">Демский</label></td>
			<td><input type="number" id="Demsky" name="Demsky" min="1" max="3" step="0.1" value=${Demsky}> </td>
		</tr>
		<tr>
			<td><label for="Sovetsky">Советский</label></td>
			<td><input type="number" id="Sovetsky" name="Sovetsky" min="1" max="3" step="0.1" value=${Sovetsky}> </td>
		</tr>

		<tr>
		<td colspan=2>
			<button type="submit" name="saveChange" class=btn> Сохранить изменения</button>
		</td>
		</tr>

	</table>
</div>
</form>
</body>
</html>
