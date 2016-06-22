package com.huayu.digxy.dao;

import java.util.List;
import java.util.Map;

import com.huayu.digxy.bo.SystemUser;
import com.huayu.platform.dao.BaseDaoMapper;

public interface SystemUserDao extends BaseDaoMapper<SystemUser, Long> {
	
	List<SystemUser> selectLoginUser(Map<String, Object> query);
}