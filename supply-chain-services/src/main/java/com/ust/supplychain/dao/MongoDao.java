package com.ust.supplychain.dao;

import java.util.Arrays;
import java.util.Date;

import org.jongo.Jongo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDao {

	@Inject
	@Named("mongo.connection.uri")
	private String connectionUri;
	
	@Inject
	@Named("mongo.connection.password")
	private String password;
	
	@Inject
	@Named("mongo.database.name")
	private String databaseName;

	@Inject
	@Named("mongo.collection.name")
	protected String collectionName;

	private static final String MONGO_SIMPLE_QUERY = "{%s: #}";

	private static final String MONGO_SEARCH_COLL_QUERY = "{%s: {$in:#}}";

	MongoClient client;

	public MongoDao() {
		super();
	}

	public MongoClient getClient() {
		MongoClientURI uri = new MongoClientURI(connectionUri);
		this.client = new MongoClient(uri);
		return this.client;
	}

	@SuppressWarnings("deprecation")
	public Jongo getJongo() {
		// return new Jongo(this.client.getDB("catalog"),
		// new JacksonMapper.Builder()
		// .registerModule(new JodaModule())
		// .enable(MapperFeature.AUTO_DETECT_GETTERS)
		//// .withView(Views.Public.class)
		// .build());

		return new Jongo(getClient().getDB(databaseName));
	}

	protected Object convertToJongoType(String attrVal, String attrType) {
		if (attrType == null) {
			return attrVal;
		}
		switch (attrType) {
		case "DATE":
			return new Date(Long.valueOf(attrVal));
		case "COLLECTION":
			return Arrays.asList(attrVal.split("\\s*,\\s*"));
		default:
			return attrVal;
		}
	}

	protected String createQuery(String attr, String attrType) {
		if (attrType == null) {
			return String.format(MONGO_SIMPLE_QUERY, attr);
		}
		switch (attrType) {
		case "COLLECTION":
			return String.format(MONGO_SEARCH_COLL_QUERY, attr);
		default:
			return String.format(MONGO_SIMPLE_QUERY, attr);
		}
	}

}