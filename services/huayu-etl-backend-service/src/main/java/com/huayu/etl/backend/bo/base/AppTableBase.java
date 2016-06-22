package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class AppTableBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  application id
	*/
	private Long appId ; 

	/**
	*  table name
	*/
	private String name ; 

	/**
	*  warehouse table name
	*/
	private String warehouseName ; 

	/**
	*  partition mark
	*/
	private Boolean partitionLabel ;

	/**
	*  partition outside sql ; the column partitions will ignore, if exist the value 
	*/
	private String partitionExt ; 

	/**
	*  1: all , 2: increment  
	*/
	private Byte extractType ; 

	/**
	*  1: extendal ; 2: inner ; 3:other
	*/
	private Byte tableType ; 

	/**
	*  boolean 
	*/
	private Boolean existIdField ; 

	/**
	*  column name for id
	*/
	private String idColumnName ; 

	/**
	*  boolean for create date column
	*/
	private Boolean existCreateDate ; 

	/**
	*  column name for create date
	*/
	private String createDateColumnName ; 

	/**
	*  boolean for update date column
	*/
	private Boolean existUpdateDate ; 

	/**
	*  column name for update date
	*/
	private String updateDateColumnName ; 

	/**
	*  introduce for it
	*/
	private String introduce ; 

	/**
	*  boolean for update extract
	*/
	private Boolean existUpdateExtractor ; 

	/**
	*  specify condition of update
	*/
	private String specifyUpdateCondition ; 

	/**
	*  boolean for delete extract
	*/
	private Boolean existDeleteExtractor ; 

	/**
	*  specify condition of delete
	*/
	private String specifyDeleteCondition ; 

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

	public void setAppId(Long appId){
		this.appId = appId;
	} 

	public Long getAppId(){
		return appId;
	}  

	public void setName(String name){
		this.name = name;
	} 

	public String getName(){
		return name;
	}  

	public void setWarehouseName(String warehouseName){
		this.warehouseName = warehouseName;
	} 

	public String getWarehouseName(){
		return warehouseName;
	}  

	public void setPartitionLabel(Boolean partitionLabel){
		this.partitionLabel = partitionLabel;
	} 

	public Boolean getPartitionLabel(){
		return partitionLabel;
	}  

	public void setPartitionExt(String partitionExt){
		this.partitionExt = partitionExt;
	} 

	public String getPartitionExt(){
		return partitionExt;
	}  

	public void setExtractType(Byte extractType){
		this.extractType = extractType;
	} 

	public Byte getExtractType(){
		return extractType;
	}  

	public void setTableType(Byte tableType){
		this.tableType = tableType;
	} 

	public Byte getTableType(){
		return tableType;
	}  

	public void setExistIdField(Boolean existIdField){
		this.existIdField = existIdField;
	} 

	public Boolean getExistIdField(){
		return existIdField;
	}  

	public void setIdColumnName(String idColumnName){
		this.idColumnName = idColumnName;
	} 

	public String getIdColumnName(){
		return idColumnName;
	}  

	public void setExistCreateDate(Boolean existCreateDate){
		this.existCreateDate = existCreateDate;
	} 

	public Boolean getExistCreateDate(){
		return existCreateDate;
	}  

	public void setCreateDateColumnName(String createDateColumnName){
		this.createDateColumnName = createDateColumnName;
	} 

	public String getCreateDateColumnName(){
		return createDateColumnName;
	}  

	public void setExistUpdateDate(Boolean existUpdateDate){
		this.existUpdateDate = existUpdateDate;
	} 

	public Boolean getExistUpdateDate(){
		return existUpdateDate;
	}  

	public void setUpdateDateColumnName(String updateDateColumnName){
		this.updateDateColumnName = updateDateColumnName;
	} 

	public String getUpdateDateColumnName(){
		return updateDateColumnName;
	}  

	public void setIntroduce(String introduce){
		this.introduce = introduce;
	} 

	public String getIntroduce(){
		return introduce;
	}  

	public void setExistUpdateExtractor(Boolean existUpdateExtractor){
		this.existUpdateExtractor = existUpdateExtractor;
	} 

	public Boolean getExistUpdateExtractor(){
		return existUpdateExtractor;
	}  

	public void setSpecifyUpdateCondition(String specifyUpdateCondition){
		this.specifyUpdateCondition = specifyUpdateCondition;
	} 

	public String getSpecifyUpdateCondition(){
		return specifyUpdateCondition;
	}  

	public void setExistDeleteExtractor(Boolean existDeleteExtractor){
		this.existDeleteExtractor = existDeleteExtractor;
	} 

	public Boolean getExistDeleteExtractor(){
		return existDeleteExtractor;
	}  

	public void setSpecifyDeleteCondition(String specifyDeleteCondition){
		this.specifyDeleteCondition = specifyDeleteCondition;
	} 

	public String getSpecifyDeleteCondition(){
		return specifyDeleteCondition;
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