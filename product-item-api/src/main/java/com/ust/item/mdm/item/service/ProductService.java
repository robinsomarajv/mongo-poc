package com.ust.item.mdm.item.service;

import java.util.Collection;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.ItemSummary;
import com.ust.item.mdm.model.Product;

public interface ProductService {

	Collection<Product> searchProductByAnyAttribute(String attr, String attrVal);

	Collection<Attribute> getProductById(String pid) throws SecurityException, IllegalArgumentException, IllegalAccessException;

	Collection<ItemSummary> searchProductByAnyAttributeForSummary(String attr, String attrVal);
	
	
}
