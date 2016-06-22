package com.huayu.platform.configure;

import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesConfigurationUtils {
	private PropertiesConfiguration configuration;

	public PropertiesConfigurationUtils(String filePath) {
		loadConfiguration(filePath);
	}

	public Map<String, Object> loadConfiguration(String filePath) {

		try {
			configuration = new PropertiesConfiguration(filePath);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getProperty(String key) {
		return configuration.getProperty(key);
	}
	
	public <T> T getValue(String key){
		T t = null ;
		if(configuration.containsKey(key)){
			if( t instanceof String){
				
			}
		}
		return null;
	}
	
}
