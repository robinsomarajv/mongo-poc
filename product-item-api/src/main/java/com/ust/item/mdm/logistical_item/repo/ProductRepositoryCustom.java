package com.ust.item.mdm.logistical_item.repo;

import java.util.Collection;

import com.ust.item.mdm.model.Product;

public interface ProductRepositoryCustom {

	public Collection<Product> searchProductByAnyAttribute(String attr, Object value, String attrType);

	Product getProductByPid(String pid);

}
