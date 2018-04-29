$(document).ready(function(){
	 order = {};
	 productos = {};

	$(".boton").click(function(event){
		 var id_clicked = $(event.target).attr('id');
		 var nameplate_clicked = $(event.target).attr('name');
		 var t = $(document.getElementById(nameplate_clicked));
		 var prc = $(document.getElementsByClassName(nameplate_clicked)).attr('value');
		 prc = parseFloat(prc);
		 var price = prc;
		 console.log(prc);
		 if (productos[id_clicked] === undefined) {
		 	productos[id_clicked] = 1;
		 	 $("#total").before(
		 		$("<tr>").attr('id', nameplate_clicked).append(
		 			$("<td>").text(nameplate_clicked)).append(
		 			$("<td>").text(productos[id_clicked])).append(
		 			$("<td>").text(prc)));	 	
		 }else { 	
		 	productos[id_clicked] += 1;
		 	t.find("td:nth-child(2)").text(productos[id_clicked]);	
		 	prc = prc * productos[id_clicked];
		 	t.find("td:nth-child(3)").text(prc.toFixed(2));
		 }
		 	console.log(productos);

		 var totalprice = $("#totalval").text();
		 totalprice = parseFloat(totalprice);
		 totalprice = totalprice + price;
		 $("#totalval").text(totalprice.toFixed(2));		 
	});

	$(".botonminus").click(function(event){
		var id_but = document.getElementsByClassName('botonminus');
		var id_clickedminus = $(event.target).attr('id');
		var nameplate_clicked = $(event.target).attr('name');
		var prc = $(document.getElementsByClassName(nameplate_clicked)).attr('value');
		prc = parseFloat(prc);
		var pricem = prc;
		var t = $(document.getElementById(nameplate_clicked));

		if ((productos[id_clickedminus] === undefined) || (productos[id_clickedminus] === 0)) {
			productos[id_clickedminus] = 0;
			t.find("td:nth-child(3)").text("0.00");
		}else {
			productos[id_clickedminus] -= 1;
			t.find("td:nth-child(2)").text(productos[id_clickedminus]);
			t.find("td:nth-child(2)").text(productos[id_clickedminus]);
			var price = t.find("td:nth-child(3)").text();
			price = parseFloat(price);
			prc = price - prc;
			t.find("td:nth-child(3)").text(prc.toFixed(2));
		}

		var totalprice = $("#totalval").text();
		 totalprice = parseFloat(totalprice);
		 totalprice = totalprice - pricem;
		 $("#totalval").text(totalprice.toFixed(2));

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

		if((city.localeCompare(ciudad)) === 0){
			$.post("newOrder",JSON.stringify(order),function(data){
				var url = "/proyecto/seeorder?id=" + data;
				window.open(url,"Tu pedido");
			});
		}else{
			window.alert("Por favor, introduza una dirección dentro de la ciudad en la que ha realizado la búsqueda de restaurantes: " + ciudad);
		
		}

	});

});