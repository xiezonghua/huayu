package com.huayu.example.service;

import java.util.List;
import java.util.Map;

import com.huayu.example.bo.SimpleUser;

/**
 * 
 * @author xzh
 *
 */
public interface SimpleUserService {

	SimpleUser getUser(Long id);

	List<SimpleUser> getUserList(Map<String, Object> query);

	void addUser(Long id, String name);

	void deleteUser(Long id);
}
