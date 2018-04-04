package restaurant;

public class User{
	private String name;
	private String surname;
	private String mail;
	private String pass;
	private String address;
	private int id;
	private int idOrder;
	private int idType;

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSurname(){
		return this.surname;
	}

	public void setSurname(String surname){
		this.surname = surname;
	}


	public String getMail(){
		return this.mail;
	}

	public void setMail(String mail){
		this.mail = mail;
	}

	public String getPass(){
		return this.pass;
	}

	public void setPass(String pass){
		this.pass = pass;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getIdOrder(){
		return this.idOrder;
	}

	public void setIdOrder(int idOrder){
		this.idOrder = idOrder;
	}

	public int getIdType(){
		return this.idType;
	}

	public void setIdType(int idType){

		this.idType = idType;
	}

}