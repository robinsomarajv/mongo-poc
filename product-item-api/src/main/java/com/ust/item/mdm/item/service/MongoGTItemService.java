package com.ust.item.mdm.item.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.item.repo.GlobalTradeItemRepository;
import com.ust.item.mdm.item.repo.MetadataRepository;
import com.ust.item.mdm.item.repo.ProductRepository;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.Product;

@Service
public class MongoGTItemService implements GTItemService{
	
	@Autowired
	private GlobalTradeItemRepository globalTradeItemRepository;
	@Autowired
	private MetadataRepository metaRepository;

	
	@Override
	public Collection<GlobalTradeItem> searchGtItemsByAnyAttribute(String attr, String attrVal) {
		Metadata metadata = metaRepository.getMetadataById(attr);
		return globalTradeItemRepository.searchGlobalTradeItemByAnyAttribute(attr, attrVal, metadata.getAttributeDataType());
		
	}

	@Override
	public Collection<Attribute> getGtItembyId(String gtin)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public Collection<Attribute> getProductById(String pid) throws SecurityException, IllegalArgumentException, IllegalAccessException {
//		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("PRODUCT");
//		Product product = productRepository.getProductByPid(pid);
//		return mergeWithProductMetadata(product, metadata);
//		
//	}
	
}
