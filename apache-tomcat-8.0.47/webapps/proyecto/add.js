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

		order = {dir, city, tel, productos};
		console.log(order);

		$.post("newOrder",JSON.stringify(order));

	});

});