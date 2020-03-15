package model;

import model.Shift;
import model.User;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShiftTest {
	
	private User pepito;
	
	private Shift shift;
	
	void setUp1(){
		shift = new Shift('D',99);
	}
	
	void setUp2(){
		shift = new Shift('Z',99);
	}
	
	
	void setUp3() {
		pepito = new User("1",User.TI, "pepito","perez","","");
		shift = new Shift('F',42,pepito,false,null);
	}
	
	//Test Getters and Setter
	@Test
	void testShift() {
		setUp3();
		assertEquals(shift.getLiteral(),'F');
		assertEquals(shift.getNumber(),42);
		assertEquals(shift.getStringShift(),"F42");
		assertEquals(shift.isActive(),false);
		assertEquals(shift.getAssignedUser(),pepito);
		assertEquals(shift.wasAttended(),false);
		
		shift.setActive(true);
		assertEquals(shift.isActive(),true);
		
		shift.setAttended(true);
		assertEquals(shift.wasAttended(),true);
		
	}

	@Test void testNextSShift() {
		
		//D99 --> E00
		setUp1();
		shift.nextShift();
		assertEquals(shift.getStringShift(),"E00");
		
		//Z99 --> A00
		setUp2();
		shift.nextShift();
		assertEquals(shift.getStringShift(),"A00");
	}
}
