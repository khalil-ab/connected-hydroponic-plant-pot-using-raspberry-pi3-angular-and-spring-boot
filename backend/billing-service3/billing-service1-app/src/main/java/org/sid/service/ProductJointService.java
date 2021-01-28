package org.sid.service;

import org.sid.dtos.Product;
import org.sid.feign.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

@Service
public class ProductJointService {

	@Autowired
	ProductFeign pf1;
	
	public Product find_it_by_id(String id) {  
		Product p = pf1.find_the_Product_By_its_Id(id);
		return p; 
	}
	
	@Autowired
	ProductFeign pf2;
	 public PagedResources<Product> GetAllProducts()
	 {
		 PagedResources<Product> p = pf2.GetAllProducts();
		 return p;
	 }
	
}
