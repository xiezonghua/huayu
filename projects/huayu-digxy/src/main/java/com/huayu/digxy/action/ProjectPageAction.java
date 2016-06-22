package com.huayu.digxy.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.digxy.bo.Notification;
import com.huayu.digxy.bo.Project;
import com.huayu.digxy.bo.ProjectMember;
import com.huayu.digxy.constant.DictConst;
import com.huayu.digxy.constant.ProjectMemberRole;
import com.huayu.digxy.service.NotificationService;
import com.huayu.digxy.service.ProjectMemberService;
import com.huayu.digxy.service.ProjectService;
import com.huayu.digxy.utils.DictionaryHelper;
import com.huayu.web.platform.action.BasicModelAction;

@Namespace("/p")
public class ProjectPageAction extends BasicModelAction{
	private static final long serialVersionUID = 1L;
	
	private Long id ;

	private final static Logger logger = LoggerFactory.getLogger(ProjectPageAction.class.getCanonicalName());

	@Resource
	private ProjectService service ;
	
	@Resource
	private ProjectMemberService memberService ;
	
	@Resource
	private NotificationService notifyService ;
	
	@Action(value="new" , results = { @Result(type = "velocity", location = "/vm/project/project_new.vm") })
	public String create(){
		logger.info("welcome into the project create page");
		return SUCCESS ; 
	}

	@Action(value="m" , results={@Result( name=SUCCESS , type="velocity"  , location="/vm/project/project_m.vm" )})
	public String manager(){
		Map<String, Object> query = new HashMap<String, Object>();
		query.putAll(getPagination().toMap());
		
		long count = service.count(query);
		List<Project> result = new ArrayList<Project>();
		if(count > 0 ){
			result = service.query(query);
		}
		
		query.clear();
		query.put("count", count);
		query.put("list", result);
		query.put("projectStatus",  DictionaryHelper.getDictionaryByTypeCode(DictConst.PROJECT_STATUS));
		setData(query);
		return SUCCESS;
	}
	
	@Action(value="m_detail" , results={@Result(name=SUCCESS , type="velocity" , location="/vm/project/project_detail_index.vm")})
	public String detailM(){
		return queryProjectDetail(id);
	}
	
	private String queryProjectDetail(long id){
		Project project = service.queryProjectDetail(id);
		if( project == null){
			return setStautsInfo("项目不存在");
		}
		
		List<ProjectMember> boneMembers = memberService.queryMembers(id, ProjectMemberRole.BONE_MEMBER);
			
		List<Notification> notifyList = notifyService.query(id);
		
		
		Map<String , Object> result = new HashMap<String , Object>();
		result.put("pInfo", project);
		result.put("boneMembers", boneMembers);
		result.put("notifyList", notifyList);
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
