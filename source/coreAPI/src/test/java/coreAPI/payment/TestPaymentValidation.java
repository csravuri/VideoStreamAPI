package coreAPI.payment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import coreAPI.user.User;

class TestPaymentValidation 
{
	Payment payment;
	PaymentValidation validation;
	
	@BeforeEach
	void beforeTest()
	{
		payment = new Payment();
		validation = new PaymentValidation(payment);
	}

	@Test
	void testIsValidCreditCard()
	{
		payment.setCreditCardNumber("1234567899874564");
		assertTrue("When CreditCard is valid", validation.isCreditCardNumberValid());
		
		payment.setCreditCardNumber("1234");
		assertFalse(validation.isCreditCardNumberValid());
		
		payment.setCreditCardNumber("ve3456789987456");
		assertFalse(validation.isCreditCardNumberValid());	

		payment.setCreditCardNumber("venkat");
		assertFalse(validation.isCreditCardNumberValid());	
	}

	@Test
	void testIsAmountValid()
	{
		payment.setAmount("123");
		assertTrue("When amount is valid", validation.isAmountValid());
		
		payment.setAmount("13");
		assertFalse(validation.isAmountValid());

		payment.setAmount("aa");
		assertFalse(validation.isAmountValid());
		
		payment.setAmount("abc");
		assertFalse(validation.isAmountValid());

		payment.setAmount("-139");
		assertFalse(validation.isAmountValid());
	}
	
	@Test
	void testIsCreditCardNotRegistredInUsers()
	{
		List<User> users = new ArrayList<User>();
		
		User user1 = new User();
		user1.setUserName("Thor");
		user1.setPassword("Hammer");
		user1.setEmail("thor@marvel.com");
		user1.setDateOfBirth("1983-09-11");
		user1.setCreditCardNumber("9832345678654311");		
		users.add(user1);
		
		payment.setCreditCardNumber("9832345678654311");
		assertFalse("When credit card registered", validation.isCreditCardNotRegistredInUsers(users));
		
		payment.setCreditCardNumber("2938474484875848");
		assertTrue("When credit card not registered", validation.isCreditCardNotRegistredInUsers(users));
	}
}
