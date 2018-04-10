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
		<% String id_rest_str = request.getAttribute("id"); %>
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
						<td>
						<% if(user.getIdType() == 2){ %>
						<form action = "changeprice">
							<input type = "number" name = "newprice" min = "0" max = "9999.99" step ="0.01" required>
							<input type="submit" name = "changeprice" value = "Modificar Precio">
							<input type="hidden" name = "idplato" value = "<%= cart.get(i).getIdPlato() %>">
						</form>
						</td>
						<td>
						<form action = "AddPlate.jsp">
							<input type = "hidden" name = "idrest" value = "<%=id_rest_str%>">
							<input type="submit" name = "addplate" value = "Añadir plato" >
						</form>
						</td>
						<td>
						<form action = "deleteplate">
							<input type="submit" name = "deleteplate" value = "Eliminar plato">
						</form>
						<%}%>
					</td>
					</tr>

					<% } %>
				</tbody>
			</table>
	</body>
</html>