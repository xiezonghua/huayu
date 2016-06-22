package com.huayu.etl.backend.handler;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.platform.exception.ServiceRuntimeException;

public class EtlCheckHandler {
	private final static Logger LOGGER = LoggerFactory.getLogger(EtlCheckHandler.class);
	
	public static boolean beforeWarehouse(String path , long count){
		boolean result = true;
		StringBuilder msg = new StringBuilder(); 
		if(StringUtils.isBlank(path)){
			LOGGER.error("The file {} is blank." , path); 
			msg.append("The file ").append(path).append("is blank ; ");
			result = false;
		}
		
		File dir = new File(path);
		Collection<File> listFiles = FileUtils.listFiles(dir, null , false);
		LOGGER.debug("The directory {} include {} files." ,path ,listFiles.size());
		
		if( count != listFiles.size()){
			LOGGER.error("The total of files can't match , uplode({}) != {}" , count , listFiles.size());
			msg.append("The total of files can't match , uplode(").append(count).append(") != ").append(listFiles.size());
			result = false;
		}
		
		LOGGER.info("Before hdfs , check completed {}" , path);
		
		if(!result){
			throw new ServiceRuntimeException(msg.toString());
		}
		return result;
	}
	
	public static void main(String[] args) {
		String path = "/home/xzh/User" ;
		File dir = new File(path);
		Collection<File> listFiles = FileUtils.listFiles(dir, null , false);
		
		LOGGER.debug("The directory {} include {} files." ,path ,listFiles.size());
		
		for(File file : listFiles){
			LOGGER.debug("{}", file.getAbsolutePath());
		}
		
		if(10 == listFiles.size()){
			
		}
		
	}
	
}
