package com.huayu.platform.configure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * @author xzh
 *
 */
public class PropertiesUtils {

	private static PropertiesUtils initor = new PropertiesUtils();

	private static Map<String, Object> configMap = new ConcurrentHashMap<String, Object>();

	private PropertiesUtils() {
	}

	public static String get(String configFile, String propertyKey) {
		return get(configFile, propertyKey, "");
	}
	
	public static String get(String configFile, String propertyKey , String defaultValue) {
		if (!configMap.containsKey(configFile)) {
			initor.initConfig(configFile);
		}
		PropertiesConfiguration config = (PropertiesConfiguration) configMap
				.get(configFile);
		String value = config.getString(propertyKey , defaultValue);
		return value;
	}

	private synchronized void initConfig(String configFile) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(configFile);
			configMap.put(configFile, config);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(PropertiesUtils.get("conf/jdbc-mysql.properties", "jdbc.driver"));  
	}

}
