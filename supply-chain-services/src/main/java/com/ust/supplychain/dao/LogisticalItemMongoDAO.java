package com.ust.supplychain.dao;

import java.util.List;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.ust.item.mdm.model.LogisticalItem;
import com.ust.supplychain.dao.apis.LogisticalItemDAO;

public class LogisticalItemMongoDAO extends MongoDao implements LogisticalItemDAO {

	@Inject
	protected Jongo jongo;
	
	@Override
	public LogisticalItem getLogisticalById(String id) {
		return jongo.getCollection(collectionName).findOne("{_id: #}", id).as(LogisticalItem.class);
	}

	@Override
	public LogisticalItem saveLogisticalItem(LogisticalItem li) {
		jongo.getCollection(collectionName).save(li);
		return li;
	}

	@Override
	public List<LogisticalItem> getLogisticalByAnyAttr(String attr, String attrVal, String attrType) {
		Iterable<LogisticalItem> iterables = jongo.getCollection(collectionName)
				.find(createQuery(attr, attrType), convertToJongoType(attrVal, attrType)).as(LogisticalItem.class);
		return Lists.newArrayList(iterables);
	}

}
