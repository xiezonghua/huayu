package com.huayu.etl.backend.service;

import java.util.List;

import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.platform.service.BasicService;

/**
 * 
 * @author xzh
 *
 */
public interface TableColumnService extends BasicService<TableColumn, Long> {
	
	List<TableColumn> queryCloumns(Long appId , String tableName);
}