package com.toread.etl.configure.serivce.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toread.etl.configure.serivce.JdbcClient;

public abstract class AbstractJdbcClient implements JdbcClient {
	private final static Logger logger = LoggerFactory.getLogger(SqlServerJdbcClient.class);
	
	protected void show(ResultSet rs){
		try {
			while(rs.next()){
				System.out.println(rs.getString(1)+ " "+ rs.getString("comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected <T> List<T> resultSetToBean(ResultSet rs, Class<T> beanClazz) {
		List<T> beanList = new ArrayList<T>();		
		Field[] field = beanClazz.getDeclaredFields();
		
		try {
			T bean = null;
			while(rs.next()){
				bean = beanClazz.newInstance();
				for(Field f : field){					
					f.setAccessible(true);				
					try{
						if(rs.findColumn(f.getName()) > 0){
							f.set(bean, rs.getString(f.getName()));
						}
					}catch(Exception e){
						logger.info("field({}) is not a column" , f.getName());
					}
				}
				beanList.add(bean);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();			
		}
		
		return beanList;
	}
	
}
