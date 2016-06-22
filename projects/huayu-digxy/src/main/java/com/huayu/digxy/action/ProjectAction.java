package com.huayu.digxy.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.digxy.bo.Project;
import com.huayu.digxy.bo.ProjectMember;
import com.huayu.digxy.service.ProjectService;
import com.huayu.web.platform.action.BasicModelAction;

/**
 * @author xzh
 *
 */
@Namespace("/p")
public class ProjectAction extends BasicModelAction{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(ProjectAction.class.getCanonicalName());
	
	private Project projectModel = new Project();
	
	//creator
	private ProjectMember member = new ProjectMember();
	
	private List<ProjectMember> boneMembers = new ArrayList<ProjectMember>();
	
	@Resource
	private ProjectService service ;
	
	@Action(value="add" , results=@Result(type="json" , name=SUCCESS))
	public String add(){
		logger.info("Project adding");
		projectModel.setCreaterDate(new Date());
		projectModel.setCreater(getUserId());
		
		member.setUserId(getUserId());
		member.setRole((byte)0);
	
		service.addProject(projectModel , member , boneMembers);
		return SUCCESS;
	}
	
	@Action(value="audit" , results={@Result( name=SUCCESS , type="json"  )})
	public String audit(){
		if(projectModel.getStatus() < 5){
			projectModel.setCheckDate(new Date());
			projectModel.setChecker(getUserId());
		}
		service.updateByPrimaryKeySelective(projectModel);
		return SUCCESS;
	}

	public Project getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(Project projectModel) {
		this.projectModel = projectModel;
	}

	public ProjectMember getMember() {
		return member;
	}

	public void setMember(ProjectMember member) {
		this.member = member;
	}

	public List<ProjectMember> getBoneMembers() {
		return boneMembers;
	}

	public void setBoneMembers(List<ProjectMember> boneMembers) {
		this.boneMembers = boneMembers;
	}
	
	
}
