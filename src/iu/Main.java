package iu;

import java.util.Scanner;

import customException.*;
import model.*;
public class Main {
	
	public static Scanner reader = new Scanner(System.in);
	public static ShiftControl shiftControl = new ShiftControl();

	public static void main(String[] args) {
		
		//Variables
		
		int option=0;
		boolean validated=false;
		
		int option2=0;
		String id="";
		String idType="";
		String name="";
		String lastNames="";
		String address="";
		String phoneNumber="";
		boolean attended;
		
		System.out.println("Welcome to ShiftControl");
		
		do {
			
			//Menu
			
			System.out.println("\nSelect an Option: \n1.Add User \n2.Register Shift \n3.Attend Shift \n4.Show Shifts Report \n5.Exit");
			option=reader.nextInt();
			reader.nextLine();
			
			switch(option) {
			
			case 1://Añadir un Usuario
				System.out.println("Add User.                             (Fields with * are required)\n\n");
				
				do {
					
					
					//Leer Tipo de Documento
					System.out.println("*Select the Id Type: \n1.Cédula de Ciudadania \n2.Tarjeta de Identidad \n3.Registro Civil \n4.Pasaporte \n5.Cédula de Extranjería");
					option2=reader.nextInt();
					reader.nextLine();
					
					switch(option2) {
					
					case 1:
						idType=User.CC;
						validated=true;
					break;
					case 2:
						idType=User.TI;
						validated=true;
					break;
					case 3:
						idType=User.REGISTRO_CIVIL;
						validated=true;
					break;
					case 4:
						idType=User.PASAPORTE;
						validated=true;
					break;
					case 5:
						idType= User.CE;
						validated=true;
					break;
					default:
						System.out.println("Invalided Option");
					break;
					}
				}while(!validated);
				
				
				//Leer Número de Documento
				System.out.println("*Type the id: ");
				id= reader.nextLine();
				
				//Leer Nombre
				System.out.println("*Type the name: ");
				name= reader.nextLine();
				
				//Leer Apellidos
				System.out.println("*Type the last names: ");
				lastNames=reader.nextLine();
				
				//Leer Direccion
				System.out.println("Type the address: ");
				address= reader.nextLine();
				
				//Leer Número de Teléfono
				System.out.println("Type the phone number: ");
				phoneNumber= reader.nextLine();
				
				try {
					
					shiftControl.registerNewUser(id, idType, name, lastNames, address, phoneNumber);
					System.out.println("User added correctly");
					
				}catch(EmptyDataException e) {
					
					System.out.println("User was not added.\n"+e.getMessage()+"\nYou must type the information of all requeride fields (*). (Id Type, Id, Name and Last Names.)");
					
				}catch(DoubleRegistrationException f) {
					
					System.out.println("User was not added. "+f.getMessage());
				}
				
			break;
			
			case 2:
				System.out.println("Register Shift\n\n");
				System.out.println("Type the id user: ");
				id= reader.nextLine();
				
				try {
					
					System.out.println(shiftControl.searchUser(id).toString());
					
					System.out.println("\n1.assign shift. \n2.Cancel");
					option2=reader.nextInt();
					reader.nextLine();
					
					if(option2==1) 
						System.out.println(shiftControl.assignShiftToUser(id));

				}catch(NullPointerException e) {
					System.out.println("User Not Found with id: "+id);
				}
				
			break;
			
			case 3:
				option2=1;
				while(option2==1) {
					
					System.out.println("Current Shift: "+ shiftControl.getCurrentShift().getStringShift());

					if(option2==1){
						
						System.out.println("\nwas the user attended? \n1.Yes \n2.No");
						option2=reader.nextInt();
						reader.nextLine();
						
						if(option2==1)
							attended=true;
						else
							attended=false;
						
						try {
							shiftControl.attendShift(attended);
							System.out.println("Shift Registered");
						}catch(UnreservedShiftException e) {
							System.out.println(e.getMessage()+" Please, wait until some user reserve the shift.");
						}
					}
					
					System.out.println("Current Shift: "+ shiftControl.getCurrentShift().getStringShift());
					System.out.println("\n1.Attend Shift \n2.Back");
					option2=reader.nextInt();
				}
			break;
			
			case 4:
				System.out.println("Good Bye ;)");
			break;
			
			
			case 5:
				System.out.println(shiftControl.generateReport());
			break;
			default:
				System.out.println("Invalided Option");
			break;
			}
			
		}while(option!=5);
	}
}
