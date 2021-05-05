<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Калькулятор клининговых услуг для квартир</title>

<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

input[type=text] {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

.btn {
	background-color: #3775dd;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 20%;
}

.btn: hover {
	background-color: #002fed;
}

input[type="number"] {
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

.selDis {
	background-color: #eee;
	vertical-align: top;
	outline: none;
	padding: 0;
	height: 40px;
	line-height: 40px;
	text-indent: 10px;
	display: inline-block;
	width: 20%;
	box-sizing: border-box;
	border: 1px solid #ddd;
	font-size: 14px;
	border-radius: 3px;
}

.navbar a {
	float: left;
	font-size: 16px;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.dropdown {
	float: left;
	overflow: hidden;
}

.dropdown .dropbtn {
	font-size: 16px;
	border: none;
	outline: none;
	color: white;
	padding: 14px 16px;
	background-color: inherit;
	font-family: inherit;
	margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
	background-color: 002fed;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	z-index: 1;
}

	.dropdown-content a {
	float: none;
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}

	.dropdown-content a:hover {
	background-color: #ddd;
}

	.dropdown:hover .dropdown-content {
	display: block;
} 

@media print {
body * {
	visibility: hidden;
}
#section-to-print, #section-to-print * {
    visibility: visible;
}
  #section-to-print {
    position: absolute;
    left: 0;
    top: 0;
  }
}
</style>
</head>
<body>
<div class="navbar">
	<div class="dropdown">
		<button class="dropbtn">Файл</button>
			<div class="dropdown-content">
				<a href="calculator?actionToDo=saveToFile&result=${result}">Сохранить в файл</a>
				<a href="javascript:print()">Печать файла</a>
			</div>
	</div>
	<div class="dropdown">
		<button class="dropbtn">Справка</button>
			<div class="dropdown-content">
				<a href="#">О программе</a>
			</div>
	</div>
</div> 
	
	<p style='color: red;'>${message}</p>
	
	<form action="${pageContext.request.contextPath}/calculator"
		method="post" id="Calculator">
		<div class="container">
			<label for="district"> Район города </label> 
			<select name="district" id="district" form="Calculator" class="selDis" required>
				<option value="Kalininsky">Калининский</option>
				<option value="Kirovsky">Кировский</option>
				<option value="Octobersky">Октябрьский</option>
				<option value="Leninsky">Ленинский</option>
				<option value="Ordzhonikidzevskiy">Орджоникидзевский</option>
				<option value="Demsky">Демский</option>
				<option value="Sovetsky">Советский</option>
			</select> <br>
			<table border="0" cellpadding="5%">
				<tr>
					<td><label for="1Leaf">Одностворчатых окон</label></td>
					<td><label for="2Leaf">Двустворчатых окон</label></td>
					<td><label for="3Leaf">Трехстворчатых окон</label></td>
				</tr>
				<tr>
					<td><input type="number" id="1Leaf" name="1Leaf" min="0" max="100" value="${count1leaf}"></td>
					<td><input type="number" id="2Leaf" name="2Leaf" min="0" max="100" value="${count2leaf}"></td>
					<td><input type="number" id="3Leaf" name="3Leaf" min="0" max="100" value="${count3leaf}"></td>
				</tr>
				<tr>
					<td colspan="3"><label for="Floor">Количество кв м пола </label> 
					<input type="number" id="Floor" name="Floor" min="0" max="200" value="${countM2}"></td>
				</tr>
			</table>

			<input type="checkbox" name="on" value="${isOn}"> Включить услугу мытья санузла<br> 
			<label for="promo">Промокод</label> 
			<input type="text" name="promo" id="promo" value="${promo}" placeholder="Enter promo"> <br> 
			<label for="promo">Стоимость</label>

			<input type="text" name="result" id="section-to-print" value="${result}" disabled> <br>

			<button type="submit" name="calculate" value="Calc" class="btn">Рассчитать</button>
		</div>
	</form>
</div>
</body>
</html>
