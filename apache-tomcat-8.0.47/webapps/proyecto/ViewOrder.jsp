<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' %>
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
			<h2> <%=user.getName()%> tu pedido está <i><%= order.getState() %> </i></h2>
			<table>
				<thead>
					<tr>
						<th> Identificador </th>
						<th> Fecha y hora </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td> <%= order.getIdOrder()%> </td>
						<td> <%= order.getFechaHora() %> </td>
					</tr>
				</tbody>
			</table>

			<ol>
				<% for(int i=0; i< listaplatos.size(); i++){ %>
					<li> <%= listaplatos.get(i).getNamePlate() %> x <%=cantidad[i]%></li>
				<% } %>
			</ol>
	</body>
</html>