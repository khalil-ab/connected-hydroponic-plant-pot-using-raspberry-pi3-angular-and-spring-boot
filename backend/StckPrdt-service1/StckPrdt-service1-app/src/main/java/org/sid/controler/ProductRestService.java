package org.sid.controler;

import java.util.Optional;

import org.sid.dao.ProductRepository;
import org.sid.dtos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestService {

	@Autowired
	private ProductRepository pr;
	
	@GetMapping("/products/{id}")
	public Optional<Product> find_the_Product_By_its_Id(@PathVariable(name = "id") String id)
	{
		return pr.findById(id);
	}
	
}
