package model;

import customException.*;
import java.io.IOException;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * @author bengi
 *
 */
@SuppressWarnings("serial")
public class ShiftManager implements Serializable {
	
	//------------------------------------------------------------------------------------------------------------------------
	//Associations
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
	 * Shift types added by users
	 */
	private ArrayList<ShiftType> shiftTypes;
	
	/**
	 * Current shift. Shift that is being attended.
	 */
	private Shift currentShift;
	
	/**
	 * Next available shift for a user.
	 */
	private Shift availableShift;
	
	private DateTime dateTime;
	
	private long availableTime;
	
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
	
	public DateTime getDateTime() {
		return dateTime;
	}
	
	public long getAvailableTime() {
		return availableTime;
	}
	
	public void setAvailableTime(long millis) {
		availableTime = millis;
	}
	
	public ArrayList<ShiftType> getShiftTypes(){
		return shiftTypes;
	}

	
	
	//------------------------------------------------------------------------------------------------------------------------
	//Constructor
	//------------------------------------------------------------------------------------------------------------------------
	/**
	 * <b>Des:</b> Default constructor of a shift object.<br>
	 * <b>Post:</b> The user list and the shifts list are instanced. The current shift and the available shift are initialized with the code 'A00'.<br>
	 */
	public ShiftManager() {
		users = new ArrayList<User>();
		shifts = new ArrayList<Shift>();
		shiftTypes = new ArrayList<ShiftType>();
		currentShift = new Shift('A', 0);
		availableShift = new Shift('A', 0);
		dateTime = new DateTime();
		availableTime = System.currentTimeMillis();
	}
	
	//------------------------------------------------------------------------------------------------------------------------
	//Analyzers Methods
	//------------------------------------------------------------------------------------------------------------------------
	
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
		else if(searchUser(id)!=null) {
			throw new DoubleRegistrationException(id);
		}
		else {
			users.add(new User(id, idType, name, lastNames, address, phoneNumber));
		}
	}
	
	/**
	 * <b>Des:</b> This method search an active shift, in the list shifts, with the id of its assignedUser same to the given id.<br>
	 * @param id Code of a user's identification document. id!=null
	 * @return shift Is a shift corresponding to the user with the given id. (shift can be null).
	 */
	public Shift activeShiftOfUser(String id) {
		Shift shift =null;
		for(int i=0; i<shifts.size();i++) {
			
			if(shifts.get(i).getAssignedUser().getId().equals(id)  &&  shifts.get(i).isActive()){
				shift=shifts.get(i);
			}
		}
		
		return shift;
	}
	
	/**
	 * <b>Des:</b>This method modify the shift that is in the position of the current shift. The attribute active change to false and the attribute attended is assigned.<br>
	 * <b>Pre:</b>The shift to attend has been registered before.<br>
	 * <b>Post:</b>The code of the 'currentShift' change to the next.<br>
	 * @param attended It´s a boolean value that indicate if the user was attended or if he wasn't present.
	 * @throws UnreservedShiftException If the shift to attend has not been registered before.
	 */
	public void attendShift(boolean attended) throws UnreservedShiftException {
		
		
	}
	
	/**
	 * <b>Des:</b>This method generate a report indicating the user corresponding to each shift, and if he was attended or not.<br>
	 * @return report Is a text that contents the report of all assigned shifts. shifts!=null shifts!=""
	 */
	public String generateReport() {
		
		String wasAttended="";
		String wasPresent="";
		
		String report="          ----------------------------------------------\n                    SHIFTS REPORT\n          ----------------------------------------------\n\n";
		report+="   Shift     ID            User         Attended    Was present";
		
		for(int i=0; i<shifts.size();i++) {
			wasAttended = shifts.get(i).isActive()?"No":"Yes";
			wasPresent = shifts.get(i).wasAttended()?"Yes":"No";
			report+="\n   "+shifts.get(i).toString()+"   "+shifts.get(i).getAssignedUser().getId()+"      "+shifts.get(i).getAssignedUser().getName();
			for(int j=0; j<20-shifts.get(i).getAssignedUser().getName().length();j++) {
				report+=" ";
			}
			report+=wasAttended+"      "+wasPresent+"\n";
		}
		return report;
	}
	
	public String showShiftTypes() {
		String sts="";
		for(int i=0; i<shiftTypes.size();i++) {
			sts=(i+1)+".  "+shiftTypes.get(i).getName()+".  Duration: "+shiftTypes.get(i).getDuration()+" min.\n";
		}
		return sts;
	}
	
	public String addTypeShift(String name, float duration) {
		shiftTypes.add(new ShiftType(name, duration));
		return "Shift type added correctly.";
	}
	
	public void generateUsers(int u) throws IOException {
		int lineName;
		int lineLastName;
		int idType;
		String id;
		String name;
		String lastName;
		String[] names = new String[553];
		String[] lastNames = new String[100];
		
		BufferedReader br = new BufferedReader(new FileReader("Data/names.txt"));
		for(int i=0; i<553;i++) {
			names[i]= br.readLine();
		}
		br.close();
		
		br = new BufferedReader(new FileReader("Data/lastNames.txt"));
		for(int i=0; i<100;i++) {
			lastNames[i]= br.readLine();
		}
		br.close();
		
		for(int i=0; i<u;) {
			lineName = (int)(Math.random()*553);
			lineLastName = (int)(Math.random()*100);
			name = names[lineName];
			lastName = lastNames[lineLastName];
			idType = (int)Math.random()*5;
			id = ((int)(Math.random()*1000000000) + 1000000000)+"";
			try {
				registerNewUser(id, User.ID[idType],name,lastName, "","");
				i++;
			} catch (EmptyDataException | DoubleRegistrationException e) {
			}
		}
	}
	
	
	
	public int generateShifts(int days, int shiftsPerDay) {
		
		int shiftsGenerated=0;
		
		if(shiftTypes.size()==0)
			throw new NullPointerException("Shifts was not registered. You have to create shifts types before than assign shifts to users.");
		else if(users.size()==0)
			throw new NullPointerException("Shifts was not registered. You have to create users before than assign shifts to users.");
		
		boolean enoughUsers = true;
		
		long currentDay = DateTime.getMillisDayOfLDT(availableTime);                     // Date in milliseconds of the current day in which shifts are being registered.
		
		long finalDay = currentDay + days*86400000;       // End date after registering shifts during the specified days.
		
		ArrayList<Integer> indexAvailable = new ArrayList<Integer>();
		
		for(int i=0; i<users.size();i++) {
			indexAvailable.add(i);
		}
		
		while(currentDay<finalDay && enoughUsers) {                         // As long as the registration day is less than the final day.
			
			for(int i=0; (i<shiftsPerDay && enoughUsers) && availableTime < currentDay + 86400000  ;) {       // As long as the shifts registered are less than those indicated and the time of registration is less the next day ...
				
				int indexAva = (int)(Math.random()*indexAvailable.size());               // Select a random index of user
				int index = indexAvailable.get(indexAva);
				indexAvailable.remove(indexAva);                          //Remove the index generated of the possibles index in the next random index.
				
				User user = users.get(index);                      
				Shift shift = activeShiftOfUser(user.getId());
				ShiftType shiftType = shiftTypes.get((int)(Math.random()*shiftTypes.size()));  // Select a random type of turn.
				if(shift==null) {                                                      // If the selected user does not have an active shift.
					shift = new Shift(availableShift.getLiteral(),availableShift.getNumber(),user,true,shiftType, availableTime); 
					shifts.add(shift);                                                  // A shift is created and registered to that user on the available date and time.
					availableShift.nextShift();                                        // The code of the available turn is advanced.
					
					availableTime+=(long)(shiftType.getDuration()*60000 + 15000);   // The time available for the next turn will advance the duration of the previous turn plus 15 seconds.
					i++;                                                                              // Increase the number of registered turns.
					shiftsGenerated++;
				}
				if(indexAvailable.size()==0)
					enoughUsers=false;
			}
			
			if(!enoughUsers && shiftsGenerated<shiftsPerDay*days)
				throw new IndexOutOfBoundsException(shiftsGenerated+" shifts was generate. There was not enough users with inactive shifs for register "+shiftsPerDay+" shifts per day, during "+days+" day(s).");
			else if( availableTime < currentDay + 86400000) {  // If after registering the designated shifts, the time available for the next shift has not advances day ...
				availableTime = currentDay + 86400000;   // The time available to register, advances to the next day.
				currentDay = currentDay + 86400000;      // Advances to the next day.
			}
		}
		return shiftsGenerated;
	}
	
	public String attendShifts() {
		String msg="";
		
		dateTime.refresh(); 
		
		for(int i=0; i<shifts.size();i++) {
			
			if(shifts.get(i).isActive() && shifts.get(i).getTimeForAttend() <= dateTime.getMillis()) {
				
				double n = Math.random();
				boolean attended = n>0.5? true:false;
				
				shifts.get(i).setAttended(attended);
				shifts.get(i).setActive(false);
				currentShift.nextShift();
				
				msg+="Shift "+shifts.get(i).toString()+" was attended at "+DateTime.millisToLDT(shifts.get(i).getTimeForAttend()).toString()+"\n";
				
			}
		}
		return msg;
	}
	
	public void refresh() {
		dateTime.refresh();
		if(availableTime<dateTime.getMillis())
			availableTime = dateTime.getMillis();
	}
}
