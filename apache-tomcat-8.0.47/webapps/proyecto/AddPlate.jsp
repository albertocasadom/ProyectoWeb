<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Carta</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

	</head>
	<body>
		<% String [] id_rest_str = request.getParameterValues("idrest"); %>
		<form action="addplate" class="form-horizontal">
			<div "form-group">
				<label>Nombre:
					<input type= "text" class="form-control" name = "nameplate" required>
				</label>
			</div>
			<div "form-group">
				<label>Precio:
					<input type= "number" class="form-control" name = "precio" min = "0" max = "9999.99" step ="0.01" required>
				</label>
			</div>
			<div "form-group">
				<label>Descripcion:
					<input type= "text" class="form-control" name = "descripcion" required>
				</label>
			</div>
			<div "form-group">
					<input type= "submit" class="form-control" name = "send" value = "Enviar">
					<input type="hidden" name = "idrest" value = "<%=id_rest_str[0]%>">
			</div>
		</form	
	</body>
</html>