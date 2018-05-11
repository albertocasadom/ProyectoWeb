<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Añadir plato</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<style type = "text/css">
			.centralbox{
				background-color: rgba(228, 169, 46, 0.72);
				border-radius: 20%;
				width: 400px;
				height: 400px;
				position: absolute;
				top: 40%;
				left: 45%;
				margin-top: -100px;
				margin-left: -125px;
			}
			.in{
				margin: .3em 40%;
				text-align: center;
			}
			.bt{
				margin-top: 20px;
				margin-left: 37.5%;
			    padding:10px 25px;
			    background:#7eb9dc;
			    border:0 none;
			    border-radius: 7px;
			}
			label{
				margin-top: 20px;
			}
			.inp{
    			padding:5px;
    			border:2px solid #7eb9dc;
   				border-radius: 7px;	
			}
			.inp1{ margin-left: 26%;}
			.inp2{ margin-left: 35%;}
		</style>
	</head>
	<body background = "prueba.jpg" width = "100%" heigth = "100%">
		<% String [] id_rest_str = request.getParameterValues("idrest"); %>

		<div class  ="centralbox">
			<form action="addplate" class="form-horizontal">
				<div class = "in">
					<label><strong>Nombre:</strong></label>
				</div>
				<div "form-group">
						<input type= "text" class="inp1 inp" name = "nameplate" required>
				</div>
				<div class = "in">
					<label><strong>Precio:</strong></label>
				</div>
				<div "form-group">
						<input type= "number" class="inp2 inp" name = "precio" min = "0" max = "9999.99" step ="0.01" required>	
				</div>
				<div class = "in">
					<label><strong>Descripcion:</strong></label>
				</div>
				<div "form-group">
						<input type= "text" class="inp1 inp" name = "descripcion" required>			
				</div>
				<div "form-group">
						<input type= "submit" class="bt" name = "send" value = "Enviar">
						<input type="hidden" name = "idrest" value = "<%=id_rest_str[0]%>">
				</div>
			</form>
		</div>	
	</body>
</html>