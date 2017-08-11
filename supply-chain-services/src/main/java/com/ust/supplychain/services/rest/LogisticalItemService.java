package com.ust.supplychain.services.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Inject;
import com.sun.jersey.spi.resource.Singleton;
import com.ust.supplychain.dao.apis.LogisticalItemDAO;

@Singleton
@Path("logistical-item")
public class LogisticalItemService {

	@Inject
	private LogisticalItemDAO lItemDao;

	@Inject
	private ObjectMapper mapper;

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getLogisticalItem(@PathParam(value = "id") String id) {
		return Response.ok(lItemDao.getLogisticalById(id)).build();
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	public Response getLogisticalItem(@QueryParam(value = "attr") String attr,
			@QueryParam(value = "attr-val") String attrVal, @QueryParam(value = "attr-type") String attrType)
			throws JsonGenerationException, JsonMappingException, IOException {
		return Response.ok(mapper.writeValueAsString(lItemDao.getLogisticalByAnyAttr(attr, attrVal, attrType))).build();
	}
}
