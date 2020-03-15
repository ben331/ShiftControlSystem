package model;

import java.io.Serializable;

/**
 * @author bengi
 *
 */
@SuppressWarnings("serial")
public class Shift implements Serializable {
	
	//-----------------------------------------
	//Associations
	//-----------------------------------------
	/**
	 * User who have reserved this shift 
	 */
	private User assignedUser;
	
	//-----------------------------------------
	//Attributes
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
	 * A boolean value that indicate if the shift has been attended
	 */
	private boolean attended;
	
	/**
	 * A boolean value that indicate if the shift is in hold or not.
	 */
	private boolean active;
	
	/**
	 * An ShiftType that contents the name of the type shift and the duration of these shifts.
	 */
	private ShiftType shiftType;
	
	/**
	 * A time when the shift will be attended in milliseconds,
	 */
	private long timeForAttend;
	
	//-----------------------------------------
	//Constructors
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
	 * @param active  A boolean value that indicate if the shift is on hold.
	 * @param shiftType An object that represents the type of shift and its respective duration. shiftType!=null.
	 */
	public Shift(char literal, int number, User assignedIdUser, boolean active, ShiftType shiftType, long timeForAttend) {
		this.literal = literal;
		this.number = number;
		this.assignedUser = assignedIdUser;
		this.active=active;
		this.shiftType=shiftType;
		this.timeForAttend = timeForAttend;
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


	public User getAssignedUser() {
		return assignedUser;
	}

	
	public boolean wasAttended() {
		return attended;
	}
	
	public ShiftType getShiftType() {
		return shiftType;
	}
	
	public long getTimeForAttend() {
		return timeForAttend;
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
	@Override
	public String toString() {
		return number<10 ? ""+literal+"0"+number : ""+literal+number;
	}
	
	//--------------------------------------------------------------
	//Analyzers Methods
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