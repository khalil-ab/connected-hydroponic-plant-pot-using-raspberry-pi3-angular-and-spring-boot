package org.sid.feign;

import org.sid.dtos.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//qd j'appelle la methode findCustomerById automatiquement
//@FeignClient encoie une requete de type GET au microservice customer-service avec le path
///customers/{id}
@FeignClient(name ="StckPrdt-service1")//on écrit le nom mentionné (majuscule ou miniscule) dans la page web eureka service dans la partie de liste des microservices connectées avec eureka service                  	
public interface ProductFeign {
	@GetMapping("/products/{id}")
	public Product find_the_Product_By_its_Id(@PathVariable(name = "id") String id);// on indique @PathVariable car on {id}

	 @GetMapping("/products")
	 public PagedResources<Product> GetAllProducts(); 

}
