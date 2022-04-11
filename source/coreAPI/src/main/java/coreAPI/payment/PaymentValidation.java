package coreAPI.payment;

import java.util.List;

import coreAPI.user.User;

public class PaymentValidation 
{
	private Payment payment;
	public PaymentValidation(Payment payment)
	{
		this.payment = payment;
	}
	
	public boolean isPaymentBasicDetailsValid()
	{
		return isCreditCardNumberValid() && isAmountValid();
	}
	
	public boolean isCreditCardNotRegistredInUsers(List<User> users)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (payment.getCreditCardNumber().equalsIgnoreCase(users.get(i).getCreditCardNumber()))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isCreditCardNumberValid()
	{
		return payment.getCreditCardNumber().matches("^[0-9]{16}$");
	}
	
	public boolean isAmountValid()
	{
		try
		{
			int amount = Integer.parseInt(payment.getAmount());
			if (payment.getAmount().length() == 3 && amount > 99 && amount < 1000)
			{
				return true;
			}
		}
		catch(Exception e)
		{
		}
		return false;
	}
}
