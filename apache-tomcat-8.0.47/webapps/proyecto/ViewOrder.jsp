<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Tu pedido</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<style type="text/css">
			.ctr{
				text-align: center;
			}
			.trwhite{
				background: white;
			}
			.divclr{
				background: rgba(255,255,255,0.5);
			}
			.bt{
				margin-left: 48.5%;
				margin-top: 5%;
				padding: 10px 20px;
			}

		</style>
	</head>
	<body background = "prueba.jpg" width = "100%" heigth = "100%">
		<% User user = (User) session.getAttribute("user"); %>
		<% Order order = (Order) request.getAttribute("order"); %>
		<% Restaurant restaurant = (Restaurant) request.getAttribute("restaurant"); %>
		<% ArrayList<Plato> listaplatos = (ArrayList<Plato>) request.getAttribute("listaplatos"); %>
		<% int [] cantidad = (int []) request.getAttribute("cantidad"); %>
		<% if(user.getIdType() == User.TYPE_CUSTOMER){ %>
		<div class  ="divclr">
			<h2 class = "ctr"> <%=user.getName()%> tu pedido está <i><%= order.getState() %> </i></h2>
		</div>
		<%}%>
			<table class="table ctr">
				<thead class="thead-dark ctr">
					<tr>
						<th> Identificador </th>
						<th> Fecha y hora </th>
						<th> Restaurante </th>
						<th> Dirección </th>
						<th> Precio Total </th>
					</tr>
				</thead>
				<tbody>
					<tr class = "trwhite">
						<td> <%= order.getIdOrder()%> </td>
						<td> <%= order.getFechaHora()%> </td>
						<td> <%= restaurant.getNameRest()%> </td>
						<td> <%= order.getAddressOrder()%> </td>
						<td> <%= order.getPrecioTotal()%> € </td>
					</tr>
				</tbody>
			</table>

			<ol class="list-group ctr">
				<% for(int i=0; i< listaplatos.size(); i++){ %>
					<li class="list-group-item"> <%= listaplatos.get(i).getNamePlate() %> x <%=cantidad[i]%> >> <%=listaplatos.get(i).getPrecio()%> € </li>
				<% } %>
			</ol>

			<form action = "init">
				<input type  ="submit" class = "bt btn btn-success" value = "Inicio">
			</form>
	</body>
</html>