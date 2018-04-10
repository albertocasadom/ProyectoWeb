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

		<% ArrayList<Restaurant> restaurantslist = (ArrayList<Restaurant>) session.getAttribute("restaurants"); %>
		<% ArrayList<ArrayList<Order>> ordersallrest = (ArrayList<ArrayList<Order>>) session.getAttribute("ordersallrest"); %>

		<% for(int i = 0; i<restaurantslist.size();i++) { %>
			<div>
				<h2> <a href = "/proyecto/rest?id=<%=restaurantslist.get(i).getIdRest()%>"><%= restaurantslist.get(i).getNameRest() %>, <%=restaurantslist.get(i).getAddressRest() %></a></h2>
			</div>
				<table>
					<thead>
						<tr>
							<th> Pedido </th>
							<th> Fecha y Hora última actualización </th>
							<th> Estado </th>
							<th> Total </th>
							<th>	</th>
						</tr>
					</thead>
					<tbody>
						<% for(int j = 0; j< ordersallrest.get(i).size(); j++){ %>
							<tr>
								<td> <%= ordersallrest.get(i).get(j).getIdOrder() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getFechaHora() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getState() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getPrecioTotal() %> € </td>		
								<td> 
								<form action = "changestate">
								 	<label for = "unittype"> Cambiar estado a: </label> 
									 <select id"idstate" name = "state" >
									 	<option value = "term" name = "term">terminated </option>
									 	<option value = "ready" name = "ready">ready</option> 
									 	<option value = "deliver" name= "deliver">on deliver</option> 
									 	<option value = "inprogress" name = "inprogress">in progress</option> 

									 <input type = "hidden" name = "idorder" value = "<%=ordersallrest.get(i).get(j).getIdOrder()%>">
									 <input type = "submit" name = "edit" value = "Editar Pedido"> </td>
								</form>	 	
									

							</tr>
						<% } %>
					</tbody>
				</table>
		<% } %>

		<ul> Lista de Restaurantes
			<% for(int x = 0; x<restaurantslist.size();x++) { %>
				<li> <a href = "rest?id=<%=restaurantslist.get(x).getIdRest()%>"> <%= restaurantslist.get(x).getNameRest() %> , <%= restaurantslist.get(x).getAddressRest() %> </a></li>
			<% } %>
		</ul>
	</body>
</html>