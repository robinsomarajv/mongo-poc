package com.ust.supplychain.dao.apis;

import java.util.List;

import com.ust.item.mdm.model.LogisticalItem;


public interface LogisticalItemDAO {

	LogisticalItem getLogisticalById(String id);

	List<LogisticalItem> getLogisticalByAnyAttr(String attr, String attrVal, String attrType);

	LogisticalItem saveLogisticalItem(LogisticalItem li);

}
