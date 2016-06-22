package com.huayu.example.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huayu.example.bo.SimpleUser;
import com.huayu.example.dao.SimpleUserDao;
import com.huayu.example.service.SimpleUserService;

/**
 * 
 * @author xzh
 *
 */
@Service("userService")
public class SimpleUserServiceImpl implements SimpleUserService{

	@Resource
	private SimpleUserDao userDao;
	
	public SimpleUser getUser(Long id) {
		return userDao.selectUserById(id);
	}

	public List<SimpleUser> getUserList(Map<String, Object> query) {
		return userDao.selectUsers(query);
	}

	public void addUser(Long id, String name) {
		SimpleUser user = new SimpleUser();

		user.setName(name);
		userDao.insertSelective(user);
		
//		userDao.insertSelective(user);
		
	}

	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}

}
