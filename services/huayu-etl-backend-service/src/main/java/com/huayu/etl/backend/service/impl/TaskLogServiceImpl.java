package com.huayu.etl.backend.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.TaskLog;
import com.huayu.etl.backend.dao.TaskLogDao;
import com.huayu.etl.backend.service.TaskLogService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;


/**
 * 
 * @author xzh
 *
 */
@Service("taskLogService")
public class TaskLogServiceImpl extends AbstractBasicService<TaskLog , Long> implements TaskLogService{

	private final static Logger logger = LoggerFactory.getLogger(TaskLogServiceImpl.class.getCanonicalName());

	@Resource
	private TaskLogDao taskLogDao ;

	@Override
	public BaseDaoMapper<TaskLog, Long> getDao() {		
		return taskLogDao;
	}
}
