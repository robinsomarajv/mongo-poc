package com.ust.item.mdm.logistical_item.repo;

import org.springframework.stereotype.Repository;

import com.ust.item.mdm.model.Product;

@Repository
public interface ProductRepository
		extends org.springframework.data.repository.CrudRepository<Product, String>, ProductRepositoryCustom {
	
}
