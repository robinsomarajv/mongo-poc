package com.ust.item.mdm.logistical_item.repo;

import java.sql.Date;
import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.Product;

@Repository
public class GlobalTradeItemRepositoryImpl implements GlobalTradeItemRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Collection<GlobalTradeItem> searchGlobalTradeItemByAnyAttribute(String attr, Object value, String attrType) {
		Query query = new Query();
		switch (attrType) {
		case "Date":
			value = new Date(Long.valueOf(value.toString()));
			query.addCriteria(Criteria.where(attr).gte(value));
			break;
		case "Alphanumeric":
			query.addCriteria(Criteria.where("_id." + attr).is(value));
			Collection<GlobalTradeItem> gtItems = mongoTemplate.find(query, GlobalTradeItem.class, "tradeitems");
			if (null == gtItems || gtItems.isEmpty()) {
				query = new Query();
				query.addCriteria(Criteria.where(attr)
						.regex(Pattern.compile(".*" + value.toString() + ".*", Pattern.CASE_INSENSITIVE)));
				gtItems = mongoTemplate.find(query, GlobalTradeItem.class, "tradeitems");
			}
			return gtItems;
		default:
			query.addCriteria(Criteria.where(attr).is(value));
		}
		return mongoTemplate.find(query, GlobalTradeItem.class, "tradeitems");

	}

	@Override
	public GlobalTradeItem getGlobalTradeItemByPid(String pid) {

		Query query = new Query();
		query.addCriteria(Criteria.where("product_id").is(pid));
		return mongoTemplate.findOne(query, GlobalTradeItem.class, "tradeitems");

	}

}
