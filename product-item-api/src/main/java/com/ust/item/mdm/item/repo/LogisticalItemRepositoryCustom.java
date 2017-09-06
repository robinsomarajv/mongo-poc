package com.ust.item.mdm.item.repo;

import java.util.Collection;

import com.ust.item.mdm.model.LogisticalItem;

public interface LogisticalItemRepositoryCustom {

	Collection<LogisticalItem> searchLogisticalItemByAnyAttribute(String attr, Object value, String attrType);

	LogisticalItem getLogisticalItemById(String id);
}
