package com.toread.etl.configure.constant;

import com.toread.cloud.platform.configure.PropertiesUtils;

public final class SqlConfiguration {
	
	public static final String JDBC_DB_DIRVER = "jdbc.db.dirver" ;
	
	public static final String JDBC_DB_URL = "jdbc.db.url";
	
	public static final String JDBC_DB_NAME = "jdbc.db.name";
	
	public static final String JDBC_DB_USERNAME = "jdbc.db.username";
	
	public static final String JDBC_DB_PASSWORD = "jdbc.db.password";
	
	static final String CONFIG_PROPERTIES_NAME = "config.properties";

	public static String getProperty(String key){
		return PropertiesUtils.get(CONFIG_PROPERTIES_NAME, key);
	}

}
