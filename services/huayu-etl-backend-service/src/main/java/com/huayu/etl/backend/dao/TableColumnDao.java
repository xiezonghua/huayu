package com.huayu.etl.backend.dao;

import java.util.List;
import java.util.Map;

import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.platform.dao.BaseDaoMapper;

/**
 * 
 * @author xzh
 *
 */
public interface TableColumnDao extends BaseDaoMapper<TableColumn, Long> {
	List<TableColumn> selectCloumns(Map<String, Object> query);
}