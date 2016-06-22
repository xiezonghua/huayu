package com.huayu.digxy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.huayu.digxy.bo.SystemUser;
import com.huayu.digxy.dao.SystemUserDao;
import com.huayu.digxy.model.UserModel;
import com.huayu.digxy.service.SystemUserService;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.platform.exception.ActionRuntimeException;
import com.huayu.platform.exception.ServiceRuntimeException;
import com.huayu.platform.service.AbstractBasicService;
import com.huayu.web.platform.bo.AuthorityType;
import com.huayu.web.platform.utils.mail.MailProperties;
import com.huayu.web.platform.utils.mail.MailServer;


@Service("systemUserService")
public class SystemUserServiceImpl extends AbstractBasicService<SystemUser , Long> implements SystemUserService{

	private final static Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class.getCanonicalName());

	@Resource
	private SystemUserDao systemUserDao ;

	@Override
	public BaseDaoMapper<SystemUser, Long> getDao() {		
		return systemUserDao;
	}
	
	public SystemUser userLogin(String loginName, String password) {
		Map<String , Object> query = new HashMap<String , Object>();
		query.put("userName", loginName);
		query.put("password", password);
		List<SystemUser> users = systemUserDao.selectLoginUser(query);

		if (1 == users.size()) {
			return users.get(0);
		}

		logger.info("user ({}) login failure , query users size : {}",
				loginName, users.size());

		return null;
	}

	public long queryCount(Map<String, Object> queryMap) {
		return systemUserDao.count(queryMap); 
	}

	public void register(UserModel user) {
		Map<String , Object> query = new HashMap<String,Object>();
		query.put("email", user.getEmail());
		Long userCount = queryCount(query);
		if(userCount > 0 ){
			throw new ServiceRuntimeException("邮箱已经被注册过了，请使用其他邮箱。");
		}
		
		//邮箱确认
		MailProperties mailProp = MailServer.getDefaultProperties();
		mailProp.setReceiver(user.getEmail());
		try {
			MailServer.sendSimpleMail(mailProp);
		} catch (MessagingException e) {
			logger.warn("确认邮件发送失败，请确认邮箱地址。exception :{}" , e );
			throw new ServiceRuntimeException("确认邮件发送失败，请确认邮箱地址");
		}
		user.setRegtime(new Date());
		user.setRole(AuthorityType.MEMBER.getValue());
		SystemUser sysUser = new SystemUser();
		BeanUtils.copyProperties(user, sysUser);
		sysUser.setName(user.getEmail());
		systemUserDao.insertSelective(sysUser);		
	}

	public void findPwd(String email) {
		SystemUser sysUser = new SystemUser();
		sysUser.setEmail(email);
		List<SystemUser> users = systemUserDao.select(sysUser);
		if(users.size() != 1 ){
			logger.warn("Find {} user by the email " , users.size());
			throw new ServiceRuntimeException("邮箱存在问题，请确认；否则请联系管理员。");
		}
		MailProperties mailProp = MailServer.getDefaultProperties();
		mailProp.setReceiver(email);
		mailProp.setSubject("迪格学苑密码找回");
		String resetUrl = "http://www.digxy.cn/page/up_reset.html?email="+email +"&t="+System.currentTimeMillis() +"&p=" + users.get(0).getPassword() ;
		mailProp.setContent("请使用当前地址一个小时内重置密码, <a href='"+resetUrl+"'>"+resetUrl+"</a>");
		
		try {
			MailServer.sendSimpleMail(mailProp);
		} catch (MessagingException e) {
			logger.info("The mail {} send failure , In password find action , exception: {}" , email , e);
			throw new ServiceRuntimeException("邮件发送失败，请重试！");
		}
	}

	public void resetPwd(String email, String password , String oldPassword) {
		SystemUser sysUser = new SystemUser();
		sysUser.setEmail(email);
		sysUser.setPassword(password);
		List<SystemUser> userList = systemUserDao.select(sysUser);
		
		if(userList.size() != 1){
			logger.warn("the user{} exist more than one" ,email);
			throw new ActionRuntimeException("该用户存在安全问题，暂时无法修改密码，请联系管理员");
		}
		
		SystemUser user = new SystemUser();
		user.setPassword(password);
		user.setId(userList.get(0).getId());
		
		systemUserDao.updateByPrimaryKeySelective(user);
		
	}

}
