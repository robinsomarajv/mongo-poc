package com.ust.supplychain.dao.apis;

import java.util.List;

import com.ust.item.mdm.model.Product;


public interface ProductDAO {
	
	Product getProductById(String id);
	
	void saveProduct(Product li);
	
	List<Product> getProductByAnyAttr(String attr, String attrVal, String attrType);

}
