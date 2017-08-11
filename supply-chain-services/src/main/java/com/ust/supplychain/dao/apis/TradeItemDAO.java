package com.ust.supplychain.dao.apis;

import java.util.List;

import com.ust.item.mdm.model.TradeItem;


public interface TradeItemDAO {
	
	TradeItem getTradeItemById(String id);
	
	List<TradeItem> getTradeItemByAnyAttr(String attr, String attrVal, String attrType);
	
	void saveTradeItem(TradeItem ti);

}
