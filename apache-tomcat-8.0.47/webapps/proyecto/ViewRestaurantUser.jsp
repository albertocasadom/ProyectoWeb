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
				font-size: 50px;
			}
			.img-css{
				max-width: 50%;
				margin-left: 25%;
				border-radius: 30px;
			}
			.centre{	
				padding: -25%;
			}
			.boxctr{
				margin-left: 37%;
			}
			.in{
				margin: .3em 47px;
				border-radius: 7px;
			}
			input[type = "text"]{
				border:2px solid #7eb9dc;
				padding:3px;
			}
			input[type = "tel"]{
				border:2px solid #7eb9dc;
				padding:3px;
			}
			.inb{
				margin: .3em 75px;
			}
			.fond{
				background: #c89048;
			}
		</style>
	</head>
	<body id = "result" >
		<% ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); %>
		<% User user = (User) session.getAttribute("user"); %>
		<% String ciudad = (String) request.getAttribute("cit"); %>
		<% int id_rest = (int) request.getAttribute("id_rest"); %>
		<form action = "init">
				<input type = "submit" class = "btn btn-danger" value = "Volver al inicio">
		</form>
		<img src="chameli_dallas_menu.png" class="img-rounded img-css" height = "300px" width ="70%">
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
		<h4> TOTAL DEL PEDIDO </h4>
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

		<div class = "fond">
			<h2 class = "ctr"> DIRECCIÓN DE ENTREGA </h2>
			<div class = "boxctr">
				<form>
					<div>
						<label class = "in"> Dirección:  </label>
					</div>
					<div>
						<input type = "text" name = "street" class = "in" id = "str" value = "<%=user.getAddress()%>" required> 
					</div>
					<div>
						<label class = "in"> Ciudad: </label>
					</div>
					<div>
						<input type = "text" name = "city" class = "in" id = "cit" value = "<%=user.getCiudad()%>" required>
					</div>
					<div>
						<label class = "in"> Número de telefono: </label>
					</div>
					<div>
						<input type = "tel" name = "tele" class = "in" id = "tlf" value = "<%=user.getPhone()%>"required>
					</div>
					<div >
						<input type  ="button" class = "inb butform btn btn-success" value = "Hacer pedido">
					</div>
				</form>
			</div>
		</div>	
	</body>
</html>