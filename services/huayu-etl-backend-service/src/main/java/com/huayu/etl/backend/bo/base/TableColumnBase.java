package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class TableColumnBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  table id
	*/
	private Long tableId ; 

	/**
	*  column name
	*/
	private String name ; 

	/**
	*  warehouse column name
	*/
	private String warehouseName ; 

	/**
	*  data type for column
	*/
	private String dataType ; 

	/**
	*  warehouse data type for column
	*/
	private String warehouseDataType ; 

	/**
	*  columnComment
	*/
	private String columnComment ; 

	/**
	*  column order
	*/
	private Integer columnOrder ; 

	/**
	*  nullable mark
	*/
	private Boolean nullableLabel ;

	/**
	*  partition mark
	*/
	private Boolean partitionLabel ;

	/**
	*  needRegexpLabel
	*/
	private Boolean needRegexpLabel ;

	/**
	*  regexp str
	*/
	private String regexpIds ; 

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

	public void setTableId(Long tableId){
		this.tableId = tableId;
	} 

	public Long getTableId(){
		return tableId;
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

	public void setDataType(String dataType){
		this.dataType = dataType;
	} 

	public String getDataType(){
		return dataType;
	}  

	public void setWarehouseDataType(String warehouseDataType){
		this.warehouseDataType = warehouseDataType;
	} 

	public String getWarehouseDataType(){
		return warehouseDataType;
	}  

	public void setColumnComment(String columnComment){
		this.columnComment = columnComment;
	} 

	public String getColumnComment(){
		return columnComment;
	}  

	public void setColumnOrder(Integer columnOrder){
		this.columnOrder = columnOrder;
	} 

	public Integer getColumnOrder(){
		return columnOrder;
	}  

	public void setNullableLabel(Boolean nullableLabel){
		this.nullableLabel = nullableLabel;
	} 

	public Boolean getNullableLabel(){
		return nullableLabel;
	}  

	public void setPartitionLabel(Boolean partitionLabel){
		this.partitionLabel = partitionLabel;
	} 

	public Boolean getPartitionLabel(){
		return partitionLabel;
	}  

	public void setNeedRegexpLabel(Boolean needRegexpLabel){
		this.needRegexpLabel = needRegexpLabel;
	} 

	public Boolean getNeedRegexpLabel(){
		return needRegexpLabel;
	}  

	public void setRegexpIds(String regexpIds){
		this.regexpIds = regexpIds;
	} 

	public String getRegexpIds(){
		return regexpIds;
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