package com.huayu.etl.backend.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.Task;
import com.huayu.etl.backend.dao.TaskDao;
import com.huayu.etl.backend.service.TaskService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

/**
 * 
 * @author xzh
 *
 */
@Service("taskService")
public class TaskServiceImpl extends AbstractBasicService<Task , Long> implements TaskService{

	private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class.getCanonicalName());

	@Resource
	private TaskDao taskDao ;

	@Override
	public BaseDaoMapper<Task, Long> getDao() {		
		return taskDao;
	}
}
