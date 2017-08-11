package com.ust.supplychain.services.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Inject;
import com.sun.jersey.spi.resource.Singleton;
import com.ust.supplychain.dao.apis.TradeItemDAO;

@Singleton
@Path("trade-item")
public class TradeItemService {

	@Inject
	private TradeItemDAO dao;

	@Inject
	private ObjectMapper mapper;

	@GET
	@Path("/list")
	@Produces("application/json")
	public Response getTradeItem(@QueryParam(value = "attr") String attr,
			@QueryParam(value = "attr-val") String attrVal, @QueryParam(value = "attr-type") String attrType)
			throws JsonGenerationException, JsonMappingException, IOException {
		return Response.ok(mapper.writeValueAsString(dao.getTradeItemByAnyAttr(attr, attrVal, attrType))).build();
	}
}
