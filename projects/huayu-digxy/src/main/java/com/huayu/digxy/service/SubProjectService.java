package com.huayu.digxy.service;

import java.util.List;

import com.huayu.digxy.bo.ProjectResource;
import com.huayu.digxy.bo.SubProject;
import com.huayu.platform.service.BasicService;

public interface SubProjectService extends BasicService<SubProject, Long> {
	void addSubProject(SubProject project  , List<ProjectResource> couseResources);
}