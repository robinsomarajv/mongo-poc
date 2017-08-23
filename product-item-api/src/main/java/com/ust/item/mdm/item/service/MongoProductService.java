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
import com.ust.item.mdm.item.repo.MetadataRepository;
import com.ust.item.mdm.item.repo.ProductRepository;
import com.ust.item.mdm.model.Product;

@Service
public class MongoProductService implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MetadataRepository metaRepository;
	
	@Override
	public Collection<Product> searchProductByAnyAttribute(String attr, String attrVal) {
		Metadata metadata = metaRepository.getMetadataById(attr);
		return productRepository.searchProductByAnyAttribute(attr, attrVal, metadata.getAttributeDataType());
		
	}

	@Override
	public Collection<Attribute> getProductById(String pid) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("PRODUCT");
		Product product = productRepository.getProductByPid(pid);
		return mergeWithProductMetadata(product, metadata);
		
	}
	

	@Override
	public Collection<ItemSummary> searchProductByAnyAttributeForSummary(String attr, String attrVal) {
		Collection<Product> products = this.searchProductByAnyAttribute(attr, attrVal);
		for(Product product : products){
			List<String> gtins = product.getConsumable_gtins();
			
		}
		return null;
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
