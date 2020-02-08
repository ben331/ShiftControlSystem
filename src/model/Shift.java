package model;

public class Shift {
	
	//-----------------------------------------
	//Atributos
	//-----------------------------------------
	
	private char literal;
	private int number;
	private User assignedIdUser;
	private boolean attended;
	
	
	//-----------------------------------------
	//Constructores
	//-----------------------------------------
	public Shift(char literal, int number) {
		this.literal = literal;
		this.number = number;
	}


	public Shift(char literal, int number, User assignedIdUser, boolean attended) {
		this.literal = literal;
		this.number = number;
		this.assignedIdUser = assignedIdUser;
		this.attended = attended;
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
	
	
	public String getStringShift() {
		String _number=""+number;
		return _number.length()==1 ? ""+literal+"0"+number : ""+literal+number;
	}
	
	//--------------------------------------------------------------
	//Metodos Analizadores
	//--------------------------------------------------------------
	
	public void nextShift() {
		number++;
		if(number==100) {
			number=0;
			literal++;
		}
		if(literal==91)
			literal=65;
	}
	
	public String toAssignUser(User user) {
		this.assignedIdUser=user;
		return"Shift "+getStringShift()+" asigned to user "+user.getName();
	}

}
