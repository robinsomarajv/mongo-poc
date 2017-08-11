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
import com.ust.item.mdm.model.Product;
import com.ust.supplychain.dao.apis.ProductDAO;

public class ProductMongoDAO extends MongoDao implements ProductDAO {

	@Inject
	protected Jongo jongo;
	
	@Override
	public Product getProductById(String id) {
		Product product = null;
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
		return product;
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Product> getProductByAnyAttr(String attr, String attrVal, String attrType) {
		Iterable<Product> iterables = jongo.getCollection(collectionName)
				.find(createQuery(attr, attrType), convertToJongoType(attrVal, attrType)).as(Product.class);
		return Lists.newArrayList(iterables);
	}

}
