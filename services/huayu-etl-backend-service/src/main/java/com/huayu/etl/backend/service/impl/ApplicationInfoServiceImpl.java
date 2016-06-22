package com.huayu.etl.backend.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.ApplicationInfo;
import com.huayu.etl.backend.dao.ApplicationInfoDao;
import com.huayu.etl.backend.service.ApplicationInfoService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

/**
 * 
 * @author xzh
 *
 */
@Service("applicationInfoService")
public class ApplicationInfoServiceImpl extends AbstractBasicService<ApplicationInfo , Long> implements ApplicationInfoService{

	private final static Logger logger = LoggerFactory.getLogger(ApplicationInfoServiceImpl.class.getCanonicalName());

	@Resource
	private ApplicationInfoDao applicationInfoDao ;

	@Override
	public BaseDaoMapper<ApplicationInfo, Long> getDao() {		
		return applicationInfoDao;
	}
}
