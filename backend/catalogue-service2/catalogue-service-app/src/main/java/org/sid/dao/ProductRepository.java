package org.sid.dao;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product,String>{
	
Optional<Product> findByName(String ImgName);
	
	@Query("{'category' : ?0 }")
	public List<Product> findAllByCategory(String category);
}
