package com.huayu.etl.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.huayu.etl.backend.bo.AppTable;
import com.huayu.etl.backend.bo.ApplicationInfo;
import com.huayu.etl.backend.bo.ExtractRecorder;
import com.huayu.etl.backend.bo.ReplacementRegexpRule;
import com.huayu.etl.backend.bo.TableColumn;
import com.huayu.etl.backend.model.AppTableModel;
import com.huayu.etl.backend.model.ApplicationInfoModel;
import com.huayu.etl.backend.service.AppConfigurationService;
import com.huayu.etl.backend.service.AppTableService;
import com.huayu.etl.backend.service.ApplicationInfoService;
import com.huayu.etl.backend.service.ExtractRecorderService;
import com.huayu.etl.backend.service.ReplacementRegexpRuleService;
import com.huayu.etl.backend.service.TableColumnService;

/**
 * 
 * @author xzh
 *
 */

@Service("appConfigurationService")
public class AppConfigurationServiceImpl implements AppConfigurationService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AppConfigurationServiceImpl.class);
	
	@Resource
	private ApplicationInfoService appInfoService;
	
	@Resource
	private AppTableService appTableService ;
	
	@Resource
	private TableColumnService tableColumnService ;
	
	@Resource
	private ExtractRecorderService extractRecorderService ;
	
	@Resource
	private ReplacementRegexpRuleService replacementRegexpRuleService;
	
	public void addExtactRecorder(ExtractRecorder recorder) {
		LOGGER.info("add extractRecorder ....");
		recorder.setCreateDate(new Date());
		extractRecorderService.addSelective(recorder);
	}

	public List<AppTable> queryTables(Map<String, Object> query) {
		LOGGER.info("query tables .... {}"  , query);
		return appTableService.query(query);
	}

	public List<AppTable> queryTables(String appUuid) {
		LOGGER.info("query tables .by uuid ... {}"  , appUuid);
		List<AppTable> appTables = new ArrayList<AppTable>();
		
		ApplicationInfo appInfo = queryAppInfo(appUuid);
		if( appInfo != null ){
			AppTable appTable = new AppTable();
			appTable.setAppId(appInfo.getId());		
			appTables = appTableService.query(appTable);
		}
	
		return appTables;
	}

	public List<TableColumn> queryTableColumns(String appUuid, String tableName) {
		LOGGER.info("query tables .by uuid ... {}"  , appUuid);
		List<TableColumn> tableColumns = new ArrayList<TableColumn>();
		
		ApplicationInfo appInfo = queryAppInfo(appUuid);
		if( appInfo != null ){
			tableColumns = tableColumnService.queryCloumns(appInfo.getId(), tableName);
		}
		
		return tableColumns;
		
	}
	
	public ApplicationInfo queryAppInfo(String appUuid){
		ApplicationInfo app = new ApplicationInfo();
		app.setAppUuid(appUuid);
//		Map<String ,Object> query = new HashMap<String ,Object>();
//		query.put("appUuid", appUuid);
		List<ApplicationInfo> appInfos = appInfoService.query(app);
		if(appInfos.size() == 1){
			return appInfos.get(0);
		}
		LOGGER.warn("the application({}) don't regist ." ,appUuid);
		return null ;
	}
		
	public List<TableColumn> queryTableColumns(Long tableId) {
		TableColumn record = new TableColumn();
		record.setTableId(tableId);
		return tableColumnService.query(record);
	}

	public ApplicationInfoModel queryAppAllInfo(String appUuid) {
		ApplicationInfo appInfo = queryAppInfo(appUuid);
		if(appInfo != null){
			AppTable appTable = new AppTable();
			appTable.setAppId(appInfo.getId());		
//			Map<String ,Object> query = new HashMap<String ,Object>();
//			query.put("appId", appInfo.getId());
			List<AppTable> appTables = appTableService.query(appTable);
			List<TableColumn> tableColumns =tableColumnService.queryCloumns(appInfo.getId(), null);
			return loadAppInfo(appInfo, appTables, tableColumns);
		}
		
		LOGGER.warn("the application({}) don't regist ." ,appUuid);
		return null ;
	}
	
	private ApplicationInfoModel loadAppInfo(ApplicationInfo appInfo , List<AppTable> appTables, List<TableColumn> tableColumns ){
		ApplicationInfoModel model = new ApplicationInfoModel();
	
		BeanUtils.copyProperties(appInfo, model , new String[]{"introduce" , "create_user_id", "createDate" , "updateUserId" , "updateDate" });
		AppTableModel[] tableModelArray = new AppTableModel[appTables.size()];
		
		
		int i = 0 ;
		Long tableId = 0l ;
		AppTableModel tableModel = null ;
		
		int fromIndex = 0 ;
		int toIndex = 0 ;
		
		for( TableColumn columnEle : tableColumns){
			columnEle.setColumnComment(null);
			if(!tableId.equals(columnEle.getTableId())){
				for(AppTable table : appTables){
					if(table.getId().equals(columnEle.getTableId())){
						//the previous model insert columns value .
						if(tableId > 0 ){
							tableModel.setColumns(tableColumns.subList(fromIndex, toIndex).toArray(new TableColumn[0]));
							fromIndex = toIndex;
						}
						
						//set the current model
						tableModel = new AppTableModel();
						BeanUtils.copyProperties(table, tableModel , new String[]{"introduce" , "create_user_id", "createDate" , "updateUserId" , "updateDate" });
						tableModelArray[i++] = tableModel ;		
						tableId = tableModel.getId();
						break ;
					}
				
				}
			}
			toIndex++;
		}
		//insert the columns value for the last tableModel
		tableModel.setColumns(tableColumns.subList(fromIndex, toIndex).toArray(new TableColumn[0]));
		
		model.setTables(tableModelArray);
		
		return model ;
	}
	
	
	private AppTableModel loadTableInfo(AppTable tableInfo , List<TableColumn> tableColumns){
		AppTableModel tableModel  = new AppTableModel();
		BeanUtils.copyProperties(tableInfo, tableModel);
		tableModel.setColumns(tableColumns.toArray(new TableColumn[0]));
		return tableModel;
	}


	public List<ExtractRecorder> queryLastTableRecorders(String appUuid) {
		ApplicationInfo appInfo = queryAppInfo(appUuid);
		if(appInfo != null ){
			Map<String ,Object> query = new HashMap<String, Object>();
			query.put("appId", appInfo.getId());
			return extractRecorderService.queryLastRecorders(query);
		}
	
		return new ArrayList<ExtractRecorder>();
	}

	public ExtractRecorder queryLastTableRecoder(String appUuid, String tableName) {
		ApplicationInfo appInfo = queryAppInfo(appUuid);
		if(appInfo != null ){
			Map<String ,Object> query = new HashMap<String, Object>();
			query.put("appId", appInfo.getId());
			query.put("tableName", tableName);
			query.put("orderBy", "table_id");
			query.put("beginNum", 0);
			query.put("offset", 1);
			List<ExtractRecorder> recorders = extractRecorderService.queryRecorders(query);
			if(recorders.size() > 0){
				return recorders.get(0);
			}
		}
		
		return null;
	}

	public void addBatchExtactRecorder(List<ExtractRecorder> recorders) {
		extractRecorderService.addBatchSelective(recorders);
	}

	public List<ReplacementRegexpRule> queryRegexpRuleList() {
		return replacementRegexpRuleService.queryAll();
	}
	
}
