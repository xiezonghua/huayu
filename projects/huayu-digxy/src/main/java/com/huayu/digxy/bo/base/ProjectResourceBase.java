package com.huayu.digxy.bo.base;

import java.util.Date;

public class ProjectResourceBase {
	/**
	*  key id
	*/
	private Long id ; 

	/**
	*  resource name
	*/
	private String name ; 

	/**
	*  resource description
	*/
	private String description ; 

	/**
	*  file name , including the doc , swf ,image
	*/
	private String docName ; 

	/**
	*  where the file store .
	*/
	private String docFloder ; 

	/**
	*  level
	*/
	private Byte star ;

	/**
	*  upload time
	*/
	private Date uploadDate ; 

	/**
	*  who upload
	*/
	private Long uploader ; 

	/**
	*  who belong to 
	*/
	private Long projectId ; 

	/**
	*  who belong to sub_project
	*/
	private Long subProjectId ; 

	/**
	*  some tag  
	*/
	private String label ; 

	/**
	*  click it times
	*/
	private Integer clickTimes ; 

	/**
	*  download times
	*/
	private Integer downloadTimes ; 

	/**
	*  maybe need to audit
	*/
	private Byte status ;

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

	public void setDescription(String description){
		this.description = description;
	} 

	public String getDescription(){
		return description;
	}  

	public void setDocName(String docName){
		this.docName = docName;
	} 

	public String getDocName(){
		return docName;
	}  

	public void setDocFloder(String docFloder){
		this.docFloder = docFloder;
	} 

	public String getDocFloder(){
		return docFloder;
	}  

	public void setStar(Byte star){
		this.star = star;
	} 

	public Byte getStar(){
		return star;
	}  

	public void setUploadDate(Date uploadDate){
		this.uploadDate = uploadDate;
	} 

	public Date getUploadDate(){
		return uploadDate;
	}  

	public void setUploader(Long uploader){
		this.uploader = uploader;
	} 

	public Long getUploader(){
		return uploader;
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

	public void setLabel(String label){
		this.label = label;
	} 

	public String getLabel(){
		return label;
	}  

	public void setClickTimes(Integer clickTimes){
		this.clickTimes = clickTimes;
	} 

	public Integer getClickTimes(){
		return clickTimes;
	}  

	public void setDownloadTimes(Integer downloadTimes){
		this.downloadTimes = downloadTimes;
	} 

	public Integer getDownloadTimes(){
		return downloadTimes;
	}  

	public void setStatus(Byte status){
		this.status = status;
	} 

	public Byte getStatus(){
		return status;
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