package com.huayu.platform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xzh
 *
 * @param <T>
 * @param <ID>
 */
public interface BasicService<T, ID extends Serializable> {
    public int delete(ID id);

    public int add(T obj);

    public int addSelective(T obj);

    public T queryByPrimaryKey(ID id);

    public int updateByPrimaryKeySelective(T obj);

    public int updateByPrimaryKey(T record);

    public long count();

    public List<T> queryAll();

    public List<T> query(T record);
    
    public List<T> query(Map<String,Object> query);
    
    public long count(Map<String,Object> query);
}

