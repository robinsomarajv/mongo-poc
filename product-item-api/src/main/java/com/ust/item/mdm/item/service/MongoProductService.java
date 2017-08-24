package com.ust.item.mdm.item.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.ItemSummary;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.item.repo.GlobalTradeItemRepository;
import com.ust.item.mdm.item.repo.MetadataRepository;
import com.ust.item.mdm.item.repo.ProductRepository;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.Product;

@Service
public class MongoProductService implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private GlobalTradeItemRepository gtItemRepository;
	@Autowired
	private MetadataRepository metaRepository;

	@Override
	public Collection<Product> searchProductByAnyAttribute(String attr, String attrVal) {
		Metadata metadata = metaRepository.getMetadataById(attr);
		return productRepository.searchProductByAnyAttribute(attr, attrVal, metadata.getAttributeDataType());

	}

	@Override
	public Collection<Attribute> getProductById(String pid)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("PRODUCT");
		Product product = productRepository.getProductByPid(pid);
		return mergeWithProductMetadata(product, metadata);

	}

	@Override
	public Collection<ItemSummary<Product>> searchProductByAnyAttributeForSummary(String attr, String attrVal) {
		Collection<Product> products = this.searchProductByAnyAttribute(attr, attrVal);
		Collection<GlobalTradeItem> tradeItems = new ArrayList<GlobalTradeItem>(0);
		Collection<ItemSummary<Product>> itemsSummary = new ArrayList<ItemSummary<Product>>(0);
		if (null != products && !products.isEmpty()) {
			for (Product product : products) {
				List<String> gtins = product.getConsumable_gtins();
				ItemSummary<Product> itemSummary = new ItemSummary<Product>();
				itemSummary.setItem(product);
				for (String gtin : gtins) {
					tradeItems.addAll(gtItemRepository.searchGlobalTradeItemByAnyAttribute("tradeItemGtin", gtin,
							"Alphanumeric"));
				}
				List<Map> conceptCount = new ArrayList<>();
				Map<String,String> concepts = new HashMap<>();
				concepts.put("label","T");
				concepts.put("value",String.valueOf(tradeItems.size()));
				conceptCount.add(concepts );
				itemSummary.setConceptCount(conceptCount);
				itemsSummary.add(itemSummary);
			}
		}
		return itemsSummary;
	}

	private Collection<Attribute> mergeWithProductMetadata(Product product, Collection<Metadata> metadata)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {

		Collection<Attribute> attributes = new ArrayList<Attribute>(0);

		for (Metadata meta : metadata) {
			if (!StringUtils.isEmpty(meta.getId())) {
				Field field;
				try {
					field = Product.class.getDeclaredField(meta.getId());
				} catch (NoSuchFieldException e) {
					continue;
				}
				field.setAccessible(true);
				Attribute attribute = new Attribute();
				attribute.setMetadata(meta);
				attribute.setId(meta.getId());

				attribute.setValues(field.get(product) instanceof List ? (List<Object>) field.get(product)
						: Arrays.asList(field.get(product)));
				attributes.add(attribute);
			}
		}
		return attributes;
	}

}
