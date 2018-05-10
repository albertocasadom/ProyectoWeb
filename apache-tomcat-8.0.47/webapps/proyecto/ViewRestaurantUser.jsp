<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage = "Error.jsp"%>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="add.js" type="text/javascript"></script>
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
	<body id = "result">
		<% ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); %>
		<% User user = (User) session.getAttribute("user"); %>
		<% String ciudad = (String) request.getAttribute("cit"); %>
		<% int id_rest = (int) request.getAttribute("id_rest"); %>
		<img src="carta.jpg" class="img-rounded img-css" height = "400px" width ="800px">
		<form>
			<input type = "hidden" class = "idrest" id = "<%=id_rest%>">
			<input type = "hidden" class = "ciudadpedido" id = "<%= ciudad %>">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th> Plato </th>
						<th> Descripción</th>
						<th> Precio </th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<% for(int i = 0; i< cart.size(); i++){ %>
					<tr>
						<td> <%= cart.get(i).getNamePlate() %> </td>
						<td> <%= cart.get(i).getDescripcion() %> </td>
						<td> <%= cart.get(i).getPrecio() %> € </td>
						<td><input type = "button" class = "boton btn btn-success" name = "<%=cart.get(i).getNamePlate()%>" id = "<%=cart.get(i).getIdPlato()%>" value = "+" > </td>
						<td><input type = "button" class = "botonminus btn btn-danger" name = "<%=cart.get(i).getNamePlate()%>" id = "<%=cart.get(i).getIdPlato()%>" value = "-" > </td>
						<input type = "hidden" class = "<%=cart.get(i).getNamePlate()%>" value = "<%= cart.get(i).getPrecio() %>">
					</tr>
					<% } %>
				</tbody>
			</table>
		</form>

		<h2> TOTAL DEL PEDIDO </h2>
		<table id ="totalpedido" class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th> Producto </th>
					<th> Unidades </th>
					<th> Precio </th>
				</tr>
				<tbody>
					<tr id="total">
          				<td>Total:</td>
          					<td></td>
          				<td id="totalval">0.00 €</td>
        			</tr>
				</tbody>
		</table>	


		<h2> DIRECCIÓN DE ENTREGA </h2>
		<form>
			<div>
				<label> Dirección:  </label>
					<input type = "text" name = "street" id = "str" value = "<%=user.getAddress()%>" required> 
			</div>
			<div>
				<label> Ciudad: </label>
					<input type = "text" name = "city" id = "cit" value = "<%=user.getCiudad()%>" required>
			</div>
			<div>
				<label> Número de telefono: </label>
					<input type = "tel" name = "tele" id = "tlf" value = "<%=user.getPhone()%>"required>
			</div>
			<div>
				<input type  ="button" class = "butform" value = "Hacer pedido">
			</div>
		</form>

		<form action = "init">
			<input type = submit value = "Volver al inicio">
		</form>
	</body>
</html>