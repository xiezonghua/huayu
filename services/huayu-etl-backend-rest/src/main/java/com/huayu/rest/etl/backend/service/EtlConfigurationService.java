package com.huayu.rest.etl.backend.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.huayu.etl.backend.bo.ApplicationInfo;
import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.etl.backend.bo.ReplacementRegexpRule;
import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.etl.backend.model.ApplicationInfoModel;
import com.huayu.etl.backend.service.AppConfigurationService;
import com.huayu.rest.service.dto.SimpleResponse;

@Component
@Path("/config")
public class EtlConfigurationService {
	private static Logger logger = LoggerFactory.getLogger(EtlReceiverService.class);
	
	@Resource(name="appConfigurationService")
	private AppConfigurationService appConfigurationService;
	
	@GET
	@Path("app/{appUuid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse appInfo(@PathParam("appUuid") String appUuid){
		logger.info("Get app base information , uuid {}" , appUuid);
		ApplicationInfo model = appConfigurationService.queryAppInfo(appUuid);
		
		SimpleResponse result = new SimpleResponse();
		result.setResult(model);
		System.out.println(result.toString());
		return result ;
	}
	
	@GET
	@Path("app/allInfo/{appUuid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse appAllInfo(@PathParam("appUuid") String appUuid){
		logger.info("Get app all information , uuid {}" , appUuid);
		ApplicationInfoModel model = appConfigurationService.queryAppAllInfo(appUuid);
		
		SimpleResponse result = new SimpleResponse();
		result.setResult(model);
		return result ;
	}
	
	
	@GET
	@Path("app/columns/{appUuid}/{tableName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse appTableColumnInfo(@PathParam("appUuid") String appUuid , @PathParam("tableName") String tableName ){
		logger.info("Get columns information by appuuid and tableName, uuid {}   tableName {}" , appUuid , tableName);
		List<TableColumn> model = appConfigurationService.queryTableColumns(appUuid, tableName);
		
		SimpleResponse result = new SimpleResponse();
		result.setResult(model);
		return result ;
	}
	
	@GET
	@Path("app/table/columns/{tableId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse appTableColumnById(@PathParam("tableId") Long tableId  ){
		logger.info("Get columns information by tableId  , tableId {}" , tableId );
		List<TableColumn> model = appConfigurationService.queryTableColumns(tableId);
		
		SimpleResponse result = new SimpleResponse();
		result.setResult(model);
		return result ;
	}
	
	@GET
	@Path("regexp/list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse regexpList(){
		logger.info("Get regexp all information ");
		SimpleResponse result = new SimpleResponse();
		
		List<ReplacementRegexpRule> model = appConfigurationService.queryRegexpRuleList();
		result.setResult(model);
		return result ;
	}
	
	@PUT
	@Path("addrecorder/{tableId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse addExtractRecorder(@PathParam("tableId") Long tableId  , ExtractRecorder recorder){
		logger.info("add extract recoder , {}" , recorder);
		
		SimpleResponse result = new SimpleResponse();
		appConfigurationService.addExtactRecorder(recorder);
		return result ;
	}
	
	@PUT
	@Path("addrecorder/list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SimpleResponse addExtractRecorder(List<ExtractRecorder> recorders){
		logger.info("add extract recoder list ...");
		SimpleResponse result = new SimpleResponse();
		if(recorders != null && recorders.size() > 0){
			appConfigurationService.addBatchExtactRecorder(recorders);
		}else{
			logger.warn("the body is empty or bad format! ,  {}" , recorders);
			result.setErrorMessage("the body is empty or bad format!");
		}
		return result ;
	}


}
