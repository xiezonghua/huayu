package com.huayu.digxy.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.digxy.bo.ProjectResource;
import com.huayu.digxy.bo.SubProject;
import com.huayu.digxy.service.SubProjectService;
import com.huayu.web.platform.action.BasicModelAction;

@Namespace("/sp")
public class SubProjectAction extends BasicModelAction{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(SubProjectAction.class);
	
	private SubProject subProjectModel ;
	
	private List<ProjectResource> courseResources ;
	
	@Resource
	private SubProjectService service;
	
	@Action(value="add" , results=@Result(type="json" , name=SUCCESS))
	public String add(){
		logger.info("Sub Project adding");
		subProjectModel.setCreateTime(new Date());
		subProjectModel.setCreatorId(getUserId());
		
		service.addSubProject(subProjectModel , courseResources);
		return SUCCESS;
	}

	public SubProject getSubProjectModel() {
		return subProjectModel;
	}

	public void setSubProjectModel(SubProject subProjectModel) {
		this.subProjectModel = subProjectModel;
	}

	public List<ProjectResource> getCourseResources() {
		return courseResources;
	}

	public void setCourseResources(List<ProjectResource> courseResources) {
		this.courseResources = courseResources;
	}


}
