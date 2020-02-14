package test;

import model.User;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
	
	private User user;
	
	//---------------------------------------------
	//Escenarios
	//---------------------------------------------
	
	public void setUp1() {
		
	 user = new User("1007378465",User.CC,"Benjamin","Silva Salgado","Calle 31C #11-84","3174787608");
	}
	
	public void setUp2() {
		
		user = new User("14893632",User.TI,"Felipe","Morales Hernandez","","");
	}
	
	//---------------------------------------------
	//metodos de prueba
	//---------------------------------------------
	
	@Test
	void testUser() {
		setUp1();
		
		assertEquals(user.getIdType(),User.CC);
		assertEquals(user.getId(),"1007378465");
		assertEquals(user.getName(),"Benjamin");
		assertEquals(user.getLastNames(),"Silva Salgado");
		assertEquals(user.getAddress(),"Calle 31C #11-84");
		assertEquals(user.getPhoneNumber(),"3174787608");
		
		setUp2();
		
		assertEquals(user.getIdType(),User.TI);
		assertEquals(user.getId(),"14893632");
		assertEquals(user.getName(),"Felipe");
		assertEquals(user.getLastNames(),"Morales Hernandez");
		assertEquals(user.getAddress(),"");
		assertEquals(user.getPhoneNumber(),"");
		
	}
}
