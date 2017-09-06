package com.ust.item.mdm.item.repo;

import java.sql.Date;
import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ust.item.mdm.model.LogisticalItem;

@Repository
public class LogisticalItemRepositoryImpl implements LogisticalItemRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Collection<LogisticalItem> searchLogisticalItemByAnyAttribute(String attr, Object value, String attrType) {
		Query query = new Query();
		switch (attrType) {
		case "Date":
			value = new Date(Long.valueOf(value.toString()));
			query.addCriteria(Criteria.where(attr).gte(value));
			break;
		case "Alphanumeric":
//			value = new Date(Long.valueOf(value.toString()));
			query.addCriteria(Criteria.where(attr).regex(Pattern.compile(".*" + value.toString() +".*", Pattern.CASE_INSENSITIVE)));
			break;
		default:
			query.addCriteria(Criteria.where(attr).is(value));
		}
		return mongoTemplate.find(query, LogisticalItem.class, "logisticalitems");

	}
	
	@Override
	public LogisticalItem getLogisticalItemById(String id){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, LogisticalItem.class, "logisticalitems");
		
	}

}
