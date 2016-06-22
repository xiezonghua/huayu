package com.huayu.etl.backend.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.ReplacementRegexpRule;
import com.huayu.etl.backend.dao.ReplacementRegexpRuleDao;
import com.huayu.etl.backend.service.ReplacementRegexpRuleService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

/**
 * 
 * @author xzh
 *
 */
@Service("replacementRegexpRuleService")
public class ReplacementRegexpRuleServiceImpl extends AbstractBasicService<ReplacementRegexpRule , Long> implements ReplacementRegexpRuleService{

	private final static Logger logger = LoggerFactory.getLogger(ReplacementRegexpRuleServiceImpl.class.getCanonicalName());

	@Resource
	private ReplacementRegexpRuleDao replacementRegexpRuleDao ;

	@Override
	public BaseDaoMapper<ReplacementRegexpRule, Long> getDao() {		
		return replacementRegexpRuleDao;
	}
}
