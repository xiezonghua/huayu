package com.huayu.digxy.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.huayu.digxy.bo.SystemUser;
import com.huayu.digxy.model.UserModel;
import com.huayu.digxy.service.SystemUserService;
import com.huayu.platform.utils.DateBaseUtils;
import com.huayu.web.platform.action.BasicModelAction;
import com.huayu.web.platform.utils.SessionHelper;
import com.huayu.web.platform.utils.ValidateUtils;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/user")
public class SystemUserAction extends BasicModelAction {

	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(SystemUserAction.class.getCanonicalName());
	
	@Resource(name="systemUserService")
	private SystemUserService service;
	
	private UserModel userModel = new UserModel();

	@Action(value="login" , results={@Result(type="json" , name=SUCCESS)})
	public String login(){
		Map<String, Object> validateResult = ValidateUtils.validateEmpty(new String[]{"userName", "passowrd"}, new String[]{userModel.getUserName(), userModel.getPassword()});
		if(!validateResult.isEmpty()){
			setValidateInfo(validateResult);
			return SUCCESS;
		}
		
		SystemUser sysUser = service.userLogin(userModel.getUserName(), userModel.getPassword());
		
		if( null == sysUser){
			setStautsInfo("login failure , username or password is bad.");
			logger.info("login failure , username or password is bad.");
		}else{
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put(SessionHelper.SESSION_USER_ID, sysUser.getId());
			session.put(SessionHelper.SESSION_USERNAME, sysUser.getPetName());
			session.put(SessionHelper.SESSION_PASSWORD, sysUser.getPassword());
			session.put(SessionHelper.SESSION_USER_ROLE, sysUser.getRole());
		}
		
		return SUCCESS;
	}
	
	@Action(value="signout" , results={@Result(type="json" , name=SUCCESS)})
	public String signOut(){
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}
	
	@Action(value="regist" , results={@Result(type="json" , name=SUCCESS)})
	public String regist(){
		String[] validateNames = new String[]{"userName", "password","petName" , "email"};
		String[] validateValues = new String[]{userModel.getUserName() , userModel.getPassword() , userModel.getPetName() , userModel.getEmail()};
		if(!validate(validateNames, validateValues)) return SUCCESS;
		
		service.register(userModel);
		return SUCCESS;
	}
	
	@Action(value="getById" , results={@Result(type="json" , name=SUCCESS)})
	public String getById(){
		if( null == userModel.getId()){
			return setStautsInfo("Query fail , id is null");
		}
		SystemUser user = service.queryByPrimaryKey(userModel.getId());
		setData(user);
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		String dateFolder = DateBaseUtils.format(new Date(), "/yyyyMMdd/");
		System.out.println(dateFolder);
	}
	
	@Action(value="update" , results={@Result(type="json" , name=SUCCESS)})
	public String update(){
		if(null == getUserId()){
			setStautsInfo("You must login in , if you need to update it.");
			return SUCCESS;
		}
		
		if( null == userModel.getId() || !getUserId().equals(userModel.getId())){
			setStautsInfo("You must login in , if you need to update it.");
			return SUCCESS;
		}
		
		String[] validateNames = new String[]{"petName" , "email"};
		String[] validateValues = new String[]{ userModel.getPetName() , userModel.getEmail()};
		if(!validate(validateNames, validateValues)) return SUCCESS;
		
		SystemUser sysUser = new SystemUser();
		BeanUtils.copyProperties(userModel, sysUser);
		service.updateByPrimaryKeySelective(sysUser);
		return SUCCESS;
		
	}
	
	@Action(value="updatePwd" , results={@Result(type="json" , name=SUCCESS)})
	public String updatePwd(){
		if(!getPassword().equals(userModel.getOldPassword())){
			return setStautsInfo("用户密码不正确");
		}
		
		SystemUser user = new SystemUser();
		user.setId(getUserId());
		user.setPassword(userModel.getPassword());
		service.updateByPrimaryKeySelective(user);
		
		return SUCCESS;
	}
	
	@Action(value="findPwd" , results={@Result(type="json" , name=SUCCESS)})
	public String findPwd(){
		service.findPwd(userModel.getEmail());
		return SUCCESS;
	}
	
	@Action(value="resetPwd" , results={@Result(type="json" , name=SUCCESS)})
	public String resetPwd(){
		if(System.currentTimeMillis() - this.getResetTime() > 60*60*1000){
			logger.info("the user{} modify password url have outtime.", userModel.getEmail());
		}
		
		service.resetPwd(userModel.getEmail(), userModel.getPassword(), oldPwd);
		
		return SUCCESS;
	}
	
	private Long resetTime ;
	private String oldPwd ;

	public Long getResetTime() {
		return resetTime;
	}

	public void setResetTime(Long resetTime) {
		this.resetTime = resetTime;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
