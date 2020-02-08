package model;

import customException.*;
import java.util.ArrayList;
public class ShiftControl {
	
	//------------------------------------------------------------------------------------------------------------------------
	//Relaciones
	//------------------------------------------------------------------------------------------------------------------------
	private ArrayList<User> users;
	private ArrayList<Shift> shifts;
	private Shift currentShift;
	private Shift availableShift;
	
	//------------------------------------------------------------------------------------------------------------------------
	//Constructor
	//------------------------------------------------------------------------------------------------------------------------
	public ShiftControl() {
		users = new ArrayList<User>();
		shifts = new ArrayList<Shift>();
		currentShift = new Shift((char)65, 0);
		availableShift = new Shift((char)65, 0);
	}
	
	//------------------------------------------------------------------------------------------------------------------------
	//Metodos Analizadores
	//------------------------------------------------------------------------------------------------------------------------
	
	
	public String searchUserAndAssignShift(String id) {
		
		String message="User not founded";
		
		for(int i=0; i<users.size();i++) {
			
			if(users.get(i).getId().equals(id)) {
				
				shifts.add(new Shift(availableShift.getLiteral(),availableShift.getNumber(),users.get(i),false));
				message="Shift "+availableShift.getStringShift()+" asigned to user "+users.get(i).getName();
				availableShift.nextShift();
				break;
			}
		}
		return message;
	}
	
	public String registerNewUser(String id, String idType, String name, String lastNames, String address, String phoneNumber) throws EmptyDataException, DoubleRegistrationException {
		
		if((id.equals("") || idType.equals("")) || (name.equals("") || lastNames.equals("")) ){
			throw new EmptyDataException(id, idType, name, lastNames);
		}
		else if(existUserWithId(id)) {
			throw new DoubleRegistrationException(id);
		}
		else {
			users.add(new User(id, idType, name, lastNames, address, phoneNumber));
		}
		return "User added correctly";
	}
	
	public boolean existUserWithId(String id) {
		
		boolean existingUser=false;
		
		for(int i=0; i<users.size();i++) {
			
			if(users.get(i).getId().equals(id)) {
				existingUser=true;
				break;
			}
		}
		
		return existingUser;
	}
	
	public void attendShift(boolean attended) throws UnreservedShiftException {
		
		boolean reservedShift=false;
		for(int i=0; i<shifts.size();i++){
			
			if(shifts.get(i).getStringShift().equals(currentShift.getStringShift())) {
				shifts.get(i).setAttended(attended);
				currentShift.nextShift();
				reservedShift=true;
				break;
			}
		}
		if(!reservedShift)
			throw new UnreservedShiftException(currentShift.getStringShift());
	}
}
