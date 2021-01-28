package org.sid;

import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CatalogueServiceApplication {

	 @Autowired
	    private RepositoryRestConfiguration restConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(CatalogueServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository)
	{
		return args -> {
			
	        restConfiguration.exposeIdsFor(Product.class);//exposer le Id du produit a chaque on affiche les donnée format json

			
		};
	}

}
