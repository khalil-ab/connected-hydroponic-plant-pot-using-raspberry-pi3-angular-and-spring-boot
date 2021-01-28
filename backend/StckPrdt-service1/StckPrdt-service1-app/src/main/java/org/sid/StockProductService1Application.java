package org.sid;

import org.sid.dao.ProductRepository;
import org.sid.dtos.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class StockProductService1Application {

	public static void main(String[] args) {
		SpringApplication.run(StockProductService1Application.class, args);
	}

	@Bean //pour specifier comme quoi que cette fonction doit etre executée au démarage
	CommandLineRunner start(ProductRepository  cr, RepositoryRestConfiguration rrc )
	{
		return args->{
			
			cr.deleteAll();
			
			rrc.exposeIdsFor(Product.class);//pour afficher le id du produit dans les resultats web
			cr.save(new Product(null,"product1",123));
			cr.save(new Product(null,"product2",456));
			cr.save(new Product(null,"product3",789));

			cr.findAll().forEach(System.out::println);
		}; 
	}
	
}
