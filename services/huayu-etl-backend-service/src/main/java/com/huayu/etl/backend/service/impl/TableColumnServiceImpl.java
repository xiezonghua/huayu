package com.huayu.etl.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.etl.backend.dao.TableColumnDao;
import com.huayu.etl.backend.service.TableColumnService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.service.AbstractBasicService;

/**
 * 
 * @author xzh
 *
 */
@Service("tableColumnService")
public class TableColumnServiceImpl extends AbstractBasicService<TableColumn , Long> implements TableColumnService{

	private final static Logger logger = LoggerFactory.getLogger(TableColumnServiceImpl.class.getCanonicalName());

	@Resource
	private TableColumnDao tableColumnDao ;

	@Override
	public BaseDaoMapper<TableColumn, Long> getDao() {		
		return tableColumnDao;
	}

	public List<TableColumn> queryCloumns(Long appId, String tableName) {
		Map<String,Object> query = new HashMap<String,Object>(2);
		query.put("tableName", tableName);
		query.put("appId", appId) ;
		query.put("orderBy", "id");
		return tableColumnDao.selectCloumns(query);
	}
	
	
}
