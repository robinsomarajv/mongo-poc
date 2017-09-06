package com.ust.item.mdm.item.repo;

import org.springframework.stereotype.Repository;

import com.ust.item.mdm.model.LogisticalItem;

@Repository
public interface LogisticalItemRepository
		extends org.springframework.data.repository.CrudRepository<LogisticalItem, String>, LogisticalItemRepositoryCustom {
	
}
