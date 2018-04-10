<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Carta</title>
	</head>
	<body>
		<% String id_rest_str = request.getParameterValues("id"); %>
		<form action="addplate?id=<%=id_rest_str%>">
			<div>
				<label>Nombre:
					<input type= "text" name = "nameplate">
				</label>
			</div>
			<div>
				<label>Precio:
					<input type= "number" name = "precio">
				</label>
			</div>
			<div>
				<label>Descripcion:
					<input type= "text" name = "descripcion">
				</label>
			</div>
			<div>
					<input type= "submit" name = "send" value = "Enviar">
			</div>
	</body>
</html>