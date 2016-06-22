package com.huayu.rest.example.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.rest.service.dto.SimpleResponse;

/**
 * @author wangmingzhe
 * 
 */
@Path("/test")
public class TestService {

	private static Logger logger = LoggerFactory.getLogger(TestService.class);

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public SimpleResponse test() {		
		logger.info("this is test method");
		SimpleResponse response = new SimpleResponse();
		return response;
	}

}
