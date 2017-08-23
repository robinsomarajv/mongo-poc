package com.ust.item.mdm.logistical_item.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.logistical_item.repo.MetadataRepository;
import com.ust.item.mdm.logistical_item.repo.ProductRepository;
import com.ust.item.mdm.model.Product;

@RestController
@RequestMapping(path = "/product")
public class ProductItemResource {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MetadataRepository metaRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<Product> searchProducts(@RequestParam(name = "attrName") String attr,
			@RequestParam(name = "attrVal") String attrVal) {
		Metadata metadata = metaRepository.getMetadataById(attr);
		return productRepository.searchProductByAnyAttribute(attr, attrVal, metadata.getAttributeDataType());

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{pid}")
	public @ResponseBody Collection<Attribute> getProduct(@PathVariable("pid") String pid)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("PRODUCT");
		Product product = productRepository.getProductByPid(pid);
		return mergeWithProductMetadata(product, metadata);
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
