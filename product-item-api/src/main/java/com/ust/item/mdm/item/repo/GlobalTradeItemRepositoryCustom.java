package com.ust.item.mdm.item.repo;

import java.util.Collection;

import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.GlobalTradeItemId;
import com.ust.item.mdm.model.Product;

public interface GlobalTradeItemRepositoryCustom {

	public Collection<GlobalTradeItem> searchGlobalTradeItemByAnyAttribute(String attr, Object value, String attrType);

	GlobalTradeItem getGlobalTradeItemById(GlobalTradeItemId tId);

}
