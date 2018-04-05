<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.List' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Inicio</title>
	</head>
	<body>

		<form action = "search">
			<div>
				<input type = "search" name = "rest" placeholder = "Introduzca restaurante"> 
				<input type = "submit" name = "search" value = "Buscar"> 
			</div>	
		</form>

	</body>
</html>
