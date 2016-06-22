package com.huayu.rest.etl.backend.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.huayu.etl.backend.model.ReceiverModel;
import com.huayu.rest.service.dto.SimpleResponse;

@Component
@Path("/backend")
public class EtlReceiverService {

	private static Logger logger = LoggerFactory
			.getLogger(EtlReceiverService.class);
	
//	@Resource(name="warehouseService")
//	private WarehouseService service;

	@POST
	@Path("/{companyName}/{appName}/{dbName}/{dateStr}/{fileCount}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse receiver(
			@PathParam("companyName") String companyName,
			@PathParam("appName") String appName,
			@PathParam("dbName") String dbName,
			@PathParam("dateStr") String dateStr,
			@PathParam("fileCount") String fileCount,
			@DefaultValue("true") @QueryParam("increment") boolean increment) {
		logger.info("Elt backend service starting...");
		SimpleResponse response = new SimpleResponse();
		try{
			ReceiverModel model = new ReceiverModel();
			model.setAppName(appName);
			model.setCompanyName(companyName);
			model.setDbName(dbName);
			model.setDateStr(dateStr);
			model.setFileCount(Long.valueOf(fileCount));
			model.setIncrement(increment);
			
//			service.handler(model);
			
		}catch(Exception e){
			e.printStackTrace();
			response.setErrorMessage(e.getMessage());
			response.setSuccess(false);
		}
		 
		return response;
	}
	

}
