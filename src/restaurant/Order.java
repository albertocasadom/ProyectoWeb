package restaurant;

import java.sql.Timestamp;

public class Order{	
	private int id_order;
	private String state;
	private int id_user;
	private String ciudad;
	private String address_order;
	private float precio_total;
	private Timestamp fecha_hora;
	private int id_rest;

	public int getIdOrder(){
		return this.id_order;
	}

	public void setIdOrder(int id_order){
		this.id_order = id_order;
	}

	public String getState(){
		if(this.state.equals("term")){
			return "terminado";
		}else if(this.state.equals("ready")){
			return "listo";
		}else if(this.state.equals("deliver")){
			return "en reparto";
		}else{
			return "en proceso";
		}
	}

	public void setState(String state){
		this.state = state;
	}

	public int getIdUser(){
		return this.id_user;
	}

	public void setIdUser(int id_user){
		this.id_user = id_user;
	}

	public String getCiudad(){
		return this.ciudad;
	}

	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}

	public String getAddressOrder(){
		return this.address_order;
	}

	public void setAddressOrder(String address_order){
		this.address_order = address_order;
	}

	public float getPrecioTotal(){
		return this.precio_total;
	}

	public void setPrecioTotal(float precio_total){
		this.precio_total= precio_total;
	}

	public Timestamp getFechaHora(){
		return this.fecha_hora;
	}

	public void setFechaHora(Timestamp fecha_hora){
		this.fecha_hora = fecha_hora;
	}

	public int getIdRest(){
		return this.id_rest;
	}

	public void setIdRest(int id_rest){
		this.id_rest = id_rest;
	}

}