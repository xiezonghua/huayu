package com.huayu.platform.dao;

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
public interface BaseDaoMapper<T, ID extends Serializable> {

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    long count();

    List<T> selectAll();

    List<T> select(T record);
    
    List<T> selectByCondition(Map<String,Object> query);
    
    long count(Map<String,Object> query);
}
