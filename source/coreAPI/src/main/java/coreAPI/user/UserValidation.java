package coreAPI.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserValidation 
{
	private User user;
	
	public UserValidation(User user)
	{
		this.user = user;
	}
	
	public boolean isBasicUserDetailsValid()
	{
		return isValidUserName() && isValidPassword() && isValidEmail() && isValidDOB() && isValidCreditCard();
	}
	
	public boolean isUserAgeUnder18()
	{
		try 
		{
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(user.getDateOfBirth());
			Calendar dob18 = Calendar.getInstance();
			dob18.setTime(dob);
			dob18.add(Calendar.YEAR, 18);
			Date currentDate = new Date();
			return dob18.getTime().after(currentDate);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isUserNameAlreadyExist(List<User> allUsers)
	{
		for (int i = 0; i < allUsers.size(); i++)
		{
			if (user.getUserName().equalsIgnoreCase(allUsers.get(i).getUserName()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isValidUserName()
	{
		String pattern = "^[a-zA-Z0-9]*$";
	    return user.getUserName().matches(pattern);
	}
	
	public boolean isValidPassword()
	{
		String pattern= "^(?=.*[A-Za-z])(?=.*\\d)[A-Z\\d]{8,}$";
	    return user.getPassword().matches(pattern);
	}
	
	public boolean isValidEmail()
	{
		String pattern= "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    return user.getEmail().matches(pattern);
	}
	
	public boolean isValidDOB()
	{
		String pattern= "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
	    return user.getDateOfBirth().matches(pattern);
	}
	
	public boolean isValidCreditCard()
	{
		return user.getCreditCardNumber().isEmpty() || user.getCreditCardNumber().matches("^[0-9]{16}$");
	}
}
