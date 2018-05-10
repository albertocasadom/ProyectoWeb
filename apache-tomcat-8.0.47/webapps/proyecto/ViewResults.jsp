<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='Error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Resultados de la búsqueda</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	</head>
	<body>
		<% ArrayList<Restaurant> foundrestaurants = (ArrayList<Restaurant>) request.getAttribute("foundrestaurants"); %>
		<% String ciudad = (String) request.getAttribute("ciudad"); %>
		<%if(foundrestaurants.size() == 0){%>
			<h2> No hay ningun restaurante del tipo seleccionado </h2>
			<form action = "init">
				<input type  ="submit" value = "Inicio">
			</form>
		<%} else{%>	
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th> Nombre </th>
						<th> Direccion</th>
					</tr>
				</thead>
				<tbody>
					<% for(int i = 0; i< foundrestaurants.size(); i++){ %>
					<tr>
						<td><a href = "rest?id=<%=foundrestaurants.get(i).getIdRest()%>&cit=<%=ciudad%>"> <%= foundrestaurants.get(i).getNameRest() %></td>
						<td> <%= foundrestaurants.get(i).getAddressRest() %> </td>
					</tr>
					<% } %>
				</tbody>
			</table>
		<%}%>	
	</body>
</html>