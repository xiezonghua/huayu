package com.huayu.rest.test;

import com.huayu.rest.server.RestServer;

/**
 * @author wangmingzhe
 * 
 */
public class TestRestServer {

	static String realmPath = "/home/jeff/huayu/huayu-rest-example/realm.properties";
	static String loginName = "auth test";
	
	static String servicePackages = "com.huayu.rest.example.service";
	static int port = 8080;
	static String servicePath = "/service/*";
	
	public static void main(String[] args) throws Exception {
		RestServer rs = new RestServer();
		rs.start(port, servicePath, servicePackages);
//		rs.startWithRealm(port, servicePath, servicePackages, loginName, realmPath);
	}

}
