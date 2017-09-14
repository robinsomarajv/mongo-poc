package com.ust.item.mdm.item.resource;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.item.mdm.dto.Attribute;
import com.ust.item.mdm.item.service.GTItemService;
import com.ust.item.mdm.model.GlobalTradeItem;
import com.ust.item.mdm.model.GlobalTradeItemId;

@RestController
@RequestMapping(path = "/gtItem")
@CrossOrigin(origins = "*")
public class GlobalTradeItemResource {

	@Autowired
	GTItemService gtItemService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<GlobalTradeItem> searchGlobalTradeItems(
			@RequestParam(name = "attrName") String attr, @RequestParam(name = "attrVal") String attrVal) {

		return gtItemService.searchGtItemsByAnyAttribute(attr, attrVal);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{tid}")
	public @ResponseBody Collection<Attribute> getTIDetails(@PathVariable("tid") String tid)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, IOException {
		
		return gtItemService.getGtItembyId(new ObjectMapper().readValue(tid, GlobalTradeItemId.class));
	}

}
