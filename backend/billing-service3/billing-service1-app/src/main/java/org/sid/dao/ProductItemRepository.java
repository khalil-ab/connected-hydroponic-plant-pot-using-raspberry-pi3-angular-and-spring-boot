package org.sid.dao;

import java.util.List;

import org.sid.dtos.ProductItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends MongoRepository<ProductItem, String> 
{
	List<ProductItem> findByBillId(String billID);
}