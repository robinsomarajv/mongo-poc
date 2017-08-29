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
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.GlobalTradeItemId;

@Service
public class MongoGTItemService implements GTItemService {

	@Autowired
	private GlobalTradeItemRepository globalTradeItemRepository;
	@Autowired
	private MetadataRepository metaRepository;

	@Override
	public Collection<GlobalTradeItem> searchGtItemsByAnyAttribute(String attr, String attrVal) {
		Metadata metadata = metaRepository.getMetadataById(attr);
		return globalTradeItemRepository.searchGlobalTradeItemByAnyAttribute(attr, attrVal,
				metadata.getAttributeDataType());

	}

	@Override
	public Collection<Attribute> getGtItembyId(GlobalTradeItemId tid)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("TRADE_ITEM");
		GlobalTradeItem tradeItem = globalTradeItemRepository.getGlobalTradeItemById(tid);
		return mergeWithTIMetadata(tradeItem, metadata);
	}

	private Collection<Attribute> mergeWithTIMetadata(GlobalTradeItem tradeItem, Collection<Metadata> metadata)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {

		Collection<Attribute> attributes = new ArrayList<Attribute>(0);

		for (Metadata meta : metadata) {
			if (!StringUtils.isEmpty(meta.getId())) {
				Field field = null;
				try {
					field = GlobalTradeItem.class.getDeclaredField(meta.getId());
					attributes.add(createAttributeBucket(tradeItem, meta, field));
				} catch (NoSuchFieldException e) {
					try {
						field = GlobalTradeItemId.class.getDeclaredField(meta.getId());
						attributes.add(createAttributeBucket(tradeItem.get_id(), meta, field));
					} catch (NoSuchFieldException e1) {
					}
				}

			}
		}
		return attributes;
	}

	private Attribute createAttributeBucket(Object obj, Metadata meta, Field field) throws IllegalAccessException {
		field.setAccessible(true);
		Attribute attribute = new Attribute();
		attribute.setMetadata(meta);
		attribute.setId(meta.getId());

		attribute.setValues(
				field.get(obj) instanceof List ? (List<Object>) field.get(obj) : Arrays.asList(field.get(obj)));
		return attribute;
	}

}
