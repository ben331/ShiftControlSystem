package test;

import model.User;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {
	
	//---------------------------------------------
	//Escenarios
	//---------------------------------------------
	
	public void setUp1() {
		
		User user = new User("1007378465",User.CC,"Benjamin","Silva Salgado","Calle 31C #11-84","3174787608");
	}
	
	public void setUp2() {
		
		User user = new User("14893632",User.TI,"Felipe","Morales Hernandez","","");
	}
	
	//---------------------------------------------
	//metodos de prueba
	//---------------------------------------------
	
	@Test
	void testUser() {
		setUp1();
		setUp2();
	}
}
