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

import com.huayu.digxy.bo.SystemUser;
import com.huayu.digxy.constant.DictConst;
import com.huayu.digxy.service.SystemUserService;
import com.huayu.digxy.utils.DictionaryHelper;
import com.huayu.web.platform.action.BasicModelAction;

@Namespace("/user")
public class UserPageAction extends BasicModelAction{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(UserPageAction.class.getCanonicalName());
	
	@Resource(name="systemUserService")	
	private SystemUserService service;
	
	@Action(value="index" , results={@Result(type="velocity" , location="/vm/user.vm" , name=SUCCESS)})
	public String index(){
		logger.info("Coming the user center!");
		SystemUser user = service.queryByPrimaryKey(getUserId());
		user.setPassword("");
		logger.debug("user information {} " , user);
		setData(user);
		return SUCCESS;
	}	
	
	@Action(value="m" , results={@Result(type="velocity" , location="/vm/user_manager.vm" , name=SUCCESS)})
	public String manage(){
		Map<String, Object> query = new HashMap<String , Object>();
		query.put("beginDate", getBeginDate());
		query.put("endDate", getEndDate());
		Long count =  service.count(query);
		
		List<SystemUser> users = new ArrayList<SystemUser>();
		if(count > 0){
			query.putAll(getPagination().toMap());
			users = service.query(query);
		}
		
		query.clear();
		query.put("count" , count);
		query.put("list", users);
		query.put("userTypes", DictionaryHelper.getDictionaryByTypeCode(DictConst.MEMBER_AUTHORITY_TYPE));
		
		setData(query);
		
		return SUCCESS;
	}
}
