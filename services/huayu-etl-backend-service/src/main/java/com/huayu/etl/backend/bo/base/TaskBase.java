package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class TaskBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  company name
	*/
	private String companyName ; 

	/**
	*  application name
	*/
	private String appName ; 

	/**
	*  database name
	*/
	private String databaseName ; 

	/**
	*  date for the extractor
	*/
	private String extractDate ; 

	/**
	*  total of upload files
	*/
	private Integer fileCount ; 

	/**
	*  1: all , 2: increment  
	*/
	private Byte extractType ; 

	/**
	*  adder user id
	*/
	private Long createUserId ; 

	/**
	*  time of add
	*/
	private Date createDate ; 

	/**
	*  updator user id
	*/
	private Long updateUserId ; 

	/**
	*  time of updator
	*/
	private Date updateDate ; 


	public void setId(Long id){
		this.id = id;
	} 

	public Long getId(){
		return id;
	}  

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	} 

	public String getCompanyName(){
		return companyName;
	}  

	public void setAppName(String appName){
		this.appName = appName;
	} 

	public String getAppName(){
		return appName;
	}  

	public void setDatabaseName(String databaseName){
		this.databaseName = databaseName;
	} 

	public String getDatabaseName(){
		return databaseName;
	}  

	public void setExtractDate(String extractDate){
		this.extractDate = extractDate;
	} 

	public String getExtractDate(){
		return extractDate;
	}  

	public void setFileCount(Integer fileCount){
		this.fileCount = fileCount;
	} 

	public Integer getFileCount(){
		return fileCount;
	}  

	public void setExtractType(Byte extractType){
		this.extractType = extractType;
	} 

	public Byte getExtractType(){
		return extractType;
	}  

	public void setCreateUserId(Long createUserId){
		this.createUserId = createUserId;
	} 

	public Long getCreateUserId(){
		return createUserId;
	}  

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	} 

	public Date getCreateDate(){
		return createDate;
	}  

	public void setUpdateUserId(Long updateUserId){
		this.updateUserId = updateUserId;
	} 

	public Long getUpdateUserId(){
		return updateUserId;
	}  

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	} 

	public Date getUpdateDate(){
		return updateDate;
	}  

}