package coreAPI.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/users")	  
    public List<User> getUsers(@RequestParam(value = "CreditCard", defaultValue = "") String CreditCard)
    {  
		if (CreditCard.isBlank())
		{
			return userDAO.getUsers();
		}
		
		if (CreditCard.equalsIgnoreCase("yes"))
		{
			return userDAO.getUsers(true);
		}
		
		if (CreditCard.equalsIgnoreCase("no"))
		{
			return userDAO.getUsers(false);
		}
		
		return new ArrayList<User>();
    }
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user)
	{
		UserValidation userValidation = new UserValidation(user);
		if (!userValidation.isBasicUserDetailsValid())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		if (userValidation.isUserAgeUnder18())
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		if (userValidation.isUserNameAlreadyExist(userDAO.getUsers()))
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		userDAO.addUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
