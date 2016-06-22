package com.huayu.digxy.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.huayu.digxy.bo.ProjectResource;
import com.huayu.digxy.dao.ProjectResourceDao;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.digxy.service.ProjectResourceService;
import com.huayu.platform.service.AbstractBasicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("projectResourceService")
public class ProjectResourceServiceImpl extends AbstractBasicService<ProjectResource , Long> implements ProjectResourceService{

	private final static Logger logger = LoggerFactory.getLogger(ProjectResourceServiceImpl.class.getCanonicalName());

	@Resource
	private ProjectResourceDao projectResourceDao ;

	@Override
	public BaseDaoMapper<ProjectResource, Long> getDao() {		
		return projectResourceDao;
	}
}
