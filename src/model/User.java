package model;

import java.io.Serializable;

/**
 * @author Benjamin Silva S
 *
 */

@SuppressWarnings("serial")
public class User implements Comparable<User>, Serializable {
	
	//---------------------------------------------------------------------
	//Constantes de dominio para el atributo 'idType'
	//---------------------------------------------------------------------
	
	public static final String[] ID = {"Cédula de Ciudadanía","Tarjeta de Identidad","Registro Civil","Pasaporte","Cédula de Extranjería"};
	
	public static final String CC = "Cédula de Ciudadanía";
	public static final String TI = "Tarjeta de Identidad";
	public static final String REGISTRO_CIVIL = "Registro Civil";
	public static final String PASAPORTE = "Pasaporte";
	public static final String CE = "Cédula de Extranjería";
	
	//---------------------------------------------------------------------
	//Atributos
	//---------------------------------------------------------------------
	
	/**
	 * Code of identification of the user.
	 */
	private String id;
	
	/**
	 * Type of user identification document.
	 */
	private String idType;
	
	/**
	 * User name.
	 */
	private String name;
	
	/**
	 * User last names.
	 */
	private String lastName;
	
	/**
	 * User address.
	 */
	private String address;
	
	/**
	 * User phone number.
	 */
	private String phoneNumber;
	
	//---------------------------------------------------------------------
	//Constructores
	//---------------------------------------------------------------------
	
	/**
	 * <b>Des:</b> Default Constructor of an user object.<br>
	 * @param id Code of identification of the user. id!=null id!=""
	 * @param idType Type of user identification document. id!=null id!=""
	 * @param name User name. id!=null id!=""
	 * @param lastNames User last names. id!=null id!=""
	 * @param address User Address.
	 * @param phoneNumber User phone number.
	 */
	public User(String id, String idType, String name, String lastName, String address, String phoneNumber) {
		this.id = id;
		this.idType = idType;
		this.name = name;
		this.lastName = lastName;
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
	
	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	/**
	 * String representation of an user object.
	 */
	public String toString() {
		String user;
		user =idType+" "+id+".  "+name+" "+lastName+".   Address: "+address+".   Phone Number: "+phoneNumber;
		return user;
	}

	@Override
	public int compareTo(User u2) {
		return id.compareTo(u2.id);
	}
}
