<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='Error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Resultados de la búsqueda</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<style type="text/css"> 
			.bckgrnd{
				background: white;
			}
			a:hover{
				color: white;
			}
			.ctr{
				text-align: center;
			}
			.msg{
				margin-top: 25%;
				text-align: center;
				background: rgba(255,255,255,0.5)
			}
			.bt{
				margin-left: 48.5%;
				margin-top: 5%;
				padding: 10px 20px;
			}
		</style>
	</head>
	<body background = "prueba.jpg" width = "100%" heigth = "100%">
		<% ArrayList<Restaurant> foundrestaurants = (ArrayList<Restaurant>) request.getAttribute("foundrestaurants"); %>
		<% String ciudad = (String) request.getAttribute("ciudad"); %>
		<%if(foundrestaurants.size() == 0){%>
			<div class = "msg">
				<h2 > No hay ningun restaurante del tipo seleccionado </h2>
			</div>
			<form action = "init">
				<input type  ="submit" class = "bt btn btn-success" value = "Inicio">
			</form>
		<%} else{%>	
			<table class="table table-hover ctr">
				<thead class="thead-dark">
					<tr>
						<th> Nombre </th>
						<th> Direccion</th>
					</tr>
				</thead>
				<tbody>
					<% for(int i = 0; i< foundrestaurants.size(); i++){ %>
					<tr class = "bckgrnd">
						<td><a href = "rest?id=<%=foundrestaurants.get(i).getIdRest()%>&cit=<%=ciudad%>"> <%= foundrestaurants.get(i).getNameRest() %></td>
						<td> <strong><%= foundrestaurants.get(i).getAddressRest() %> </strong></td>
					</tr>
					<% } %>
				</tbody>
			</table>
		<%}%>	
	</body>
</html>