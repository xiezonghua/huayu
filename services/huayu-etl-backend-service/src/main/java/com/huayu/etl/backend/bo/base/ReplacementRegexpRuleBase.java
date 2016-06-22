package com.huayu.etl.backend.bo.base;

import java.util.Date;

public class ReplacementRegexpRuleBase {
	/**
	*  id
	*/
	private Long id ; 

	/**
	*  regexp rule name
	*/
	private String name ; 

	/**
	*  regexpReplacement
	*/
	private String regexpReplacement ; 

	/**
	*  replacementContent
	*/
	private String replacementContent ; 

	/**
	*  description
	*/
	private String description ; 

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

	public void setName(String name){
		this.name = name;
	} 

	public String getName(){
		return name;
	}  

	public void setRegexpReplacement(String regexpReplacement){
		this.regexpReplacement = regexpReplacement;
	} 

	public String getRegexpReplacement(){
		return regexpReplacement;
	}  

	public void setReplacementContent(String replacementContent){
		this.replacementContent = replacementContent;
	} 

	public String getReplacementContent(){
		return replacementContent;
	}  

	public void setDescription(String description){
		this.description = description;
	} 

	public String getDescription(){
		return description;
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