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
	private String lastNames;
	
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
	/**
	 * String representation of an user object.
	 */
	public String toString() {
		String user;
		user =idType+" "+id+".  "+name+" "+lastNames+".   Address: "+address+".   Phone Number: "+phoneNumber;
		return user;
	}
}
