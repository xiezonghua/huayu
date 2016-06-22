package com.huayu.platform.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author xzh
 *
 */
public class JsonUtils {
	public final static ObjectMapper objMapper = new ObjectMapper(); 
	
	public static String toJson(Object obj) {
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonResult = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	public static <T> T toBean(String jsonStr, Class<T> c) {
		T resultBean = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			resultBean = mapper.readValue(jsonStr, c);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}

	public static <T> T toBean(String jsonStr, Class<T> c, String targetRoot) {
		T resultBean = null;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode readTree = null;
		try {
			readTree = mapper.readTree(jsonStr);
			JsonNode jsonNode = readTree.get(targetRoot);
			resultBean = mapper.readValue(jsonNode.toString(), c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
	
	public static <T> List<T> toList(String jsonStr, Class<T> c ) {
		List<T> resultBean = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			resultBean = mapper.readValue(jsonStr, getJavaType(ArrayList.class , c));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
	
	public static <T> List<T> toList(String jsonStr, Class<T> c , String targetRoot) {
		List<T> resultBean = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode readTree = null;
			readTree = mapper.readTree(jsonStr);
			JsonNode jsonNode = readTree.get(targetRoot);
			resultBean = mapper.readValue(jsonNode.toString(), getJavaType(ArrayList.class , c));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
	
	public static <T> Map<String ,T> toMap(String jsonStr, Class<T> c) {
		Map<String, T> resultBean = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultBean = mapper.readValue(jsonStr, getJavaType(HashMap.class ,String.class , c));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
	
	public static <T> Map<String ,T> toMap(String jsonStr, Class<T> c , String targetRoot) {
		Map<String, T> resultBean = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode readTree = null;
			readTree = mapper.readTree(jsonStr);
			JsonNode jsonNode = readTree.get(targetRoot);
			resultBean = mapper.readValue(jsonNode.toString(), getJavaType(HashMap.class ,String.class , c));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
	
	public static JavaType getJavaType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objMapper.getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
	}
	
}
