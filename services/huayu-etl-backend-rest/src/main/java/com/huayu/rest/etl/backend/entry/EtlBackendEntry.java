package com.huayu.rest.etl.backend.entry;

import com.huayu.rest.server.RestServer;

public class EtlBackendEntry {
	static String realmPath = "/home/jeff/huayu/huayu-rest-example/realm.properties";
	static String loginName = "auth test";
	
	static String servicePackages = "com.huayu.rest.etl.backend.service;com.huayu.rest.example.service";
	static int port = 8080;
	static String servicePath = "/etl/*";
	
	public static void main(String[] args) throws Exception {
//	System.out.println(WarehouseService.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//		String appxml = WarehouseService.class.getProtectionDomain().getCodeSource().getLocation().getPath() +"conf/applicationContext.xml";
//		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/applicationContext_local.xml");
//		WarehouseService hdfsClient = (WarehouseService) ac.getBean("warehouseService");
//		System.out.println("== " + hdfsClient);
		
		RestServer rs = new RestServer("classpath:conf/applicationContext_local.xml");
		
		rs.start(port, servicePath, servicePackages);
//		rs.startWithRealm(port, servicePath, servicePackages, loginName, realmPath);
	}
}
