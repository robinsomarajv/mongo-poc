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
import com.ust.item.mdm.dto.ItemSummary;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.item.repo.LogisticalItemRepository;
import com.ust.item.mdm.item.repo.MetadataRepository;
import com.ust.item.mdm.model.LogisticalItem;

@Service
public class MongoLogisticalItemService implements LogisticalItemService {

	@Autowired
	private LogisticalItemRepository lItemRepository;
	@Autowired
	private MetadataRepository metaRepository;

	@Override
	public Collection<LogisticalItem> searchLogisticalItemByAnyAttribute(String attr, String attrVal) {
		Metadata metadata = metaRepository.getMetadataById(attr);
		return lItemRepository.searchLogisticalItemByAnyAttribute(attr, attrVal, metadata.getAttributeDataType());

	}

	@Override
	public Collection<Attribute> getLogisticalItemById(String id)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("LOGISTICAL_ITEM");
		LogisticalItem lItem = lItemRepository.getLogisticalItemById(id);
		return mergeWithLogisticalItemMetadata(lItem, metadata);

	}

	@Override
	public Collection<ItemSummary<LogisticalItem>> searchLogisticalItemByAnyAttributeForSummary(String attr, String attrVal) {
		Collection<LogisticalItem> lItems = this.searchLogisticalItemByAnyAttribute(attr, attrVal);
		Collection<ItemSummary<LogisticalItem>> itemsSummary = new ArrayList<ItemSummary<LogisticalItem>>(0);
		if (null != lItems && !lItems.isEmpty()) {
			for (LogisticalItem lItem : lItems) {
				ItemSummary<LogisticalItem> itemSummary = new ItemSummary<LogisticalItem>();
				itemSummary.setItem(lItem);
				itemsSummary.add(itemSummary);
			}
		}
		return itemsSummary;
	}

	private Collection<Attribute> mergeWithLogisticalItemMetadata(LogisticalItem lItem, Collection<Metadata> metadata)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {

		Collection<Attribute> attributes = new ArrayList<Attribute>(0);

		for (Metadata meta : metadata) {
			if (!StringUtils.isEmpty(meta.getId())) {
				Field field;
				try {
					field = LogisticalItem.class.getDeclaredField(meta.getId());
				} catch (NoSuchFieldException e) {
					continue;
				}
				field.setAccessible(true);
				Attribute attribute = new Attribute();
				attribute.setMetadata(meta);
				attribute.setId(meta.getId());

				attribute.setValues(field.get(lItem) instanceof List ? (List<Object>) field.get(lItem)
						: Arrays.asList(field.get(lItem)));
				attributes.add(attribute);
			}
		}
		return attributes;
	}

}
