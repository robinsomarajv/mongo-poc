package com.ust.item.mdm.item.repo;

import org.springframework.stereotype.Repository;

import com.ust.item.mdm.model.GlobalTradeItem;

@Repository
public interface GlobalTradeItemRepository
		extends org.springframework.data.repository.CrudRepository<GlobalTradeItem, String>, GlobalTradeItemRepositoryCustom {
	
}
