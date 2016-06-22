package com.huayu.etl.backend.service;

import java.util.List;
import java.util.Map;

import com.huayu.etl.backend.bo.AppTable;
import com.huayu.etl.backend.bo.ApplicationInfo;
import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.etl.backend.bo.ReplacementRegexpRule;
import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.etl.backend.model.ApplicationInfoModel;

/**
 * 
 * @author xzh
 *
 */
public interface AppConfigurationService {

	void addExtactRecorder(ExtractRecorder recorder);

	void addBatchExtactRecorder(List<ExtractRecorder> recorders);

	List<ExtractRecorder> queryLastTableRecorders(String appUuid);

	ExtractRecorder queryLastTableRecoder(String appUuid, String tableId);

	List<AppTable> queryTables(Map<String, Object> query);

	List<AppTable> queryTables(String appUuid);

	List<TableColumn> queryTableColumns(String appUuid, String tableName);

	List<TableColumn> queryTableColumns(Long tableId);

	ApplicationInfo queryAppInfo(String appUuid);

	ApplicationInfoModel queryAppAllInfo(String appUuid);

	List<ReplacementRegexpRule> queryRegexpRuleList();
}