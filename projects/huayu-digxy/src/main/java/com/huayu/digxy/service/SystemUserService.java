package com.huayu.digxy.service;

import java.util.Map;

import com.huayu.digxy.bo.SystemUser;
import com.huayu.digxy.model.UserModel;
import com.huayu.platform.service.BasicService;

public interface SystemUserService extends BasicService<SystemUser, Long> {

	SystemUser userLogin(String loginName, String password);

	long queryCount(Map<String, Object> queryMap);

	void register(UserModel sysUser);

	void findPwd(String email);

	void resetPwd(String email, String password, String oldPassword);

}