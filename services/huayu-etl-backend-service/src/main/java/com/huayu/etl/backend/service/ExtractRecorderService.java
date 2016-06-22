package com.huayu.etl.backend.service;

import java.util.List;
import java.util.Map;

import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.platform.service.BasicService;

/**
 * 
 * @author xzh
 *
 */
public interface ExtractRecorderService extends BasicService<ExtractRecorder, Long> {
	List<ExtractRecorder> queryRecorders(Map<String , Object> query);
	
	List<ExtractRecorder> queryLastRecorders(Map<String ,Object> query);
	
	int addBatchSelective(List<ExtractRecorder> recorderList);
}