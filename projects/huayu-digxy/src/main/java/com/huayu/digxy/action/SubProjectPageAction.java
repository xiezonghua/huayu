package com.huayu.digxy.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.action.BasicModelAction;

@Namespace("/sp")
public class SubProjectPageAction extends BasicModelAction{
	
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(SubProjectPageAction.class);

	private Long id ;
	
	private String name ;
	
	@Action(value="new" , results = { @Result(type = "velocity", location = "/vm/project/sub_project_new.vm") })
	public String create(){
		logger.info("welcome into the project create page");
		//the id is parent Id 
		Map<String , Object> request  = new HashMap<String,Object>();
		request.put("id" , id);
		request.put("name", name);
		setData(request);
		return SUCCESS ; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
