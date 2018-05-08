<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Tu pedido</title>
	</head>
	<body>
		<% User user = (User) session.getAttribute("user"); %>
		<% Order order = (Order) request.getAttribute("order"); %>
		<% Restaurant restaurant = (Restaurant) request.getAttribute("restaurant"); %>
		<% ArrayList<Plato> listaplatos = (ArrayList<Plato>) request.getAttribute("listaplatos"); %>
		<% int [] cantidad = (int []) request.getAttribute("cantidad"); %>
		<% if(user.getIdType() == User.TYPE_CUSTOMER){ %>
			<h2> <%=user.getName()%> tu pedido está <i><%= order.getState() %> </i></h2>
		<%}%>
			<table>
				<thead>
					<tr>
						<th> Identificador </th>
						<th> Fecha y hora </th>
						<th> Restaurante </th>
						<th> Dirección </th>
						<th> Precio Total </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td> <%= order.getIdOrder()%> </td>
						<td> <%= order.getFechaHora()%> </td>
						<td> <%= restaurant.getNameRest()%> </td>
						<td> <%= order.getAddressOrder()%> </td>
						<td> <%= order.getPrecioTotal()%> € </td>
					</tr>
				</tbody>
			</table>

			<ol>
				<% for(int i=0; i< listaplatos.size(); i++){ %>
					<li> <%= listaplatos.get(i).getNamePlate() %> x <%=cantidad[i]%> >> <%=listaplatos.get(i).getPrecio()%> € </li>
				<% } %>
			</ol>
	</body>
</html>