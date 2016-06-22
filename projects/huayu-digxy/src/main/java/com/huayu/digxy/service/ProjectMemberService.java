package com.huayu.digxy.service;

import java.util.List;

import com.huayu.digxy.bo.ProjectMember;
import com.huayu.digxy.constant.ProjectMemberRole;
import com.huayu.platform.service.BasicService;

public interface ProjectMemberService extends BasicService<ProjectMember, Long> {
	List<ProjectMember> queryMembers(Long projectId , ProjectMemberRole memberRoleType);
}