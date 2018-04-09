<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>



<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Inicio</title>
	</head>
	<body>

		<% ArrayList<Restaurant> restaurantslist = (ArrayList<Restaurant>) session.getAttribute("restaurants"); %>
		<% ArrayList<ArrayList<Order>> ordersallrest = (ArrayList<ArrayList<Order>>) session.getAttribute("ordersallrest"); %>

		<% for(int i = 0; i<restaurantslist.size();i++) { %>
			<div>
				<h2> <a href = "/proyecto/rest?id=<%=restaurantslist.get(i).getIdRest()%>"><%= restaurantslist.get(i).getNameRest() %>, <%=restaurantslist.get(i).getAddressRest() %></a></h2>
			</div>
				<table>
					<thead>
						<tr>
							<th> Pedido </th>
							<th> Fecha y Hora </th>
							<th> Estado </th>
							<th> Total </th>
							<th>	</th>
						</tr>
					</thead>
					<tbody>
						<% for(int j = 0; j< ordersallrest.get(i).size(); j++){ %>
							<tr>
								<td> <%= ordersallrest.get(i).get(j).getIdOrder() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getFechaHora() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getState() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getPrecioTotal() %> € </td>		
								<td> <input type = "submit" name = "edit" value = "Editar Pedido" id = "<%=ordersallrest.get(i).get(j).getIdOrder()%>"> </td>
							</tr>
						<% } %>
					</tbody>
				</table>
				<input type = "submit" name = "add" value = "Añadir plato">
				<input type = "submit" name = "delete" value = "Eliminar plato">
				<input type = "submit" name = "change" value = "Modificar precio">
		<% } %>

		<ul> Lista de Restaurantes
			<% for(int x = 0; x<restaurantslist.size();x++) { %>
				<li> <a href = "/proyecto/rest?id=<%=restaurantslist.get(x).getIdRest()%>"> <%= restaurantslist.get(x).getNameRest() %> , <%= restaurantslist.get(x).getAddressRest() %> </a></li>
			<% } %>
		</ul>
	</body>
</html>