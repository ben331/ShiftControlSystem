package model;

import customException.*;
import java.util.ArrayList;
/**
 * @author bengi
 *
 */
public class ShiftControl {
	
	//------------------------------------------------------------------------------------------------------------------------
	//Relaciones
	//------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Users list that belong to the entity. 
	 */
	private ArrayList<User> users;
	
	/**
	 * Registered Shifts by users. 
	 */
	private ArrayList<Shift> shifts;
	
	/**
	 * Current shift. Shift that is being attended.
	 */
	private Shift currentShift;
	
	/**
	 * Next available shift for a user.
	 */
	private Shift availableShift;
	
	//------------------------------------------------------------------------------------------------------------------------
	//Getters
	//------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public ArrayList<Shift> getShifts(){
		return shifts;
	}
	
	public Shift getCurrentShift() {
		return currentShift;
	}

	public Shift getAvailableShift() {
		return availableShift;
	}

	
	
	//------------------------------------------------------------------------------------------------------------------------
	//Constructor
	//------------------------------------------------------------------------------------------------------------------------
	/**
	 * <b>Des:</b> Default constructor of a shift object.<br>
	 * <b>Post:</b> The user list and the shifts list are instanced. The current shift and the available shift are initialized with the code 'A00'.<br>
	 */
	public ShiftControl() {
		users = new ArrayList<User>();
		shifts = new ArrayList<Shift>();
		currentShift = new Shift('A', 0);
		availableShift = new Shift('A', 0);
	}
	
	//------------------------------------------------------------------------------------------------------------------------
	//Metodos Analizadores
	//------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * <b>Des:</b> This method assign an user, with a particular id, to a shift that is added in the shifts list.<br>
	 * If the user have a active shift, the method return the same shift.
	 * If the user don't have an active shift, the method created a new shift in the list with the first available code, associated with the user's id.
	 * <b>Pre: </b> the user is in the users list.<br>
	 * @param id Code of a user's identification document. id!=null.
	 * @return A message that confirm the shift code assigned to the user.
	 * @throws NullPointerException When the users is not founded in the list of users.
	 */
	public String assignShiftToUser(String id) throws NullPointerException{
		
		User user = searchUser(id);
		Shift shift = activeShiftOfUser(id);
		if(shift==null) {
			shifts.add(new Shift(availableShift.getLiteral(),availableShift.getNumber(),user,false));
			availableShift.nextShift();
		}
		return "Shift "+shift.getStringShift()+" asigned to user "+user.getName();
	}
	
	/**
	 * <b>Des:</b> This method search a user in the users list with his id.<br>
	 * @param id Code of a user's identification document. id!=null
	 * @return User A user in the users list with the same id. If the user is not in the list, return null. 
	 */
	public User searchUser(String id) {
		
		User user=null;
		for(int i=0; i<users.size();i++) {
			
			if(users.get(i).getId().equals(id)) {
				
				user=users.get(i);
			}
		}
		return user;
	}
	
	/**
	 * <b>Des:</b> This method search an active shift, in the list shifts, with the id of its assignedUser same to the given id.<br>
	 * @param id Code of a user's identification document. id!=null
	 * @return shift Is a shift corresponding to the user with the given id. (shift can be null).
	 */
	public Shift activeShiftOfUser(String id) {
		Shift shift =null;
		for(int i=0; i<shifts.size();i++) {
			
			if(shifts.get(i).getAssignedIdUser().getId().equals(id)  &&  shifts.get(i).isActive()){
				shift=shifts.get(i);
			}
		}
		
		return shift;
	}
	
	/**
	 * <b>Des:</b> This method create and add a new user to the users list with a given parameters, only if the parameters are valid.<br>
	 * <b>Pre:</b> The parameters id, idType, name and last names are not empty. The user is not already registered in the users list.<br>
	 * <b>Post:</b> The user created are added to the users list. <br>
	 * @param id Code of a user's identification document. id!=null id!=""
	 * @param idType Type of user identification document. idType!=null idType!=""
	 * @param name User's name. name!=null name!=""
	 * @param lastNames last names's user. lastNames !=null lastNames!=""
	 * @param address User's address.
	 * @param phoneNumber User's phone number.
	 * @throws EmptyDataException When a required parameter are empty.
	 * @throws DoubleRegistrationException When exist an user with the same id code.
	 */
	public void registerNewUser(String id, String idType, String name, String lastNames, String address, String phoneNumber) throws EmptyDataException, DoubleRegistrationException {
		
		if((id.equals("") || idType.equals("")) || (name.equals("") || lastNames.equals("")) ){
			throw new EmptyDataException(id, idType, name, lastNames);
		}
		else if(existUserWithId(id)) {
			throw new DoubleRegistrationException(id);
		}
		else {
			users.add(new User(id, idType, name, lastNames, address, phoneNumber));
		}
	}
	
	/**
	 * <b>Des:</b>This method determine if exist an user with a particular id.<br>
	 * @param id Code of a user's identification document. id!=null
	 * @return A boolean value that indicate if exist a user in users list with the given id.
	 */
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
	
	/**
	 * <b>Des:</b>This method modify the shift that is in the position of the current shift. The attribute active change to false and the attribute attended is assigned.<br>
	 * <b>Pre:</b>The shift to attend has been registered before.<br>
	 * <b>Post:</b>The code of the 'currentShift' change to the next.<br>
	 * @param attended It´s a boolean value that indicate if the user was attended or if he wasn't present.
	 * @throws UnreservedShiftException If the shift to attend has not been registered before.
	 */
	public void attendShift(boolean attended) throws UnreservedShiftException {
		
		boolean attendedShift=false;
		for(int i=0; i<shifts.size();i++){
			
			if(shifts.get(i).getStringShift().equals(currentShift.getStringShift())) {
				shifts.get(i).setAttended(attended);
				shifts.get(i).setActive(false);
				currentShift.nextShift();
				attendedShift=true;
				break;
			}
		}
		if(!attendedShift)
			throw new UnreservedShiftException(currentShift.getStringShift());
	}
	
	/**
	 * <b>Des:</b>This method generate a report indicating the user corresponding to each shift, and if he was attended or not.<br>
	 * @return report Is a text that contents the report of all assigned shifts. shifts!=null shifts!=""
	 */
	public String generateReport() {
		String report="----------------------------------------------\nSHIFTS REPORT\n----------------------------------------------\n\n";
		report+="      Shift       ID                       User                         Attended";
		
		for(int i=0; i<shifts.size();i++) {
			
			report+="\n   "+shifts.get(i).getStringShift()+"   "+shifts.get(i).getAssignedIdUser().getId()+"      "+shifts.get(i).getAssignedIdUser().getName()+"             "+shifts.get(i).wasAttended();
		}
		return report;
	}
}
