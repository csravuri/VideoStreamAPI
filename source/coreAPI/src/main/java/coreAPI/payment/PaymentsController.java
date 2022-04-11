package coreAPI.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coreAPI.user.UserDAO;


@RestController
public class PaymentsController 
{
	@Autowired
	private UserDAO userDAO;
	
	@PostMapping("/payments")
	public ResponseEntity<Object> addPayment(@RequestBody Payment payment)
	{
		PaymentValidation validation = new PaymentValidation(payment);
		
		if (!validation.isPaymentBasicDetailsValid())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		if (validation.isCreditCardNotRegistredInUsers(userDAO.getUsers()))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
