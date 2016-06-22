package com.huayu.digxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.digxy.bo.ProjectMember;
import com.huayu.digxy.constant.ProjectMemberRole;
import com.huayu.digxy.dao.ProjectMemberDao;
import com.huayu.digxy.service.ProjectMemberService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

@Service("projectMemberService")
public class ProjectMemberServiceImpl extends AbstractBasicService<ProjectMember , Long> implements ProjectMemberService{

	private final static Logger logger = LoggerFactory.getLogger(ProjectMemberServiceImpl.class.getCanonicalName());

	@Resource
	private ProjectMemberDao projectMemberDao ;

	@Override
	public BaseDaoMapper<ProjectMember, Long> getDao() {		
		return projectMemberDao;
	}

	@Override
	public List<ProjectMember> queryMembers(Long projectId , ProjectMemberRole memberRoleType) {
		logger.info("query members({}) of project(id={})" , memberRoleType , projectId);
		ProjectMember recorder = new ProjectMember();
		recorder.setProjectId(projectId);
		recorder.setRole(memberRoleType.getValue());
		return projectMemberDao.select(recorder);
	}

}
