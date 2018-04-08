package restaurant;

public class Restaurant{
	private int id_rest;
	private String name;
	private String address_rest;
	private String ciudad;
	private String phone_rest;
	private String typ_rest;

	public String getIdRest(){
		return id_rest;
	}

	public void setIdRest(int id_rest){
		this.id_rest = id_rest;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getAddressRest(){
		return this.address_rest;
	}

	public void setAddressRest(String address_rest){
		this.address_rest = address_rest;
	}

	public String getCiudad(){
		return this.ciudad;
	}

	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}

	public String getPhoneRest(){
		return this.phone_rest;
	}

	public void setPhoneRest(String phone_rest){
		this.phone_rest = phone_rest;
	}

	public String getTypeRest(){
		return this.typ_rest;
	}

	public void setTypeRest(String typ_rest){
		this.typ_rest = typ_rest;
	}
}