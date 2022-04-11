package coreAPI.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


public class TestUserValidation 
{
	@BeforeEach
	void beforeTest()
	{
		user = new User();
		validation = new UserValidation(user);
	}
	
	User user;
	UserValidation validation;

	@Test
	void TestIsValidUserName()
	{		
		user.setUserName("tony12");
		assertTrue("When user name has valid lettes", validation.isValidUserName());
		
		user.setUserName("tony 12");
		assertFalse(validation.isValidUserName());
		
		user.setUserName("tony#12");
		assertFalse(validation.isValidUserName());		
	}
	
	@Test
	void TestIsValidPassword()
	{
		fail("add your test");
	}
	
	
}
