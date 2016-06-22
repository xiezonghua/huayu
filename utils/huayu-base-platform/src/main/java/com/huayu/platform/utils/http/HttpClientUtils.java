package com.huayu.platform.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huayu.platform.utils.JsonUtils;

/**
 * 
 * @author xzh
 *
 */
public class HttpClientUtils {
	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	
	private static HttpClient client =  new HttpClient();
	
	public static HttpResponse doGet(String url){
		return doGet(url , null);
	}
	
	public static HttpResponse doGet(String url , Map<String , Object> params){
		HttpMethod method = new GetMethod(url);
		method.setQueryString(getNameValuePairs(params));
		return doMethod(method, url, params); 
	}
	
	public static HttpResponse doPost(String url){
		return doPost(url, null); 
	}
	
	public static HttpResponse doPost(String url , Map<String , Object> params){
		PostMethod method = new PostMethod(url);
		
		method.addParameters(getNameValuePairs(params));
		return doMethod(method, url, params); 
	}
	
	public static HttpResponse doPut(String url , Object params){
		PutMethod method = new PutMethod(url);
		RequestEntity requestEntity;
		try {
			requestEntity = new StringRequestEntity(JsonUtils.toJson(params), "application/json", "utf8");
			method.setRequestEntity(requestEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return doMethod(method, url, null); 
	}
	
	private static HttpResponse doMethod(HttpMethod method , String url , Map<String , Object> params ){
		String result  = "" ;
		HttpResponse  response = new HttpResponse();
		try {
			method.setRequestHeader("Accept-Charset", "UTF-8");
			client.executeMethod(method);
			
			response.setCode(method.getStatusCode());
			
			if(method.getStatusCode() == HttpStatus.SC_OK){
				method.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
				method.getResponseHeaders("Content-Type")[0].setValue("application/json;charset=UTF-8");
				result = method.getResponseBodyAsString();
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode readTree = mapper.readTree(result);
				JsonNode resultJsonNode = readTree.get("result");
				JsonNode statusJsonNode = readTree.get("success");
				JsonNode msgJsonNode = readTree.get("errorMessage");
				response.setResult(resultJsonNode.toString());
				response.setSuccess(new Boolean(statusJsonNode.toString()));
				response.setMsg(msgJsonNode.asText());
				
			}else{
				logger.warn("access failure: {}" , method.getStatusLine());
				response.setMsg(method.getStatusText());
			}
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
		
		return response ;
	}
	
	
	private static NameValuePair[] getNameValuePairs(Map<String, Object> params){
		NameValuePair[] pairs = new NameValuePair[0];
		if( params != null ){
			pairs = new NameValuePair[params.size()] ;
			int i = 0 ;
			for(String key : params.keySet()){
				NameValuePair pairEle = new NameValuePair(key , params.get(key).toString());
				pairs[i++] = pairEle ;
			}
		}
		return pairs ;
	}
	
	public static void main(String[] args) {	

//		System.out.println(doGet("http://localhost:8080/etl/config/0ec876f7-c3f5-42f4-9f49-d043f3a94912"));

//		System.out.println(doPost("http://localhost:8080/etl/backend/mjld/b2b/uts/20160517/4"));
//		System.out.println(doPost("http://192.168.106.54:8080/etl/backend/mjld/b2b/uts/20160520/5"));
	}
}
