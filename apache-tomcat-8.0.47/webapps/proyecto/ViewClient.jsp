<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='Error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<title>Inicio</title>
		<style type="text/css">
			.ctr{
				text-align: center;
			}
			.colr{
				background: Khaki;
			}
			.colr:hover{
				background:white;
				transition: all 0.5s ease;
				padding: 15px 15px;
			}
			.trwhite{
				background: white;
			}
		</style>
	</head>
	<body background = "prueba.jpg" width = "100%" heigth = "100%">
		<% ArrayList<Order> orderlist = (ArrayList<Order>) session.getAttribute("orders"); %>
		<form action = "search" method = "post" class="navbar-form navbar-left" >
			<div class="form-group">
				<input type = "search"  class="form-control" name = "rest" placeholder = "Ciudad" required> 
				<input type = "submit"    class="btn btn-default"  name = "search" value = "Buscar">
			</div>	
		</form class="form-inline">
			<div class="form-group">
				<form action = "resttype?id=ff" method = "post">
					<input type = "submit" class="colr form-control" class="btn btn-default" value = "Fast Food">
				</form>
				<form action  ="resttype?id=eu"  method = "post">
					<input type = "submit" class="colr form-control" class="btn btn-default" value = "Comida Europea">
				</form>
				<form action = "resttype?id=asa" method = "post"> 
					<input type = "submit" class="colr form-control" class="btn btn-default" value = "Asador">
				</form>
				<form action= "resttype?id=cafe"  method = "post">
					<input type = "submit" class="colr form-control" class="btn btn-default" value = "Desayunos / Brunch">
				</form>
				<form action = "resttype?id=asia" method = "post">
					<input type = "submit" class="colr form-control" class="btn btn-default" value = "Asiático">  
				</form>
			</div>
			<table class="table ctr">
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
						<tr class  ="trwhite">
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
