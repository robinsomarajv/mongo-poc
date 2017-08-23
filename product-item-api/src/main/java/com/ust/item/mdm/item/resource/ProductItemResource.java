package com.ust.item.mdm.item.resource;

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
import com.ust.item.mdm.dto.ItemSummary;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.item.repo.MetadataRepository;
import com.ust.item.mdm.item.repo.ProductRepository;
import com.ust.item.mdm.item.service.ProductService;
import com.ust.item.mdm.model.Product;

@RestController
@RequestMapping(path = "/product")
public class ProductItemResource {

	@Autowired
	ProductService mongoProductService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<Product> searchProducts(@RequestParam(name = "attrName") String attr,
			@RequestParam(name = "attrVal") String attrVal) {
		return mongoProductService.searchProductByAnyAttribute(attr, attrVal);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{pid}")
	public @ResponseBody Collection<Attribute> getProduct(@PathVariable("pid") String pid)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return mongoProductService.getProductById(pid);
		
	}
	
	@RequestMapping(path = "/summary", method = RequestMethod.GET)
	public @ResponseBody Collection<ItemSummary> getProductSummary(@RequestParam(name = "attrName") String attr,
			@RequestParam(name = "attrVal") String attrVal){
		return mongoProductService.searchProductByAnyAttributeForSummary(attr, attrVal);
	}
	

}
