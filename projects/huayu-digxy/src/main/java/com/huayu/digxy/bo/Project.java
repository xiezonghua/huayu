package com.huayu.digxy.bo;
import com.huayu.digxy.bo.base.ProjectBase;

/**
* 
*
*/
public class Project extends ProjectBase{
	ProjectMember creatorMember;

	public ProjectMember getCreatorMember() {
		return creatorMember;
	}

	public void setCreatorMember(ProjectMember creatorMember) {
		this.creatorMember = creatorMember;
	} 
	
	
}