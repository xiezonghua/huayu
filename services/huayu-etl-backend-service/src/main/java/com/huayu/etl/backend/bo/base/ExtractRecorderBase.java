package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class ExtractRecorderBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  table id
	*/
	private Long tableId ; 

	/**
	*  export date
	*/
	private String exportDate ; 

	/**
	*  current max of the extract.
	*/
	private Long currentMaxId ; 

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

	public void setExportDate(String exportDate){
		this.exportDate = exportDate;
	} 

	public String getExportDate(){
		return exportDate;
	}  

	public void setCurrentMaxId(Long currentMaxId){
		this.currentMaxId = currentMaxId;
	} 

	public Long getCurrentMaxId(){
		return currentMaxId;
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