package com.huayu.etl.backend.model;

import com.huayu.etl.backend.bo.AppTable;
import com.huayu.etl.backend.bo.TableColumn;

/**
 * 
 * @author xzh
 *
 */
public class AppTableModel extends AppTable {
	private TableColumn[] columns;

	public TableColumn[] getColumns() {
		return columns;
	}

	public void setColumns(TableColumn[] columns) {
		this.columns = columns;
	}

}
