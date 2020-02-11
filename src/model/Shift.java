package model;

/**
 * @author bengi
 *
 */
public class Shift {
	
	//-----------------------------------------
	//Atributos
	//-----------------------------------------
	/**
	 * Literal part of the code that identify the shift
	 */
	private char literal;
	
	/**
	 * Number part of the code that identify the shift
	 */
	private int number;
	
	/**
	 * User who have reserved this shift 
	 */
	private User assignedIdUser;
	
	/**
	 * A boolean value that indicate if the shift has been attended
	 */
	private boolean attended;
	
	/**
	 * A boolean value that indicate if the shift is in hold or not.
	 */
	private boolean active;
	
	
	//-----------------------------------------
	//Constructores
	//-----------------------------------------
	
	/**
	 * <b>Des:</b> Constructor for 'abstract shifts'. (Shifts that will never have assigned users).<br>
	 * @param literal Literal part of the code that identify the shift. Literal is between 'A' and 'Z'. 
	 * @param number Number part of the code that identify the shift. Number is between 0 and 99.
	 */
	public Shift(char literal, int number) {
		this.literal = literal;
		this.number = number;
	}

	/**
	 * <b>Des:</b> Constructor for Shifts that will have assigned users.<br>
	 * @param literal Literal part of the code that identify the shift. Literal is between 'A' and 'Z'. 
	 * @param number Number part of the code that identify the shift. Number is between 0 and 99.
	 * @param assignedIdUser User that have reserved this shift. User must exist in the users list.
	 * @param attended  A boolean value that indicate if the shift has been attended.
	 */
	public Shift(char literal, int number, User assignedIdUser, boolean attended) {
		this.literal = literal;
		this.number = number;
		this.assignedIdUser = assignedIdUser;
		this.attended = attended;
		this.active=true;
	}
	
	//---------------------------------------------------------
	//Getters and Setters
	//---------------------------------------------------------
	
	public char getLiteral() {
		return literal;
	}


	public int getNumber() {
		return number;
	}


	public User getAssignedIdUser() {
		return assignedIdUser;
	}

	
	public boolean wasAttended() {
		return attended;
	}
	
	public void setAttended(boolean attended) {
		this.attended = attended;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active=active;
	}
	
	/**
	 * <b>Des:</b> This method join the literal part and the number part of the code.<br> 
	 * @return The union of the parts of the shift code. 
	 */
	public String getStringShift() {
		return number<10 ? ""+literal+"0"+number : ""+literal+number;
	}
	
	//--------------------------------------------------------------
	//Metodos Analizadores
	//--------------------------------------------------------------
	
	/**
	 * <b>Des:</b> This method set the code like the next code.<br>
	 * <b>POST:</b> The attribute literal change to the next character, and the attribute number increases one. Rank of literal[A-Z], Rank of number[0-99].<br>
	 */
	public void nextShift() {
		number++;
		if(number==100) {
			number=0;
			literal++;
		}
		if(literal==91)
			literal='A';
	}

}