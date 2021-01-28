package org.sid.feign;

import org.sid.dtos.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//qd j'appelle la methode findCustomerById automatiquement
//@FeignClient encoie une requete de type GET au microservice customer-service avec le path
///customers/{id}
@FeignClient(name = "customer-service1")//on écrit le nom mentionné (majuscule ou miniscule) dans la page web eureka service dans la partie de liste des microservices connectées avec eureka service
public interface CustomerFeign {
	@GetMapping("/customers/{id}")
	public Customer find_the_Customer_By_its_Id(@PathVariable(name = "id") String id);// on indique @PathVariable car on {id}
}
