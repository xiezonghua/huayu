package com.huayu.digxy.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.huayu.digxy.bo.Project;
import com.huayu.digxy.bo.ProjectMember;
import com.huayu.digxy.constant.ProjectMemberRole;
import com.huayu.digxy.service.NotificationService;
import com.huayu.digxy.service.ProjectMemberService;
import com.huayu.digxy.service.ProjectService;
import com.huayu.web.platform.action.BasicModelAction;

@Namespace("/")
public class PageAction extends BasicModelAction{

	private static final long serialVersionUID = 1L;
	
	private Long id ;
	
	@Resource
	private ProjectService service ;
	
	@Resource
	private ProjectMemberService memberService ;
	
	@Resource
	private NotificationService notifyService ;
	
	
	@Action(value="index" , results = { @Result(type = "velocity", location = "/vm/project/project_index.vm") })
	public String pIndex(){
		Map<String ,Object> queryParam = new HashMap<String, Object>();
		queryParam.put("orderBy" , "id");
		queryParam.put("orderType" , "desc");
		
		List<Project> projects = service.query(queryParam);
		if(projects.size() > 0){
			id = projects.get(0).getId();
		}else{
			return setStautsInfo("暂时不存在项目,请发起!");
		}
		
		Project project = service.queryProjectDetail(id);
		if( project == null){
			return setStautsInfo("项目不存在");
		}
		
		List<ProjectMember> boneMembers = memberService.queryMembers(id, ProjectMemberRole.BONE_MEMBER);
			
		
		
		Map<String , Object> result = new HashMap<String , Object>();
		result.put("pInfo", project);
		result.put("boneMembers", boneMembers);
		result.put("notifyMain", notifyService.queryMainNotice(id));
		setData(result);
		return SUCCESS;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
}
