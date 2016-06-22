package com.huayu.digxy.service;

import java.util.List;
import java.util.Map;

import com.huayu.digxy.bo.Project;
import com.huayu.digxy.bo.ProjectMember;
import com.huayu.platform.service.BasicService;

public interface ProjectService extends BasicService<Project, Long> {
	void addProject(Project model , ProjectMember creater , List<ProjectMember> boneMembers);
	
	List<Project> queryProjectDetailList(Map<String , Object> query);
	
	Project queryProjectDetail(long id);
}