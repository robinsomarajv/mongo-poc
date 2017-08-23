package com.ust.item.mdm.item.resource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.dto.Metadata;
import com.ust.item.mdm.item.service.GTItemService;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.Product;

@RestController
@RequestMapping(path = "/gtItem")
public class GlobalTradeItemResource {

	@Autowired
	GTItemService gtItemService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<GlobalTradeItem> searchGlobalTradeItems(@RequestParam(name = "attrName") String attr,
			@RequestParam(name = "attrVal") String attrVal) {
		
		return gtItemService.searchGtItemsByAnyAttribute(attr, attrVal);
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/{pid}")
//	public @ResponseBody Collection<Attribute> getProduct(@PathVariable("pid") String pid)
//			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		Collection<Metadata> metadata = metaRepository.getMetadataBySpecification("PRODUCT");
//		GlobalTradeItem gtItem = globalTradeItemRepository.getGlobalTradeItemByPid(pid);
//		return mergeWithProductMetadata(gtItem, metadata);
//	}

	
}
