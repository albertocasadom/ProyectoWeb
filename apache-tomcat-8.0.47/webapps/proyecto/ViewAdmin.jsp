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
				<h2> <%= restaurantslist.get(i).getNameRest() %>, <%=restaurantslist.get(i).getAddressRest()  %></h2>
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
								<td> <input type = "submit" name = "search" value = "Editar Pedido" id = "<%=ordersallrest.get(i).get(j).getIdOrder()%>"> </td>
							</tr>
						<% } %>
					</tbody>
				</table>
		
		<% } %>



	</body>
</html>