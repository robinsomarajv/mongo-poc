package com.ust.supplychain.dao.apis;

import java.util.List;

import com.ust.item.mdm.model.GlobalTradeItem;


public interface TradeItemDAO {
	
	GlobalTradeItem getTradeItemById(String id);
	
	List<GlobalTradeItem> getTradeItemByAnyAttr(String attr, String attrVal, String attrType);
	
	void saveTradeItem(GlobalTradeItem ti);

}
