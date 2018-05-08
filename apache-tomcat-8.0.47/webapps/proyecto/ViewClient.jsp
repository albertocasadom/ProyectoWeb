<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='Error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<title>Inicio</title>
	</head>
	<body>
		<% ArrayList<Order> orderlist = (ArrayList<Order>) session.getAttribute("orders"); %>


		<form action = "search" method = "post" class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type = "search"  class="form-control" name = "rest" placeholder = "Ciudad" required> 
				<input type = "submit"    class="btn btn-default"  name = "search" value = "Buscar">
			</div>	
		</form>
			<table class="table table-hover">
				<thead class="thead-dark">
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
							<td> <a href ="seeorder?id=<%=orderlist.get(i).getIdOrder()%>"><%= orderlist.get(i).getIdOrder() %> </a></td>
							<td> <%= orderlist.get(i).getFechaHora() %> </td>
							<td> <%= orderlist.get(i).getState() %> </td>
							<td> <%= orderlist.get(i).getPrecioTotal() %> € </td>
						</tr>
						<% } %>
					<% }else{ %>
						<% for(int i = orderlist.size() - 10; i < orderlist.size(); i++){ %>
							<tr>
								<td> <a href ="seeorder?id=<%=orderlist.get(i).getIdOrder()%>"> <%= orderlist.get(i).getIdOrder() %> </a></td>
								<td> <%= orderlist.get(i).getFechaHora() %> </td>
								<td> <%= orderlist.get(i).getState() %> </td>
								<td> <%= orderlist.get(i).getPrecioTotal() %> € </td>
							</tr>
						<% } %>
					<% } %>
				</tbody>
			</table>
			<form action = "close">
				<input type = "submit" class="btn btn-danger"  name = "close" value = "Cerrar sesión"> 
			</form>
	</body>
</html>
