package test;

import model.Shift;
import model.User;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShiftTest {
	
	private Shift shift;

	void setUp1(){
		shift = new Shift('D',34);
	}
	
	
	void setUp2() {
		User pepito = new User("1",User.TI, "pepito","perez","","");
		shift = new Shift('F',42,pepito,false);
	}
	
	@Test
	void testShift() {
		
	}

}
