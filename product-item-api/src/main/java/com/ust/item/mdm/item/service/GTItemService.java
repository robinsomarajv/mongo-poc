package com.ust.item.mdm.item.service;

import java.util.Collection;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.GlobalTradeItemId;

public interface GTItemService {

	Collection<GlobalTradeItem> searchGtItemsByAnyAttribute(String attr, String attrVal);

	Collection<Attribute> getGtItembyId(GlobalTradeItemId tId) throws SecurityException, IllegalArgumentException, IllegalAccessException;

	
	
}
