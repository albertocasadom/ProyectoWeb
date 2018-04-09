<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Carta</title>
	</head>
	<body>
		<% ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); %>
			<h2> CARTA </h2>
			<table>
				<thead>
					<tr>
						<th> Plato </th>
						<th> Descripción</th>
						<th> Precio </th>
					</tr>
				</thead>
				<tbody>
					<% for(int i = 0; i< cart.size(); i++){ %>
					<tr>
						<td> <%= cart.get(i).getNamePlate() %> </td>
						<td> <%= cart.get(i).getDescripcion() %> </td>
						<td> <%= cart.get(i).getPrecio() %> € </td>
					</tr>
					<% } %>
				</tbody>
			</table>
	</body>
</html>