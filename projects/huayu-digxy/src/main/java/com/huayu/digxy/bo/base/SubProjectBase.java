package com.huayu.digxy.bo.base;

import java.util.Date;

public class SubProjectBase {
	/**
	*  key id
	*/
	private Long id ; 

	/**
	*  sub project name
	*/
	private String name ; 

	/**
	*  flagImage
	*/
	private String flagImage ; 

	/**
	*  description
	*/
	private String description ; 

	/**
	*  we need to achieve
	*/
	private String goal ; 

	/**
	*  parent project_id
	*/
	private Long projectId ; 

	/**
	*  who create it
	*/
	private Long creatorId ; 

	/**
	*  when creating
	*/
	private Date createTime ; 

	/**
	*  who update it
	*/
	private Long modifierId ; 

	/**
	*  when modifying
	*/
	private Date modifyTime ; 


	public void setId(Long id){
		this.id = id;
	} 

	public Long getId(){
		return id;
	}  

	public void setName(String name){
		this.name = name;
	} 

	public String getName(){
		return name;
	}  

	public void setFlagImage(String flagImage){
		this.flagImage = flagImage;
	} 

	public String getFlagImage(){
		return flagImage;
	}  

	public void setDescription(String description){
		this.description = description;
	} 

	public String getDescription(){
		return description;
	}  

	public void setGoal(String goal){
		this.goal = goal;
	} 

	public String getGoal(){
		return goal;
	}  

	public void setProjectId(Long projectId){
		this.projectId = projectId;
	} 

	public Long getProjectId(){
		return projectId;
	}  

	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	} 

	public Long getCreatorId(){
		return creatorId;
	}  

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	} 

	public Date getCreateTime(){
		return createTime;
	}  

	public void setModifierId(Long modifierId){
		this.modifierId = modifierId;
	} 

	public Long getModifierId(){
		return modifierId;
	}  

	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	} 

	public Date getModifyTime(){
		return modifyTime;
	}  

}