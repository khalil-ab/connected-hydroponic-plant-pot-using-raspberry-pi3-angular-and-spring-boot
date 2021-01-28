package org.sid.service;


import org.sid.dtos.Customer;
import org.sid.feign.CustomerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerJointService {

	@Autowired
	CustomerFeign cf;
	
	public Customer find_it_by_id(String id) {  
		Customer c = cf.find_the_Customer_By_its_Id(id);
		return c; 
	}
	
}
