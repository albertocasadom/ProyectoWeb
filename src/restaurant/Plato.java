package restaurant;

import java.io.*;

public class Plato{
	private int id_plato;
	private float precio;
	private int id_rest;
	private File img;
	private String descripcion;

	public int getIdPlato(){
		return this.id_plato;
	}

	public void setIdPlato(int id_plato){
		this.id_plato = id_plato;
	}

	public float getPrecio(){
		return this.precio;
	}

	public void setPrecio(float precio){
		this.precio = precio;
	}

	public int getIdRest(){
		return this.id_rest;
	}

	public void setIdRest(int id_rest){
		this.id_rest = id_rest;
	}

	public File getImg(){
		return this.img;
	}

	public void setImg(File img){
		this.img = img;
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
}