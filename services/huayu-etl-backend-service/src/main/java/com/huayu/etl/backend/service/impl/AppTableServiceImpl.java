package com.huayu.etl.backend.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.AppTable;
import com.huayu.etl.backend.dao.AppTableDao;
import com.huayu.etl.backend.service.AppTableService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

/**
 * 
 * @author xzh
 *
 */
@Service("appTableService")
public class AppTableServiceImpl extends AbstractBasicService<AppTable , Long> implements AppTableService{

	private final static Logger logger = LoggerFactory.getLogger(AppTableServiceImpl.class.getCanonicalName());

	@Resource
	private AppTableDao appTableDao ;

	@Override
	public BaseDaoMapper<AppTable, Long> getDao() {		
		return appTableDao;
	}
}
