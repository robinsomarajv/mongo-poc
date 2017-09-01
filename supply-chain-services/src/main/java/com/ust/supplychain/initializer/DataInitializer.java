package com.ust.supplychain.initializer;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jongo.Jongo;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.LogisticalItem;
import com.ust.item.mdm.model.Product;
import com.ust.supplychain.services.servlet.modules.BootstrapPropertiesModule;

public class DataInitializer {

	@Inject
	@Named("mongo.collection.name")
	private String collectionName;

	@Inject
	Jongo jongo;

	@Inject
	private ObjectMapper mapper;

	public static void main(String[] args) throws JsonParseException, JsonMappingException, JSONException, IOException {
		Injector injector = Guice.createInjector(new BootstrapPropertiesModule());
		DataInitializer intializer = injector.getInstance(DataInitializer.class);
		
//		intializer.jongo.getCollection("metadata").drop();
//		intializer.loadProdMetadata();
//		intializer.loadTIMetadata();
//		intializer.loadLogisticalItems();
//		intializer.loadTradeItems();
//		intializer.loadProducts();
		intializer.loadLogisticalItemsMetadata();
	}

	private void loadLogisticalItemsMetadata() throws JSONException, IOException {
		JSONObject prods = new JSONObject(Resources.toString(Resources.getResource("logistical-items-metadata.json"), Charsets.UTF_8));
		JSONObject optAttrs = prods.getJSONObject("attributes").getJSONObject("Optional");
		JSONObject reqAttrs = prods.getJSONObject("attributes").getJSONObject("Required");
		Iterator<String> optKeys = optAttrs.keys();
		Iterator<String> reqKeys = reqAttrs.keys();
		while(optKeys.hasNext()){
			JSONObject optMeta = optAttrs.getJSONObject(optKeys.next()).getJSONObject("metadata");
			jongo.getCollection("metadata").save(mapper.readValue(optMeta.toString(), Metadata.class));
		}
		while(reqKeys.hasNext()){
			JSONObject reqMeta = reqAttrs.getJSONObject(reqKeys.next()).getJSONObject("metadata");
			jongo.getCollection("metadata").save(mapper.readValue(reqMeta.toString(), Metadata.class));
		}
		
	}

	private void loadTIMetadata() throws JSONException, IOException {
		
		JSONObject prods = new JSONObject(Resources.toString(Resources.getResource("metadata-trade-item.json"), Charsets.UTF_8));
		JSONObject optAttrs = prods.getJSONObject("attributes").getJSONObject("Optional");
		JSONObject reqAttrs = prods.getJSONObject("attributes").getJSONObject("Required");
		Iterator<String> optKeys = optAttrs.keys();
		Iterator<String> reqKeys = reqAttrs.keys();
		while(optKeys.hasNext()){
			JSONObject optMeta = optAttrs.getJSONObject(optKeys.next()).getJSONObject("metadata");
			jongo.getCollection("metadata").save(mapper.readValue(optMeta.toString(), Metadata.class));
		}
		while(reqKeys.hasNext()){
			JSONObject reqMeta = reqAttrs.getJSONObject(reqKeys.next()).getJSONObject("metadata");
			jongo.getCollection("metadata").save(mapper.readValue(reqMeta.toString(), Metadata.class));
		}
	}

	private void loadProdMetadata() throws JSONException, IOException {
		
		JSONObject prods = new JSONObject(Resources.toString(Resources.getResource("metadata-product.json"), Charsets.UTF_8));
		JSONObject optAttrs = prods.getJSONObject("attributes").getJSONObject("Optional");
		JSONObject reqAttrs = prods.getJSONObject("attributes").getJSONObject("Required");
		Iterator<String> optKeys = optAttrs.keys();
		Iterator<String> reqKeys = reqAttrs.keys();
		while(optKeys.hasNext()){
			JSONObject optMeta = optAttrs.getJSONObject(optKeys.next()).getJSONObject("metadata");
			jongo.getCollection("metadata").save(mapper.readValue(optMeta.toString(), Metadata.class));
		}
		while(reqKeys.hasNext()){
			JSONObject reqMeta = reqAttrs.getJSONObject(reqKeys.next()).getJSONObject("metadata");
			jongo.getCollection("metadata").save(mapper.readValue(reqMeta.toString(), Metadata.class));
		}
	}

	private void loadProducts() throws JSONException, JsonParseException, JsonMappingException, IOException {
		JSONArray prods = new JSONArray(Resources.toString(Resources.getResource("products.json"), Charsets.UTF_8));
		jongo.getCollection("products").drop();
		for (int i = 0; i < prods.length(); i++) {

			Product item = mapper.readValue(prods.getJSONObject(i).toString(), Product.class);
			jongo.getCollection("products").save(item);
		}
	}

	private void loadTradeItems() throws JsonParseException, JsonMappingException, IOException, JSONException {
		JSONArray tItems = new JSONArray(Resources.toString(Resources.getResource("trade-items.json"), Charsets.UTF_8));
		jongo.getCollection("tradeitems").drop();
		for (int i = 0; i < tItems.length(); i++) {

			GlobalTradeItem item = mapper.readValue(tItems.getJSONObject(i).toString(), GlobalTradeItem.class);
			jongo.getCollection("tradeitems").save(item);
		}
	}

	private void loadLogisticalItems() throws JSONException, JsonParseException, JsonMappingException, IOException {
		JSONArray lItems = new JSONArray(Resources.toString(Resources.getResource("logistical-items.json"), Charsets.UTF_8));
		jongo.getCollection(collectionName).drop();
		jongo.getCollection(collectionName).drop();
		for (int i = 0; i < lItems.length(); i++) {
			LogisticalItem item = mapper.readValue(lItems.getJSONObject(i).toString(), LogisticalItem.class);
			jongo.getCollection(collectionName).save(item);
		}
//		To QUERY
//		LogisticalItem item1 = jongo.getCollection(collectionName).findOne("{consumableGtinId: '0000000000017_GLN_000000009_US_WM'}").as(LogisticalItem.class);
		System.out.println("Queried");
	}

}
