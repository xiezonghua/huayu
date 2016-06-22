package com.huayu.digxy.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.digxy.bo.ProjectResource;
import com.huayu.digxy.bo.SubProject;
import com.huayu.digxy.dao.ProjectResourceDao;
import com.huayu.digxy.dao.SubProjectDao;
import com.huayu.digxy.service.SubProjectService;
import com.huayu.digxy.utils.FileInfoHelper;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.exception.ServiceRuntimeException;
import com.huayu.platform.service.AbstractBasicService;
import com.huayu.platform.utils.file.FileTools;

@Service("subProjectService")
public class SubProjectServiceImpl extends AbstractBasicService<SubProject , Long> implements SubProjectService{

	private final static Logger logger = LoggerFactory.getLogger(SubProjectServiceImpl.class.getCanonicalName());

	@Resource
	private SubProjectDao subProjectDao ;
	
	@Resource
	private ProjectResourceDao resourceDao ;

	@Override
	public BaseDaoMapper<SubProject, Long> getDao() {		
		return subProjectDao;
	}

	@Override
	public void addSubProject(SubProject model,
			List<ProjectResource> couseResources) {
		logger.info("sub project add ...");
		String uuidName = UUID.randomUUID().toString();
		String imageFileSrc = model.getFlagImage();
		String flagImgFile = uuidName + "." + FilenameUtils.getExtension(model.getFlagImage());
		model.setFlagImage(flagImgFile.toLowerCase());
		subProjectDao.insertSelective(model);
		
		FileInfoHelper.moveImgFile(imageFileSrc,  model.getFlagImage() , model.getId());
		
		for(ProjectResource resEle : couseResources){
			resEle.setSubProjectId(model.getId());
			resEle.setProjectId(model.getProjectId());
			resEle.setClickTimes(0);
			resEle.setCreateTime(new Date());
			resEle.setCreatorId(model.getCreatorId());
			
			uuidName = UUID.randomUUID().toString();
			String srcDocName = resEle.getDocName() ; 
			String docNewName = uuidName + "." + FilenameUtils.getExtension(resEle.getDocName());
			resEle.setDocName(docNewName.toLowerCase());
			resEle.setDocFloder(FileInfoHelper.getProjectPath(model.getProjectId(), model.getId()));
			FileInfoHelper.moveSubFile(srcDocName , docNewName, model.getProjectId(), model.getId());
			resourceDao.insertSelective(resEle);
		}
	}
	
}
