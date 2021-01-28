package org.sid;

import java.util.Date;

import org.sid.controler.CustomerRestService;
import org.sid.controler.ProductRestService;
import org.sid.dao.BillRepository;
import org.sid.dao.ProductItemRepository;
import org.sid.dtos.Bill;
import org.sid.dtos.Customer;
import org.sid.dtos.Product;
import org.sid.dtos.ProductItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedResources;

@SpringBootApplication
@EnableFeignClients
public class BillingService1Application {

	public static void main(String[] args) {
		SpringApplication.run(BillingService1Application.class, args);
	}

	// http://localhost:8083/bills/
	// http://localhost:8083/bills/1
	// http://localhost:8083/bills/1/productItems
	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
			CustomerRestService cs,ProductRestService pdt2,RepositoryRestConfiguration rrc) {
		return args -> {

			rrc.exposeIdsFor(Bill.class);
			rrc.exposeIdsFor(ProductItem.class);
			
			productItemRepository.deleteAll();
			billRepository.deleteAll(); 
			
			Customer c1 = cs.findcustomerByItsID("5eb56d8b8d3fe81d7c90889d");

			System.out.println("**************");
			System.out.println("ID=" + c1.getId());
			System.out.println("Name=" + c1.getName());
			System.out.println("Email=" + c1.getEmail());
			System.out.println("**************");

						
			Bill bill1 = billRepository.save(new Bill(null, new Date(), c1.getId(), null));

			PagedResources<Product> products = pdt2.GetAllProducts();
			products.getContent().forEach(p -> {
				productItemRepository.save(new ProductItem(null, p.getId(), p.getPrice(), 30, bill1));
			}

			);

/*			Product p1 = pdt1.findproductByItsID("5e7625128d3fe80f5872ef0a");

			System.out.println("**************");
			System.out.println("ID=" + p1.getId());
			System.out.println("Name=" + p1.getName());
			System.out.println("Price=" + p1.getPrice());
			System.out.println("**************");
			productItemRepository.save(new ProductItem(null, p1.getId(), p1.getPrice(), 30, bill1));

			Product p2 = pdt1.findproductByItsID("5e7625128d3fe80f5872ef0b");
			System.out.println("**************");
			System.out.println("ID=" + p2.getId());
			System.out.println("Name=" + p2.getName());
			System.out.println("Price=" + p2.getPrice());
			System.out.println("**************");
			productItemRepository.save(new ProductItem(null, p2.getId(), p2.getPrice(), 30, bill1));

			Product p3 = pdt1.findproductByItsID("5e7625128d3fe80f5872ef0c");
			System.out.println("**************");
			System.out.println("ID=" + p3.getId());
			System.out.println("Name=" + p3.getName());
			System.out.println("Price=" + p3.getPrice());
			System.out.println("**************");
			productItemRepository.save(new ProductItem(null, p3.getId(), p3.getPrice(), 30, bill1));*/

		};
	}

}
