package com.huayu.digxy.dao;

import java.util.List;
import java.util.Map;

import com.huayu.digxy.bo.Project;
import com.huayu.platform.dao.BaseDaoMapper;

public interface ProjectDao extends BaseDaoMapper<Project, Long> {
	
	List<Project> selectProjectDetailList(Map<String , Object> query);
}