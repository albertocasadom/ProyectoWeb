<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="add.js" type="text/javascript"></script>
	<head>
		<meta charset = "UTF-8">
		<title>Carta</title>
	</head>
	<body>
		<% ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); %>
		<% User user = (User) session.getAttribute("user"); %>
		<% String ciudad = (String) request.getAttribute("cit"); %>
		<% int id_rest = (int) request.getAttribute("id_rest"); %>
		<% System.out.println("Id del restaurante:" + id_rest); %>
			<h2> CARTA </h2>
			<form>
				<input type = "hidden" class = "idrest" id = "<%=id_rest%>">
				<input type = "hidden" class = "ciudadpedido" id = "<%= ciudad %>">
				<table>
					<thead>
						<tr>
							<th> Plato </th>
							<th> Descripción</th>
							<th> Precio </th>
						</tr>
					</thead>
					<tbody>
						<% for(int i = 0; i< cart.size(); i++){ %>
						<tr>
							<td> <%= cart.get(i).getNamePlate() %> </td>
							<td> <%= cart.get(i).getDescripcion() %> </td>
							<td> <%= cart.get(i).getPrecio() %> € </td>
							<td><input type = "button" class = "boton" id = "<%=cart.get(i).getIdPlato()%>" value = "+" > </td>
							<td><input type = "button" class = "botonminus" id = "<%=cart.get(i).getIdPlato()%>" value = "-" > </td>

						</tr>
						<% } %>
					</tbody>
				</table>
			</form>


		<h2> DIRECCIÓN DE ENTREGA </h2>
		<form>
			<div>
				<label> Dirección:  </label>
					<input type = "text" name = "street" id = "str" required>
			</div>
			<div>
				<label> Ciudad: </label>
					<input type = "text" name = "city" id = "cit" required>
			</div>
			<div>
				<label> Número de telefono: </label>
					<input type = "tel" name = "tele" id = "tlf" required>
			</div>
			<div>
				<input type  ="button" class = "butform" value = "Hacer pedido">
			</div>
		</form>
	</body>
</html>