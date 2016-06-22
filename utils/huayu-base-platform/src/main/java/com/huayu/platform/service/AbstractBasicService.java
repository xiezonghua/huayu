package com.huayu.platform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huayu.platform.dao.BaseDaoMapper;

/**
 * 
 * @author xzh
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractBasicService<T , ID extends Serializable> {
	
	public abstract BaseDaoMapper<T , ID> getDao();
	
	public int delete(ID id){
		return getDao().deleteByPrimaryKey(id);
	}
	
	public int add(T obj){
		return getDao().insert(obj);
	}

	public int addSelective(T obj){
		return getDao().insertSelective(obj);
	}

	public T queryByPrimaryKey(ID id){
		return getDao().selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(T obj){
		return getDao().updateByPrimaryKeySelective(obj);
	}

	public int updateByPrimaryKey(T record){
		return getDao().updateByPrimaryKey(record);
	}

	public long count(){
		return getDao().count();
	}

	public List<T> queryAll(){
		return getDao().selectAll();
	}
	
	public List<T> query(T obj){
		return getDao().select(obj);
	}
	
	public List<T> query(Map<String, Object> query) {
		return getDao().selectByCondition(query);
	}

	public long count(Map<String, Object> query) {
		return getDao().count(query);
	}
}
