package org.sid.controler;

import org.sid.dtos.Product;
import org.sid.service.ProductJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestService {
	
	 @Autowired 
	 private ProductJointService pr1,pr2;
	 
	 @GetMapping("/products/{id}")
	 public Product findproductByItsID(@PathVariable(name = "id") String id)
	 {
	   return pr1.find_it_by_id(id);
	 } 
	 
	 @GetMapping("/products")
	 public PagedResources<Product> GetAllProducts()
	 {
	   return pr2.GetAllProducts();
	 } 
}
