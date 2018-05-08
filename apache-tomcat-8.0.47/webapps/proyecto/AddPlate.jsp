<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Carta</title>
	</head>
	<body>
		<% String [] id_rest_str = request.getParameterValues("idrest"); %>
		<form action="addplate">
			<div>
				<label>Nombre:
					<input type= "text" name = "nameplate" required>
				</label>
			</div>
			<div>
				<label>Precio:
					<input type= "number" name = "precio" min = "0" max = "9999.99" step ="0.01" required>
				</label>
			</div>
			<div>
				<label>Descripcion:
					<input type= "text" name = "descripcion" required>
				</label>
			</div>
			<div>
					<input type= "submit" name = "send" value = "Enviar">
					<input type="hidden" name = "idrest" value = "<%=id_rest_str[0]%>">
			</div>
		</form	
	</body>
</html>