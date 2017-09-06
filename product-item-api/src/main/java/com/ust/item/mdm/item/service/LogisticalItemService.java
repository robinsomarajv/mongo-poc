package com.ust.item.mdm.item.service;

import java.util.Collection;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.ItemSummary;
import com.ust.item.mdm.model.LogisticalItem;

public interface LogisticalItemService {

	Collection<LogisticalItem> searchLogisticalItemByAnyAttribute(String attr, String attrVal);

	Collection<Attribute> getLogisticalItemById(String id) throws SecurityException, IllegalArgumentException, IllegalAccessException;

	Collection<ItemSummary<LogisticalItem>> searchLogisticalItemByAnyAttributeForSummary(String attr, String attrVal);

	
	
}
