<%@ page language ='java' contentType ='text/html;charset=utf-8' isErrorPage='false' errorPage='Error.jsp' %>
<%@ page import ='restaurant.*' %>
<%@ page import ='java.util.*' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Resultados de la búsqueda</title>
	</head>
	<body>
		<% ArrayList<Restaurant> foundrestaurants = (ArrayList<Restaurant>) request.getAttribute("foundrestaurants"); %>
		<% String ciudad = (String) request.getAttribute("ciudad"); %>
			<table>
				<thead>
					<tr>
						<th> Nombre </th>
						<th> Direccion</th>
					</tr>
				</thead>
				<tbody>
					<% for(int i = 0; i< foundrestaurants.size(); i++){ %>
					<tr>
						<td><a href = "rest?id=<%=foundrestaurants.get(i).getIdRest()%>&cit=<%=ciudad%>"> <%= foundrestaurants.get(i).getNameRest() %></td>
						<td> <%= foundrestaurants.get(i).getAddressRest() %> </td>
					</tr>
					<% } %>
				</tbody>
			</table>
	</body>
</html>