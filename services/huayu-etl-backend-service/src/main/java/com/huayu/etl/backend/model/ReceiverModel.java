package com.huayu.etl.backend.model;

/**
 * @author xzh
 *
 */
public class ReceiverModel {
	private String companyName;
	private String appName;
	private String dbName;
	private String dateStr;
	private Long fileCount;
	private boolean increment;
	
	
	private String filePath;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Long getFileCount() {
		return fileCount;
	}

	public void setFileCount(Long fileCount) {
		this.fileCount = fileCount;
	}

	public boolean isIncrement() {
		return increment;
	}

	public void setIncrement(boolean increment) {
		this.increment = increment;
	}

	public String getFilePath(){
		if(null == filePath){
			StringBuffer pathStr = new StringBuffer();
			pathStr.append("/").append(companyName).append("/")
				   .append(appName).append("/")
				   .append(dbName).append("/")
				   .append(dateStr.substring(0, 4)).append("/")
				   .append(dateStr.substring(4, 6)).append("/")
				   .append(dateStr.substring(6, 8)).append("/");
			filePath = pathStr.toString();
		}
		
		
		return filePath;
	}
	
	public static void main(String[] args) {
		ReceiverModel model = new ReceiverModel();
		model.setDateStr("20160101");
		System.out.println(model.getFilePath());
	}
}
