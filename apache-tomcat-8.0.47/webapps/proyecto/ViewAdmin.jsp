<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='Error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>



<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Inicio</title>
				<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

		<style type="text/css">
			
			.trwhite{
				background: white;
			}
			.bt{
				
				margin-left:8%;
			    padding:5px 15px;
			    background:#7eb9dc;
			    border:0 none;
			    border-radius: 7px;
			}
			.tlt{
				padding: 1%;
				text-align: center;
				background: rgba(255,255,255,0.7);
			}

			.ctr{
				text-align: center;
			}
		</style>
	</head>
	<body background = "prueba.jpg" width = "100%" heigth = "100%">

		<% ArrayList<Restaurant> restaurantslist = (ArrayList<Restaurant>) session.getAttribute("restaurants"); %>
		<% ArrayList<ArrayList<Order>> ordersallrest = (ArrayList<ArrayList<Order>>) session.getAttribute("ordersallrest"); %>

		<% for(int i = 0; i<restaurantslist.size();i++) { %>
			<div>
				<h2 class = "tlt"> <a href = "/proyecto/rest?id=<%=restaurantslist.get(i).getIdRest()%>"><%= restaurantslist.get(i).getNameRest() %>, <%=restaurantslist.get(i).getAddressRest() %></a></h2>
			</div>
				<table class="table ctr">
					<thead class="thead-dark">
						<tr class = "trwhite">
							<th> Pedido </th>
							<th> Fecha y Hora última actualización </th>
							<th> Estado </th>
							<th> Total </th>
							<th>	</th>
						</tr>
					</thead>
					<tbody>
						<% for(int j = 0; j< ordersallrest.get(i).size(); j++){ %>
							<tr class = "trwhite">
								<td> <a href = "seeorder?id=<%=ordersallrest.get(i).get(j).getIdOrder()%>"><%= ordersallrest.get(i).get(j).getIdOrder() %></a></td>
								<td> <%= ordersallrest.get(i).get(j).getFechaHora() %> </td>  
								<td> <%= ordersallrest.get(i).get(j).getState() %> </td>
								<td> <%= ordersallrest.get(i).get(j).getPrecioTotal() %> € </td>		
								<td> 
								<form action = "changestate">
								 	<label for = "unittype"> Cambiar estado a: <br> </label> 
									 <select id"idstate" data-toggle="dropdown" class="btn btn-secondary dropdown-toggle" name = "state" >
									 	<option value = "term"  name = "term">terminated </option>
									 	<option value = "ready"  name = "ready">ready</option> 
									 	<option value = "deliver"  name= "deliver">on deliver</option> 
									 	<option value = "inprogress"  name = "inprogress">in progress</option> 

									 <input type = "hidden" name = "idorder" value = "<%=ordersallrest.get(i).get(j).getIdOrder()%>">
									 <input type = "submit" class = "bt" name = "edit" value = "Editar Pedido"> </td>
								</form>	 	
									

							</tr>
						<% } %>
					</tbody>
				</table>
		<% } %>
			<form action = "close">
				<input type = "submit" class="btn btn-danger"  name = "close" value = "Cerrar sesión"> 
			</form>
	</body>
</html>