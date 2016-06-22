package com.huayu.digxy.bo.base;

import java.util.Date;

public class ProjectMemberBase {
	/**
	*  KEY ID
	*/
	private Long id ; 

	/**
	*  prorject id
	*/
	private Long projectId ; 

	/**
	*  sub project id
	*/
	private Long subProjectId ; 

	/**
	*  user id
	*/
	private Long userId ; 

	/**
	*  member name
	*/
	private String name ; 

	/**
	*  member image
	*/
	private String imgSrc ; 

	/**
	*  education
	*/
	private String education ; 

	/**
	*  what your working into
	*/
	private String researchFields ; 

	/**
	*  project responsibility
	*/
	private String responsibility ; 

	/**
	*  project experience
	*/
	private String projectExperience ; 

	/**
	*  company or shool
	*/
	private String job ; 

	/**
	*  title for your work
	*/
	private String jobTitle ; 

	/**
	*  plan or other
	*/
	private String description ; 

	/**
	*  create date time
	*/
	private Date applyDate ; 

	/**
	*  0 : creator , 1: bone member , 2: attender , attender role and bone member
	*/
	private Byte role ; 

	/**
	*  state , 1 apply , 2 : working 3:completed 
	*/
	private Byte state ; 

	/**
	*  failure inforamtion
	*/
	private String msg ; 


	public void setId(Long id){
		this.id = id;
	} 

	public Long getId(){
		return id;
	}  

	public void setProjectId(Long projectId){
		this.projectId = projectId;
	} 

	public Long getProjectId(){
		return projectId;
	}  

	public void setSubProjectId(Long subProjectId){
		this.subProjectId = subProjectId;
	} 

	public Long getSubProjectId(){
		return subProjectId;
	}  

	public void setUserId(Long userId){
		this.userId = userId;
	} 

	public Long getUserId(){
		return userId;
	}  

	public void setName(String name){
		this.name = name;
	} 

	public String getName(){
		return name;
	}  

	public void setImgSrc(String imgSrc){
		this.imgSrc = imgSrc;
	} 

	public String getImgSrc(){
		return imgSrc;
	}  

	public void setEducation(String education){
		this.education = education;
	} 

	public String getEducation(){
		return education;
	}  

	public void setResearchFields(String researchFields){
		this.researchFields = researchFields;
	} 

	public String getResearchFields(){
		return researchFields;
	}  

	public void setResponsibility(String responsibility){
		this.responsibility = responsibility;
	} 

	public String getResponsibility(){
		return responsibility;
	}  

	public void setProjectExperience(String projectExperience){
		this.projectExperience = projectExperience;
	} 

	public String getProjectExperience(){
		return projectExperience;
	}  

	public void setJob(String job){
		this.job = job;
	} 

	public String getJob(){
		return job;
	}  

	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	} 

	public String getJobTitle(){
		return jobTitle;
	}  

	public void setDescription(String description){
		this.description = description;
	} 

	public String getDescription(){
		return description;
	}  

	public void setApplyDate(Date applyDate){
		this.applyDate = applyDate;
	} 

	public Date getApplyDate(){
		return applyDate;
	}  

	public void setRole(Byte role){
		this.role = role;
	} 

	public Byte getRole(){
		return role;
	}  

	public void setState(Byte state){
		this.state = state;
	} 

	public Byte getState(){
		return state;
	}  

	public void setMsg(String msg){
		this.msg = msg;
	} 

	public String getMsg(){
		return msg;
	}  

}