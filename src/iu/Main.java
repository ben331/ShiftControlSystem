package iu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.util.Scanner;
import model.*;
public class Main {
	
	public static Scanner reader = new Scanner(System.in);
	public static ShiftManager shiftManager;

	public static void main(String[] args) {
		
		//Variables
		
		int option=0;
		long time = 0;
		int option2=0;
		float duration;
		
		String shiftTypeName="";
		
		try {
			loadShiftManager();
			System.out.println("Data loaded");
		} catch (ClassNotFoundException e2) {
			System.out.println("Welcome for first Time!");
			shiftManager = new ShiftManager();
		} catch (IOException e2) {
			e2.printStackTrace();
			shiftManager = new ShiftManager();
		}
		
		System.out.println("System Control Shift");
		
		do {
			
			//Menu
			System.out.println("\n"+shiftManager.getDateTime().toString());
			System.out.print("Available Shift: "+ shiftManager.getAvailableShift().toString());
			System.out.println("         Current Shift: "+shiftManager.getCurrentShift().toString());
			System.out.println("\nMenu\nSelect an Option: \n1.Generate users \n2.Generate and register shifts randomly \n3.Attend Shifts until the moment \n4.Assign new type of shift. \n5.Show Shifts Report \n6.Refresh and Save data \n7 Set Date and Time\n8.Exit");
			option=reader.nextInt();
			reader.nextLine();
			
			switch(option) {
			
			case 1://Generate users
				System.out.println("Type the quanty of users to generate: ");
				int u = reader.nextInt();
				reader.nextLine();
				
				try {
					time=System.currentTimeMillis();
					shiftManager.generateUsers(u);
					time=System.currentTimeMillis()-time;
					System.out.println(u+" users generated. (Time: "+time+"ms.)");
				} catch (IOException e1) {
					System.out.println("File 'Data/names.txt' or 'Data/lastNames.txt' was not Found. It could been removed o change of location");
				}
				break;
			
			case 2:
				System.out.println("Type the number of days to generate shifts: ");
				int days = reader.nextInt();
				reader.nextLine();
				
				System.out.println("Type the number of shifts to generate per day: ");
				int shiftsPerDay = reader.nextInt();
				reader.nextLine();
				
				try {
					time = System.currentTimeMillis();
					shiftManager.generateShifts(days, shiftsPerDay);
					time = System.currentTimeMillis() - time;
					System.out.println(days*shiftsPerDay+" shifts was registered randomly. (Time: "+time+"ms.)");
				}catch(NullPointerException e) {
					System.out.println(e.getMessage());
				}catch(IndexOutOfBoundsException e) {
					time = System.currentTimeMillis() - time;
					System.out.println(e.getMessage()+" (Time: "+time+"ms.)");
				}
				break;
			
			case 3:
				time = System.currentTimeMillis();
				String shiftsAttended = shiftManager.attendShifts();
				time = System.currentTimeMillis() - time;
				if(shiftsAttended.equals(""))
					System.out.print("No shifts was attended");
				else
					System.out.print(shiftsAttended);
				System.out.println(" (time: "+time+"ms)");
				break;
			case 4:
				System.out.println("Type the name of the shift type:");
				shiftTypeName=reader.nextLine();
				System.out.println("Type the duration of this shift type in minuts:                 Ex:(2,5)");
				duration = reader.nextFloat();
				time = System.currentTimeMillis();
				String msg = shiftManager.addTypeShift(shiftTypeName, duration);
				time = System.currentTimeMillis() - time;
				System.out.println(msg + " (Time:"+time+"ms.)");
				break;
			
			case 5:
				time=System.currentTimeMillis();
				msg = shiftManager.generateReport();
				time=System.currentTimeMillis() - time;
				System.out.println(msg+" (Time: "+time+"ms)");
				break;
			
			case 6:
				shiftManager.refresh();
				try {
					saveShiftManager();
				} catch (IOException e1) {
				}
				break;
			
			case 7:
				System.out.println("\n1.Manual\n2.Automathic");
				option2=reader.nextInt();
				reader.nextLine();
				
				switch(option2) {
				case 1:
					System.out.println("Type the year:      (2020)");
					int year = reader.nextInt();
					reader.nextLine();
					
					System.out.println("Type the month (1-12):");
					int month = reader.nextInt();
					reader.nextLine();
					
					System.out.println("Type the day of month (1-31):");
					int day = reader.nextInt();
					reader.nextLine();
					
					System.out.println("Type hour (0-23):");
					int hour = reader.nextInt();
					reader.nextLine();
					
					System.out.println("Type the min (0-59):");
					int min = reader.nextInt();
					reader.nextLine();
					
					System.out.println("Type the second (0-59):");
					int second = reader.nextInt();
					reader.nextLine();
					
					try {
						time = System.currentTimeMillis();
						shiftManager.getDateTime().setDateTimeManual(year, month, day, hour, min, second);
						time = System.currentTimeMillis() - time;
						System.out.print("Date Time updated");
					}catch(DateTimeException e) {
						System.out.println("The value of some field is out of range, or the day of month is invalid for the month/year");
					}
					break;
					
					
					
				case 2:
					time = System.currentTimeMillis();
					shiftManager.getDateTime().setDateTimeAutomathic();
					time = System.currentTimeMillis() - time;
					System.out.print("Date and Time was updated using the current date and time of the computer system.\n");
					break;
				}
				System.out.println(" Time: "+time+"ms.");
				shiftManager.refresh();
				break;
				
				
			case 8:
				try {
					saveShiftManager();
				} catch (IOException e) {
				}
				System.out.println("Good Bye ;)");
				break;
				
				
			default:
				System.out.println("Invalided Option");
				break;
			}
			
		}while(option!=8);
	}
	
	public static void saveShiftManager() throws IOException{
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/ShiftManager.srl"));
		
		oos.writeObject(shiftManager);
		
		oos.close();
	}
	
	public static void loadShiftManager() throws IOException, ClassNotFoundException{
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/ShiftManager.srl"));
		
		shiftManager=(ShiftManager)ois.readObject();
		
		ois.close();
	}
}
