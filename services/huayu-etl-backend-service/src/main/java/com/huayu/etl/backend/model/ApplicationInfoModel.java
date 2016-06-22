package com.huayu.etl.backend.model;

import com.huayu.etl.backend.bo.ApplicationInfo;

/**
 * 
 * @author xzh
 *
 */
public class ApplicationInfoModel extends ApplicationInfo{
	private AppTableModel[] tables ;

	public AppTableModel[] getTables() {
		return tables;
	}

	public void setTables(AppTableModel[] tables) {
		this.tables = tables;
	}
}
