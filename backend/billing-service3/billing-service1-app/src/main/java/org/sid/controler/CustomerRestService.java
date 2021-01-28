package org.sid.controler;

import org.sid.dtos.Customer;
import org.sid.service.CustomerJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestService {

	 @Autowired 
	 private CustomerJointService cr;
	 
	 @GetMapping("/customers/{id}")
	 public Customer findcustomerByItsID(@PathVariable(name = "id") String id)
	 {
	   return cr.find_it_by_id(id); 
	 } 
	
}
