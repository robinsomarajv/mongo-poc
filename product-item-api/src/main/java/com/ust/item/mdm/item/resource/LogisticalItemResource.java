package com.ust.item.mdm.item.resource;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.ItemSummary;
import com.ust.item.mdm.item.service.LogisticalItemService;
import com.ust.item.mdm.model.LogisticalItem;

@RestController
@RequestMapping(path = "/litem")
public class LogisticalItemResource {

	@Autowired
	LogisticalItemService mongoLItemService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<LogisticalItem> searchProducts(@RequestParam(name = "attrName") String attr,
			@RequestParam(name = "attrVal") String attrVal) {
		return mongoLItemService.searchLogisticalItemByAnyAttribute(attr, attrVal);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody Collection<Attribute> getProduct(@PathVariable("id") String id)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return mongoLItemService.getLogisticalItemById(id);
		
	}
	
	@RequestMapping(path = "/summary", method = RequestMethod.GET)
	public @ResponseBody Collection<ItemSummary<LogisticalItem>> getProductSummary(@RequestParam(name = "attrName") String attr,
			@RequestParam(name = "attrVal") String attrVal){
		return mongoLItemService.searchLogisticalItemByAnyAttributeForSummary(attr, attrVal);
	}
	

}
