package com.huayu.etl.backend.dao;

import java.util.List;
import java.util.Map;

import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.platform.dao.BaseDaoMapper;

/**
 * 
 * @author xzh
 *
 */
public interface ExtractRecorderDao extends BaseDaoMapper<ExtractRecorder, Long> {
	
	List<ExtractRecorder> selectRecorders(Map<String , Object> query);
	
	List<Long> selectMaxRecorderIds(Map<String ,Object> query);
	
	int insertBatchSelective(List<ExtractRecorder> recorderList);
}
