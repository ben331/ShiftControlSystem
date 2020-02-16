package test;

import model.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customException.DoubleRegistrationException;
import customException.EmptyDataException;
import customException.UnreservedShiftException;

class ShiftControlTest {
	
	private ShiftControl control;
	
	void setup1(){
		
		control = new ShiftControl();
		
		//Users
		
		User benjamin = new User("1007378465", User.CC,"Benjamin","Silva S","","");
		User reyes = new User("15872632",User.CC, "Juan","Reyes","","");
		User anderson = new User("1008253222",User.PASAPORTE,"Anderson","Cardenas","","");
		control.getUsers().add(benjamin);
		control.getUsers().add(reyes);
		control.getUsers().add(anderson);
		
		//Shifts
		
		control.getShifts().add(new Shift('A',0,reyes,false)); //
		control.getAvailableShift().nextShift();              // Pretense assign and attend a shift. (Reyes has an inactive shift).
		control.getCurrentShift().nextShift();                //
		
		
		control.getShifts().add(new Shift('A',1,benjamin,true)); //
		control.getAvailableShift().nextShift();                  //Pretense assign a shift. (Benjamin has an active shift).
		
		//Two shifts was assigned and one shift was attended , so availableShift=A02, and cuurentShift=A01.
	}
	void setup2() {
		control.getUsers().clear();
	}

	@Test
	void testRegisterNewUser() {
		
		
		// I. The User doesn't exist. And There are Users in the system.
		try {
			setup1();
			control.registerNewUser("1004581240", User.CC, "Manuel", "alvarez", "", "");
			
			assertEquals(control.getUsers().get(3).getId(),"1004581240","User added Incorrectly");
		} catch (EmptyDataException e) {
			fail("Wrong Exception");
		} catch (DoubleRegistrationException e) {
			fail("Wrong Exception");
		}
		
		
		//II. The user already exist. (Benjamin)
		try {
			setup1();
			control.registerNewUser("1007378465", User.CC, "Diego", "reyes", "", "");
			fail("DoubleRegistrationException unhandled");
		} catch (EmptyDataException e) {
			fail("Wrong Exception");
		} catch (DoubleRegistrationException e) {
			assertTrue(true);
		}
		
		//III. There are not users in the system.
		try {
			setup2();
			control.registerNewUser("1007378465", User.CC, "Benjamin", "Silva S", "", "");
			assertEquals(control.getUsers().get(0).getId(),"1007378465","User added Incorrectly");
		} catch (EmptyDataException e) {
			fail("Wrong Exception");
		} catch (DoubleRegistrationException e) {
			fail("Wrong Exception");
		}
		
		//IV.The users have id, typeId, name or lastnames empty.
		
		//Empty id.
		try {
			setup1();
			control.registerNewUser("", User.CC, "Benjamin", "Silva S", "Calle 31c #11-84", "314787608");
			assertEquals(control.getUsers().get(2).getId(),"1007378465","User added Incorrectly");
			fail("EmptyDataException unhandled");
		} catch (EmptyDataException e) {
			assertTrue(true);
		} catch (DoubleRegistrationException e) {
			fail("Wrong Exception");
		}
		
		//Empty typeId.
		try {
			setup2();
			control.registerNewUser("1007378465", "", "Benjamin", "Silva S", "Calle 31c #11-84", "314787608");
			assertEquals(control.getUsers().get(0).getId(),"1007378465","User added Incorrectly");
			fail("EmptyDataException unhandled");
		} catch (EmptyDataException e) {
				assertTrue(true);
		} catch (DoubleRegistrationException e) {
			fail("Wrong Exception");
		}
		
		//Empty Name
		try {
			setup1();
			control.registerNewUser("1007378465",User.CC, "", "Silva S", "Calle 31c #11-84", "");
			assertEquals(control.getUsers().get(2).getId(),"1007378465","User added Incorrectly");
			fail("EmptyDataException unhandled");
		} catch (EmptyDataException e) {
				assertTrue(true);
		} catch (DoubleRegistrationException e) {
			fail("Wrong Exception");
		}
		

		//Empty LastNames
		try {
			setup2();
			control.registerNewUser("Benjamin", User.CC, "Benjamin", "", "", "314787608");
			assertEquals(control.getUsers().get(0).getId(),"1007378465","User added Incorrectly");
			fail("EmptyDataException unhandled");
		} catch (EmptyDataException e) {
				assertTrue(true);
		} catch (DoubleRegistrationException e) {
			fail("Wrong Exception");
		}
	}
	
	@Test
	void testSearchUser() {
		
		//User searched exist. And There are users in the system.
		setup1();
		User user = control.searchUser("1007378465");
		assertTrue(user!=null);
		assertEquals(user.getName(),"Benjamin");
		assertEquals(control.getUsers().indexOf(user),0);
		
		//User searched does not exist.
		setup1();
		user = control.searchUser("9999999");
		assertTrue(user==null);
		
		//There are not users in the system.
		setup2();
		user = control.searchUser("9999999");
		assertTrue(user==null);
		
		user = control.searchUser("1007379465");
		assertTrue(user==null);
	}
	
	@Test
	void testAssignShiftToUser() {
		
		//I.The User have an active shift
		setup1();
		try {
			control.assignShiftToUser("1007378465");
			assertEquals(control.activeShiftOfUser("1007378465"),control.getShifts().get(1));
			assertEquals(control.getAvailableShift().getStringShift(),"A02");
		} catch(NullPointerException e) {
			fail("Wrong exception");
		}
		
		//II. The user doesn't have an active shift.
		setup1();
		try {
			assertEquals(control.assignShiftToUser("15872632"),"Shift A02 asigned to user Juan"); //Reyes had the inactive shift A00.
			assertEquals(control.activeShiftOfUser("15872632").getStringShift(),control.getShifts().get(2).getStringShift());
			assertEquals(control.getAvailableShift().getStringShift(),"A03");
		} catch(NullPointerException e) {
			fail("Wrong exception");
		}
		
		//III. The user doesn't have any shift.
		setup1();
		try {
			control.assignShiftToUser("1008253222");
			assertEquals(control.activeShiftOfUser("1008253222"),control.getShifts().get(2));
			assertEquals(control.getAvailableShift().getStringShift(),"A03");
		} catch(NullPointerException e) {
			fail("Wrong exception");
		}
		
		//IV. The user doesn't exist
		setup1();
		try {
			control.assignShiftToUser("888888888");
			fail("NullPointerException unhandled");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	//The test of operation "generate new shift" is tested in lines #165, #175, #185. This method is called 'nextShift' and it's tested in the class 'ShiftTest' too.
	
	@Test
	void testAttendShift() {
		
		//I.There are shifts for attend.
		setup1();
		try {
			control.attendShift(true); //Se atiende el turno de benjamin
			assertEquals(control.getShifts().get(1).isActive(),false);
			assertEquals(control.getShifts().get(1).wasAttended(),true);
			assertEquals(control.getCurrentShift().getStringShift(),"A02");
			
		} catch(UnreservedShiftException e) {
			fail();
		}
		
		//II. There are nor shifts for attend.
		setup2();
		try {
			control.attendShift(true); //Se intenta atender un turno que no ha sido asignado.
			fail();
		} catch(UnreservedShiftException e) {
			assertTrue(true);
		}
	}
}