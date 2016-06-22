package com.huayu.rest.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.etl.backend.bo.ReplacementRegexpRule;
import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.etl.backend.model.AppTableModel;
import com.huayu.etl.backend.model.ApplicationInfoModel;
import com.huayu.platform.utils.http.HttpClientUtils;
import com.huayu.platform.utils.http.HttpResponse;
import com.huayu.rest.server.RestServer;

/**
 * @author xzh 
 * 
 */
public class TestRestServer {

	static String realmPath = "/home/jeff/huayu/huayu-rest-example/realm.properties";
	static String loginName = "auth test";
	
	static String servicePackages = "com.huayu.rest.etl.backend.service;com.huayu.rest.example.service";
	static int port = 8080;
	static String servicePath = "/service/*";
	
	public static void main(String[] args) throws Exception {
//		testServer();
		
//		testPut();
		
		testAppInfoJson();
		
//		testRegexpList();
	}
	
	public static void testRegexpList(){
		HttpResponse response = HttpClientUtils.doGet("http://localhost:8080/etl/config/regexp/list");
		List<ReplacementRegexpRule> beanlist = response.toList(ReplacementRegexpRule.class);
		System.out.println(beanlist);
		for(ReplacementRegexpRule rule : beanlist){
			System.out.println(rule.getName() + " \t" + rule.getRegexpReplacement() +"\t" + rule.getReplacementContent());
		}
		
		
	}
	
	public static void testPut(){
		List<ExtractRecorder> recorders = new ArrayList<ExtractRecorder>();
		ExtractRecorder extractRecorder = new ExtractRecorder();
		extractRecorder.setTableId(1l);
		extractRecorder.setCurrentMaxId(100l);
		extractRecorder.setExportDate("20160111");
		recorders.add(extractRecorder);
		ExtractRecorder extractRecorder1 = new ExtractRecorder();
		extractRecorder1.setTableId(2l);
		extractRecorder1.setCurrentMaxId(1100l);
		extractRecorder1.setExportDate("20160112");
		recorders.add(extractRecorder1);
		
		
		HttpResponse appInfo = HttpClientUtils.doPut("http://localhost:8080/etl/config/addrecorder/list" , recorders);
		
		System.out.println(appInfo);
	}
	
//	public static void testServer() throws Exception{
//		System.out.println(WarehouseService.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//		String appxml = WarehouseService.class.getProtectionDomain().getCodeSource().getLocation().getPath() +"conf/applicationContext.xml";
//		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext_local.xml" );
//		WarehouseService hdfsClient = (WarehouseService) ac.getBean("warehouseService");
//		System.out.println("== " + hdfsClient.toString());
//		
//		RestServer rs = new RestServer();
//		
//		rs.start(port, servicePath, servicePackages);
//		rs.startWithRealm(port, servicePath, servicePackages, loginName, realmPath);
//	}
	
	public  static void testAppInfoJson() throws JsonProcessingException, IOException{
		HttpResponse response = HttpClientUtils.doGet("http://localhost:8080/etl/config/app/allInfo/0ec876f7-c3f5-42f4-9f49-d043f3a94912");
//		HttpResponse response = HttpClientUtils.doGet("http://localhost:8080/etl/config/app/0ec876f7-c3f5-42f4-9f49-d043f3a94912");
		ApplicationInfoModel readValue =  response.toBean(ApplicationInfoModel.class);
		System.out.println(readValue.getAppName());
		for(AppTableModel table : readValue.getTables()){
			System.out.println(table.getName());
			for(TableColumn column : table.getColumns()){
				System.out.println("\t " + column.getName() + "\t " + column.getColumnComment());
			}
		}
	}

}
