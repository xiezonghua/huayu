package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class TaskLogBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  taskId
	*/
	private Long taskId ; 

	/**
	*  0: info , 1:warning , 3: error
	*/
	private Byte logLevel ; 

	/**
	*  msg
	*/
	private String msg ; 

	/**
	*  time of add
	*/
	private Date createDate ; 


	public void setId(Long id){
		this.id = id;
	} 

	public Long getId(){
		return id;
	}  

	public void setTaskId(Long taskId){
		this.taskId = taskId;
	} 

	public Long getTaskId(){
		return taskId;
	}  

	public void setLogLevel(Byte logLevel){
		this.logLevel = logLevel;
	} 

	public Byte getLogLevel(){
		return logLevel;
	}  

	public void setMsg(String msg){
		this.msg = msg;
	} 

	public String getMsg(){
		return msg;
	}  

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	} 

	public Date getCreateDate(){
		return createDate;
	}  

}