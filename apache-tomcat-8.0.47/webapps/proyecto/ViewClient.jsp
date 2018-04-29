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
		<% ArrayList<Order> orderlist = (ArrayList<Order>) session.getAttribute("orders"); %>

		<form action = "search" method = "post">
			<div>
				<input type = "search" name = "rest" placeholder = "Ciudad" required> 
				<input type = "submit" name = "search" value = "Buscar"> 
			</div>	
		</form>
			<table>
				<thead>
					<tr>
						<th> Pedido </th>
						<th> Fecha y Hora </th>
						<th> Estado </th>
						<th> Total </th>
					</tr>
				</thead>
				<tbody>
					<% if(orderlist.size() < 10){ %>
						<% for(int i = 0; i< orderlist.size(); i++){ %>
						<tr>
							<td> <a href = "seeorder?id=<%=orderlist.get(i).getIdOrder()%>"><%= orderlist.get(i).getIdOrder() %> </a></td>
							<td> <%= orderlist.get(i).getFechaHora() %> </td>
							<td> <%= orderlist.get(i).getState() %> </td>
							<td> <%= orderlist.get(i).getPrecioTotal() %> € </td>
						</tr>
						<% } %>
					<% }else{ %>
						<% for(int i = orderlist.size() - 10; i < orderlist.size(); i++){ %>
							<tr>
								<td> <%= orderlist.get(i).getIdOrder() %> </td>
								<td> <%= orderlist.get(i).getFechaHora() %> </td>
								<td> <%= orderlist.get(i).getState() %> </td>
								<td> <%= orderlist.get(i).getPrecioTotal() %> € </td>
							</tr>
						<% } %>
					<% } %>
				</tbody>
			</table>
			<form action = "close">
				<input type = "submit" name = "close" value = "Cerrar sesión"> 
			</form>
	</body>
</html>
