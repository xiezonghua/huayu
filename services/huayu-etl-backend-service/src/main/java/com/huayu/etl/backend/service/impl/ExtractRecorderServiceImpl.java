package com.huayu.etl.backend.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.etl.backend.dao.ExtractRecorderDao;
import com.huayu.etl.backend.service.ExtractRecorderService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

/**
 * 
 * @author xzh
 *
 */
@Service("extractRecorderService")
public class ExtractRecorderServiceImpl extends AbstractBasicService<ExtractRecorder , Long> implements ExtractRecorderService{

	private final static Logger logger = LoggerFactory.getLogger(ExtractRecorderServiceImpl.class.getCanonicalName());

	@Resource
	private ExtractRecorderDao extractRecorderDao ;

	@Override
	public BaseDaoMapper<ExtractRecorder, Long> getDao() {		
		return extractRecorderDao;
	}

	public List<ExtractRecorder> queryRecorders(Map<String, Object> query) {
		Object obj = query.get("ids");
		if(obj != null){
			if(!(obj instanceof Collection)){
				logger.error("The ids data type is bad!");
				return new ArrayList<ExtractRecorder>();
			}
		}
		return extractRecorderDao.selectRecorders(query);
	}

	public List<ExtractRecorder> queryLastRecorders(Map<String, Object> query) {
		List<Long> maxIds = extractRecorderDao.selectMaxRecorderIds(query);
		Map<String ,Object> localQuery = new HashMap<String, Object>();
		localQuery.put("ids", maxIds);
		
		return extractRecorderDao.selectRecorders(localQuery);
	}

	public int addBatchSelective(List<ExtractRecorder> recorderList) {
		if(recorderList == null || recorderList.isEmpty()){
			return 0;
		}
		
		return extractRecorderDao.insertBatchSelective(recorderList);
	}
}
