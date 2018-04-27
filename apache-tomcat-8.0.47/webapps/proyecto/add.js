$(document).ready(function(){
	 order = {};
	 productos = {};
	$(".boton").click(function(event){
		 var id_but = document.getElementsByClassName('boton');
		 var id_clicked = $(event.target).attr('id');
		 console.log(id_clicked);

		 if (productos[id_clicked] === undefined) {productos[id_clicked] = 1;} else {productos[id_clicked] += 1;}
		 	console.log(productos);
		 
	});

	$(".botonminus").click(function(event){
		var id_but = document.getElementsByClassName('botonminus');
		var id_clickedminus = $(event.target).attr('id');
		console.log(id_clickedminus);

		if ((productos[id_clickedminus] === undefined) || (productos[id_clickedminus] === 0)) {productos[id_clickedminus] = 0;} else {productos[id_clickedminus] -= 1;}
		 	console.log(productos);
		 
	});

	$(".butform").click(function(event){
		var dir = $("#str").val();
		var city = $("#cit").val();
		var tel = $("#tlf").val();
		var ciudad = $('.ciudadpedido').attr('id');
		var id_rest = $('.idrest').attr('id');
		console.log("id del restaurante  = " + id_rest);

		order = {dir, city, tel, id_rest, productos};
		console.log(order);

		if((city.localeCompare(ciudad)) === 0){
			$.post("newOrder",JSON.stringify(order));
		}else{
			window.alert("Por favor, introduza una dirección dentro de la ciudad en la que ha realizado la búsqueda de restaurantes: " + ciudad);
		
		}

	});

});