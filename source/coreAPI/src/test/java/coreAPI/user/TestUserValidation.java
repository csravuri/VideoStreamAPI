package coreAPI.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		user.setUserName("venkat12");
		assertTrue("When user name has valid lettes", validation.isValidUserName());
		
		user.setUserName("venkat 12");
		assertFalse(validation.isValidUserName());
		
		user.setUserName("venkat#12");
		assertFalse(validation.isValidUserName());		
	}
	
	@Test
	void TestIsValidPassword()
	{
		user.setPassword("Venkat@1");
		assertTrue("When password has valid lettes", validation.isValidPassword());
		
		user.setPassword("venkat12");
		assertFalse("Test 2", validation.isValidPassword());
		
		user.setPassword("venkatakrishna");
		assertFalse("Test 3", validation.isValidPassword());	

		user.setPassword("12345678");
		assertFalse("Test 4",validation.isValidPassword());	
		
		user.setPassword("AAAAAAA1");
		assertTrue("Test 5",validation.isValidPassword());	
	
		user.setPassword("aaaaaaaa");
		assertFalse("Test 5",validation.isValidPassword());	
	}
	
	@Test
	void TestIsValidEmail()
	{
		//fail("add your test");
		user.setEmail("venkata@gmail.com");
		assertTrue("When Email has valid lettes", validation.isValidEmail());
		
		user.setEmail("venkat12");
		assertFalse(validation.isValidEmail());
		
		user.setEmail("venkat@com");
		assertFalse(validation.isValidEmail());	

		user.setEmail("venkat dvk@gmail.com");
		assertFalse(validation.isValidEmail());	
	}
	
	@Test
	void TestIsValidDOB()
	{
		//fail("add your test");
		user.setDateOfBirth("1994-04-25");
		assertTrue("When DOB is valid", validation.isValidDOB());
		
		user.setDateOfBirth("94-04-25");
		assertFalse(validation.isValidDOB());
		
		user.setDateOfBirth("25-04-1994");
		assertFalse(validation.isValidDOB());	

		user.setDateOfBirth("25/4/94");
		assertFalse(validation.isValidDOB());	
	}
	
	@Test
	void TestIsValidCreditCard()
	{
		user.setCreditCardNumber("1234567899874564");
		assertTrue("When CreditCard is valid", validation.isValidCreditCard());
		
		user.setCreditCardNumber("1234");
		assertFalse(validation.isValidCreditCard());
		
		user.setCreditCardNumber("ve3456789987456");
		assertFalse(validation.isValidCreditCard());	

		user.setCreditCardNumber("venkat");
		assertFalse(validation.isValidCreditCard());	
	}
	
	@Test
	void TestisUserAgeUnder18()
	{
		user.setDateOfBirth("2019-04-25");
		assertTrue("When age is valid", validation.isUserAgeUnder18());
		
		user.setDateOfBirth("1976-04-25");
		assertFalse(validation.isUserAgeUnder18());
	}
	
	@Test
	void TestisUserNameAlreadyExist()
	{
		List<User> users = new ArrayList<User>();
		
		User user1 = new User();
		user1.setUserName("Thor");
		user1.setPassword("Hammer");
		user1.setEmail("thor@marvel.com");
		user1.setDateOfBirth("1983-09-11");
		user1.setCreditCardNumber("");		
		users.add(user1);
		
		user.setUserName("Thor");
		assertTrue("When user", validation.isUserNameAlreadyExist(users));
		
		user.setUserName("venkat");
		assertFalse(validation.isUserNameAlreadyExist(users));
	}	
}

