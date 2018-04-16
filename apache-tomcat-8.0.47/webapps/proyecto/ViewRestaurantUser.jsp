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
		<% ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); %>
		<% User user = (User) session.getAttribute("user"); %>
		<% String id_rest_str = (String) request.getAttribute("id"); %>
			<h2> CARTA </h2>
			<form action = "AddPlate.jsp">
				<input type = "hidden" name = "idrest" value = "<%=id_rest_str%>">
			
			</form>
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
						<td> </td>
				
					</tr>

					<% } %>
				</tbody>
			</table>
	</body>
</html>