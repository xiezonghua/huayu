package com.toread.etl.configure.serivce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestMysqlData {

	private static final String SQLSERVER_DB_URL = "SQLSERVER_DB_URL";
	private static final String SQLSERVER_DB_USERNAME = "SQLSERVER_DB_USERNAME";
	private static final String SQLSERVER_DB_PASSWORD = "SQLSERVER_DB_PASSWORD";
	private static final String CONFIG_PROPERTIES_NAME = "config.properties";
	private static final Properties props = new Properties();

	static {
		try {
			InputStream inputStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(CONFIG_PROPERTIES_NAME);
			props.load(inputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DataSource getSQLServerDataSource() {

		SQLServerDataSource sqlDS = new SQLServerDataSource();

		sqlDS.setURL(props.getProperty(SQLSERVER_DB_URL));
		sqlDS.setUser(props.getProperty(SQLSERVER_DB_USERNAME));
		sqlDS.setPassword(props.getProperty(SQLSERVER_DB_PASSWORD));

		return sqlDS;
	}

	public static void executeSqlBatch(String sql) {
		DataSource ds = getSQLServerDataSource();

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();

			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void testExecuteSqlBatch(String sql) {
		DataSource ds = getSQLServerDataSource();

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			System.out.println(sql);
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

			System.out.println("The result is:");
			Integer i = 0;
			while (rs.next()) {
				System.out.println(rs.getString(1) + "---" + rs.getString(2)
						+ "---" + rs.getString(3) + "---" + rs.getString(4)
						+ "---" + rs.getString(5) + "---" + rs.getString(6)
						+ "---" + rs.getString(7) + "---" + rs.getString(8));
			}

			System.out.println("Completed.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// SqlServerHelper sqlServerHelper
		String sql = new TestMysqlData().getTestSql();
		// System.out.println(sql);

		// testExecuteSqlBatch("select * from sys.extended_properties");
		// testExecuteSqlBatch("select * from sys.columns");

		testExecuteSqlBatch(sql);
	}

	private String getTestSql() throws IOException {
		String path = "E:\\大数据项目\\美景联动\\测试环境\\mysqltest.sql";

		StringBuilder sbTemplate = new StringBuilder();
		String encoding = "UTF-8";
		File file = new File(path);

		if (file.isFile() && file.exists()) {
			InputStreamReader read = null;
			read = new InputStreamReader(new FileInputStream(file), encoding);
			BufferedReader bufferedReader = new BufferedReader(read);

			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {

				sbTemplate.append(lineTxt);
				sbTemplate.append("\r\n");
			}

			read.close();
		}

		return sbTemplate.toString();
	}
}
