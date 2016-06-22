package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class ApplicationInfoBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  appplication ID
	*/
	private String appUuid ; 

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

	/**
	*  introduce for it
	*/
	private String introduce ; 


	public void setId(Long id){
		this.id = id;
	} 

	public Long getId(){
		return id;
	}  

	public void setAppUuid(String appUuid){
		this.appUuid = appUuid;
	} 

	public String getAppUuid(){
		return appUuid;
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

	public void setIntroduce(String introduce){
		this.introduce = introduce;
	} 

	public String getIntroduce(){
		return introduce;
	}  

}