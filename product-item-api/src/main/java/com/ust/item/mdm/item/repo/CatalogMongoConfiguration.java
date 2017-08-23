package com.ust.item.mdm.logistical_item.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class CatalogMongoConfiguration {

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(
				new MongoClient(new MongoClientURI(
						"mongodb://cbdesai:Aliso2017!@cluster0-shard-00-00-0sdaq.mongodb.net:27017,cluster0-shard-00-01-0sdaq.mongodb.net:27017,cluster0-shard-00-02-0sdaq.mongodb.net:27017/catalog?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin")),
				"catalog");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		return mongoTemplate;

	}

}