package com.ust.supplychain.dao;

import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.supplychain.dao.apis.TradeItemDAO;

public class TradeItemMongoDAO extends MongoDao implements TradeItemDAO {
	
	@Inject
	protected Jongo jongo;
	
	@Override
	public GlobalTradeItem getTradeItemById(String id) {
		GlobalTradeItem tradeItem = null;
		try (MongoClient client = getClient()) {
			MongoDatabase database = client.getDatabase("catalog");
			MongoCollection<Document> table = database.getCollection("items");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", id);

			FindIterable<Document> cursor = table.find(searchQuery);
			Iterator it = cursor.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
		return tradeItem;
	}
	
	
	@Override
	public List<GlobalTradeItem> getTradeItemByAnyAttr(String attr, String attrVal, String attrType) {
		Iterable<GlobalTradeItem> iterables = jongo.getCollection(collectionName)
				.find(createQuery(attr, attrType), convertToJongoType(attrVal, attrType)).as(GlobalTradeItem.class);
		return Lists.newArrayList(iterables);
	}

	@Override
	public void saveTradeItem(GlobalTradeItem li) {
		// TODO Auto-generated method stub

	}

}
