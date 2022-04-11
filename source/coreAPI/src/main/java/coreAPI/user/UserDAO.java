package coreAPI.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	private static List<User> users = new ArrayList<User>();
	static
	{
		User user1 = new User();
		user1.setUserName("TonyStark");
		user1.setPassword("Ironman3000");
		user1.setEmail("ironman@marvel.com");
		user1.setDateOfBirth("1970-05-29");
		user1.setCreditCardNumber("9093123459843283");
		
		User user2 = new User();
		user2.setUserName("Thor");
		user2.setPassword("Hammer4Best");
		user2.setEmail("thor@marvel.com");
		user2.setDateOfBirth("1983-09-11");
		user2.setCreditCardNumber("");
		
		User user3 = new User();
		user3.setUserName("BruceBanner");
		user3.setPassword("Hulk4All");
		user3.setEmail("hulk@marvel.com");
		user3.setDateOfBirth("1969-12-18");
		user3.setCreditCardNumber("7384374890123485");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}
	
	public List<User> getUsers()
	{
		return users;
	}
	
	public List<User> getUsers(boolean hasCreditCardNumber)
	{
		List<User> filteredUsers = new ArrayList<User>(users);
		
		filteredUsers.removeIf(u -> (hasCreditCardNumber && u.getCreditCardNumber().isBlank()) || (!hasCreditCardNumber && !u.getCreditCardNumber().isBlank()));
		
		return filteredUsers;
	}
	
	public void addUser(User user)
	{
		users.add(user);
	}
}
