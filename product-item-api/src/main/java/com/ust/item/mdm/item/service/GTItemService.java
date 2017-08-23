package com.ust.item.mdm.item.service;

import java.util.Collection;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.Product;

public interface GTItemService {

	Collection<GlobalTradeItem> searchGtItemsByAnyAttribute(String attr, String attrVal);

	Collection<Attribute> getGtItembyId(String gtin) throws SecurityException, IllegalArgumentException, IllegalAccessException;

	
	
}
