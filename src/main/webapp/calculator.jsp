<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Калькулятор клининговых услуг для квартир</title>
<link rel="stylesheet" href="style.css">
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
	
	<div class="container"> 
		<form action="${pageContext.request.contextPath}/calculator" method="post" id="Calculator">
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
		</form>
	</div>
</div>
</body>
</html>
