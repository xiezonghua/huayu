package com.huayu.etl.backend.constant;

import com.huayu.platform.configure.PropertiesUtils;

/**
 * 
 * @author xzh
 *
 */
public final class EtlConfigurationConstant {
	public final static String APPLICATION_CONFIGURATION_FILE_PATH = "conf/elt-backend-application.properties" ;
	
	public final static String HDFS_URL = "hdfs.url";
	
	public final static String HDFS_USERNAME = "hdfs.username";
	
	public final static String HDFS_PASSWORD = "hdfs.password";
	
	public final static String HDFS_ETL_HOME = "hdfs.etl.home" ;
	
	public final static String HIVE_URL = "hive.url";
	
	public final static String HIVE_USERNAME = "hive.username";
	
	public final static String HIVE_PASSWORD = "hive.password";
	
	public final static String RSYNC_HOME = "rsync.home";
	
	public final static String PARTITION_DATE_DEFAULT_NAME = "partition.date.default.name";
	
	public static String getPropertyValue(String key){
		return PropertiesUtils.get(EtlConfigurationConstant.APPLICATION_CONFIGURATION_FILE_PATH, key);
	}
}
