package org.sid;

import org.sid.dao.CustomerRepository;
import org.sid.dtos.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerService1Application {

	public static void main(String[] args) {
		SpringApplication.run(CustomerService1Application.class, args);
	}

	@Bean // pour specifier comme quoi que cette fonction doit etre executée au démarage
	CommandLineRunner start(CustomerRepository cr, RepositoryRestConfiguration rrc) {
		return args -> {
			
			cr.deleteAll();
			
			rrc.exposeIdsFor(Customer.class);// pour afficher le id du client dans les resultats web
			cr.save(new Customer(null, "cust1", "cust1@gmail.com"));
			cr.save(new Customer(null, "cust2", "cust2@gmail.com"));
			cr.save(new Customer(null, "cust3", "cust3@gmail.com"));

			cr.findAll().forEach(System.out::println);	
			
		};
	}

}
