package com.toread.etl.configure.serivce;

import java.util.List;

import com.toread.etl.configure.bo.TableColumnInfo;
import com.toread.etl.configure.bo.TableInfo;

public interface JdbcClient {
	
	List<TableInfo> queryTables(String dbName );
	
	List<TableColumnInfo> queryTableColumns(String dbName , String tableName);
	
	void close();
}
