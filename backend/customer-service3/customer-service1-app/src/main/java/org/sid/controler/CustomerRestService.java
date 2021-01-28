package org.sid.controler;

import java.util.Optional;

import org.sid.dao.CustomerRepository;
import org.sid.dtos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestService {

	@Autowired
	private CustomerRepository cr;
	
	@GetMapping("/customers/{id}")
	public Optional<Customer> find_the_Customer_By_its_Id(@PathVariable(name = "id") String id)
	{
		return cr.findById(id);
	}
	
}
