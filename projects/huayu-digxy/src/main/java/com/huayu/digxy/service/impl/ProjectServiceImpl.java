package com.huayu.digxy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.digxy.bo.Project;
import com.huayu.digxy.bo.ProjectMember;
import com.huayu.digxy.constant.ProjectMemberRole;
import com.huayu.digxy.dao.ProjectDao;
import com.huayu.digxy.service.ProjectMemberService;
import com.huayu.digxy.service.ProjectService;
import com.huayu.digxy.utils.FileInfoHelper;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

@Service("projectService")
public class ProjectServiceImpl extends AbstractBasicService<Project , Long> implements ProjectService{

	private final static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class.getCanonicalName());
	
	@Resource
	private ProjectDao projectDao ;
	
	@Resource
	private ProjectMemberService memberService;

	@Override
	public BaseDaoMapper<Project, Long> getDao() {		
		return projectDao;
	}

	@Override
	public void addProject(Project model , ProjectMember creatorModel , List<ProjectMember> boneMembers) {
		logger.info("adding project ......");
		String uuidName = UUID.randomUUID().toString();
		String imageFile = model.getFlagImage();
		String flagImgFile = uuidName + "." + FilenameUtils.getExtension(model.getFlagImage());
		model.setFlagImage(flagImgFile.toLowerCase());
		
		model.setStatus((byte)1);
		projectDao.insertSelective(model);
		
		FileInfoHelper.moveImgFile(imageFile,  model.getFlagImage() , model.getId());
		
		addMember(model.getId() , creatorModel, ProjectMemberRole.CREATOR);
		
		for(ProjectMember member : boneMembers){
			addMember(model.getId(), member , ProjectMemberRole.BONE_MEMBER);
		}
	}
	
	private void addMember(Long projectId , ProjectMember member , ProjectMemberRole memberRole){
		member.setProjectId(projectId );
		String memberImg = member.getImgSrc();
		member.setImgSrc(System.currentTimeMillis() + memberImg );
		member.setRole(memberRole.getValue());
		memberService.addSelective(member);

		FileInfoHelper.moveImgFile(memberImg , member.getImgSrc() , projectId) ;
	}
	
	
	
	@Override
	public List<Project> queryProjectDetailList(Map<String, Object> query) {
		return projectDao.selectProjectDetailList(query);
	}

	@Override
	public Project queryProjectDetail(long id) {
		Map<String , Object> query = new HashMap<String , Object>();
		query.put("id", id);
		List<Project> projects = projectDao.selectProjectDetailList(query);
		if(projects.size() == 1){
			return projects.get(0);
		}else{
			logger.info("The preject(id = {}) is exception." , id);
		}
		return null;
	}
}
