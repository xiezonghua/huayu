package com.huayu.digxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.huayu.digxy.bo.SystemDictionary;
import com.huayu.digxy.dao.SystemDictionaryDao;
import com.huayu.digxy.service.SystemDictionaryService;
import com.huayu.digxy.utils.DictionaryHelper;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

@Service("systemDictionaryService")
public class SystemDictionaryServiceImpl extends AbstractBasicService<SystemDictionary , Long> implements SystemDictionaryService , InitializingBean{

	private final static Logger logger = LoggerFactory.getLogger(SystemDictionaryServiceImpl.class.getCanonicalName());

	@Resource
	private SystemDictionaryDao systemDictionaryDao ;

	@Override
	public BaseDaoMapper<SystemDictionary, Long> getDao() {		
		return systemDictionaryDao;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<SystemDictionary> data = systemDictionaryDao.selectAll();
		DictionaryHelper.loadData(data);
	}
}
