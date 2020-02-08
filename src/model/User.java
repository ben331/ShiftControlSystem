package model;

/**
 * @author Benjamin Silva S
 *
 */

public class User {
	
	//---------------------------------------------------------------------
	//Constantes de dominio para el atributo 'idType'
	//---------------------------------------------------------------------
	
	public static final String CC = "Cédula de Ciudadania";
	public static final String TI = "Tarjeta de Identidad";
	public static final String REGISTRO_CIVIL = "Registro Civil";
	public static final String PASAPORTE = "Pasaporte";
	public static final String CE = "Cédula de Extranjería";
	
	//---------------------------------------------------------------------
	//Atributos
	//---------------------------------------------------------------------
	
	private String id;
	private String idType;
	private String name;
	private String lastNames;
	private String address;
	private String phoneNumber;
	
	//---------------------------------------------------------------------
	//Constructores
	//---------------------------------------------------------------------
	
	public User(String id, String idType, String name, String lastNames, String address, String phoneNumber) {
		this.id = id;
		this.idType = idType;
		this.name = name;
		this.lastNames = lastNames;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	//--------------------------------------------------
	//Getters and Setters
	//--------------------------------------------------
	
	public String getId() {
		return id;
	}

	public String getIdType() {
		return idType;
	}

	public String getName() {
		return name;
	}
	
	
	
	@Override
	public String toString() {
		String user;
		user =idType+" "+id+".  "+name+" "+lastNames+".   Address: "+address+".   Phone Number: "+phoneNumber;
		return user;
	}
}
