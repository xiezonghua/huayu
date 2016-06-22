package com.huayu.digxy.dao;

import java.util.List;
import java.util.Map;

import com.huayu.digxy.bo.SimpleUser;
import com.huayu.platform.dao.BaseDaoMapper;

/**
 * @author xzh
 *
 */
public interface SimpleUserDao extends BaseDaoMapper<SimpleUser, Long>{
	
	SimpleUser selectUserById(Long id);

	List<SimpleUser> selectUsers(Map<String, Object> query);
	
	void deleteById(Long id);
	
	void updateByIdSelective(SimpleUser user);
}
