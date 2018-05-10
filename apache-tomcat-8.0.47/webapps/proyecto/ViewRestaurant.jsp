<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Carta</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<style type="text/css"> 
			.ctr { 
				text-align: center;
				font-size: 20px;
			}
			.img-css{
				max-width: 50%;
				margin-left: 27%;
				border-radius: 30px;
			}
			.centre{	
				padding: -25%;
			}	
		</style>			 	
	</head>
	<body>
		<% ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); %>
		<% User user = (User) session.getAttribute("user"); %>
		<% String id_rest_str = (String) request.getAttribute("id"); %>
		<% String err = (String) request.getAttribute("err"); %>
			<% if("1".equals(err)){ %>
			<p> Error al añadir el plato, ya existe un plato con ese nombre </p>
			<%}%>
			<img src="carta.jpg" class="img-rounded img-css" height = "400px" width ="800px">
			<table class="table-hover ctr table">
				<thead class="thead-dark">
					<tr>
						<th> Plato </th>
						<th> Descripción</th>
						<th> Precio </th>
						<th></th>
						<th> <form action = "AddPlate.jsp">
								<input type = "hidden" name = "idrest" value = "<%=id_rest_str%>">
								<input type="submit" class="btn btn-success" name = "addplate" value = "Añadir plato" >
							</form>
						</th>
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
							<input type="submit" class="btn btn-warning" name = "changeprice" value = "Modificar Precio">
							<input type="hidden" name = "idplato" value = "<%= cart.get(i).getIdPlato() %>">
						</form>
						</td>
						<td>
						<form action = "deleteplate">
							<input type="submit" class="btn btn-danger" name = "deleteplate" value = "Eliminar plato">
							<input type="hidden" name = "idplatodelete" value = "<%= cart.get(i).getIdPlato() %>">
						</form>
						<%}%>
					</td>
					</tr>

					<% } %>
				</tbody>
			</table>
	</body>
</html>