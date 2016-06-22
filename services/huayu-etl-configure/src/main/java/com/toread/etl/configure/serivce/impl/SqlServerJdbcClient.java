package com.toread.etl.configure.serivce.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.toread.etl.backend.bo.AppTable;
import com.toread.etl.backend.bo.TableColumn;
import com.toread.etl.configure.bo.TableColumnInfo;
import com.toread.etl.configure.bo.TableInfo;
import com.toread.etl.configure.constant.SqlConfiguration;
import com.toread.etl.configure.serivce.JdbcClient;
import com.toread.etl.configure.utils.HiveDataTypeConvertor;

public class SqlServerJdbcClient extends AbstractJdbcClient implements JdbcClient{
	private final static Logger logger = LoggerFactory.getLogger(SqlServerJdbcClient.class);
	private Connection conn ;
	private Statement sta;
	
	public List<TableInfo> queryTables(String dbName) {
		List<TableInfo> tableList = new ArrayList<TableInfo>();
		try {
			
			String sql = "SELECT tbs.name name ,cast(ds.value as varchar(5000)) comment  FROM sysobjects tbs LEFT JOIN (select major_id , value from sys.extended_properties ds1 where ds1.minor_id=0) ds  ON ds.major_id=tbs.id where xtype='U'";
//			ResultSet result = sta.executeQuery("select name , '' as comment from sysobjects where xtype='U';");
			ResultSet result = sta.executeQuery(sql);
			tableList = resultSetToBean(result, TableInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}
	
	

	public List<TableColumnInfo> queryTableColumns(String dbName,
			String tableName) {
		List<TableColumnInfo> tableColumnList = new ArrayList<TableColumnInfo>();
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select ")
		.append(" columns.name name, ")
		.append(" types.name dataType,")
		.append(" columns.is_nullable isNullable,")
		.append(" columns.is_identity isIdentity,")
//		.append(" cast(columns.max_length as int) ByteLength,")
		.append(" (")
		.append("       case ")
		.append("               when types.name='nvarchar' and columns.max_length>0 then columns.max_length/2 ")
		.append("               when types.name='nchar' and columns.max_length>0 then columns.max_length/2")
		.append("               when types.name='ntext' and columns.max_length>0 then columns.max_length/2 ")
		.append("               else columns.max_length")
		.append("       end")
		.append(" ) fieldLength,")
		.append(" cast(columns.scale as int) scale,")
		.append(" cast(extended_properties.value as varchar(5000)) comment")
		.append(" from sys.columns")
		.append(" inner join sys.types on columns.system_type_id=types.system_type_id and columns.user_type_id=types.user_type_id")
		.append(" left join sys.extended_properties on columns.object_id=extended_properties.major_id and columns.column_id=extended_properties.minor_id")
		.append(" where object_id=OBJECT_ID('").append(tableName).append("')")
		.append(" order by columns.column_id");
		
		ResultSet result;
		
		try {
//			System.out.println(sqlBuilder.toString());
			result = sta.executeQuery(sqlBuilder.toString());
			tableColumnList = resultSetToBean(result, TableColumnInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		resultSetToBean(result, TableInfo.class);
		return tableColumnList;
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
		try {
			this.sta = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DataSource getSQLServerDataSource() {


		SQLServerDataSource sqlDS = new SQLServerDataSource();

		sqlDS.setURL(SqlConfiguration.getProperty(SqlConfiguration.JDBC_DB_URL));
		sqlDS.setDatabaseName(SqlConfiguration.getProperty(SqlConfiguration.JDBC_DB_NAME));
		sqlDS.setUser(SqlConfiguration.getProperty(SqlConfiguration.JDBC_DB_USERNAME));
		sqlDS.setPassword(SqlConfiguration.getProperty(SqlConfiguration.JDBC_DB_PASSWORD));

		return sqlDS;
	}
	
	

	public void close() {
		try {
			sta.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
		DataSource ds = getSQLServerDataSource();
		SqlServerJdbcClient client = new SqlServerJdbcClient();
		try {
			client.setConn(ds.getConnection());
			List<TableInfo> tables = client.queryTables("");
			List<AppTable> appTables = new ArrayList<AppTable>();
			for(TableInfo table : tables){
				AppTable apptable = new AppTable();
				apptable.setName(table.getName());
				apptable.setIntroduce(table.getComment());
				apptable.setWarehouseName(table.getName().toLowerCase());
				
				appTables.add(apptable);
				System.out.println(table.getName() );
				List<TableColumnInfo> tableColumns = client.queryTableColumns("", table.getName());
				int i = 0 ;
				List<TableColumn> columns = new ArrayList<TableColumn>();
				
				for(TableColumnInfo columnEle: tableColumns){
					TableColumn column =  new TableColumn();
					column.setName(columnEle.getName());
					column.setWarehouseName(columnEle.getName().toLowerCase());
					column.setColumnOrder(++i);
					column.setNullableLabel(new Boolean(columnEle.getIsNullable()));
					column.setDataType(columnEle.getDataType());
					column.setWarehouseDataType(HiveDataTypeConvertor.getType(columnEle.getDataType()));
					column.setCreateUserId(0l);
					column.setCreateDate(new Date());
					column.setColumnComment(columnEle.getComment());
					
					if("1".equals(columnEle.getIsIdentity())){
						apptable.setExistIdField(true);
						apptable.setIdColumnName(columnEle.getName());
					}
					columns.add(column);
//					System.out.println(columnEle.toString());
				}
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
	}
}
