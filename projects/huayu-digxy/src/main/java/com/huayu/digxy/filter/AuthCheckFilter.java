package com.huayu.digxy.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.digxy.bo.SystemUser;
import com.huayu.digxy.service.SystemUserService;
import com.huayu.platform.exception.ActionRuntimeException;
import com.huayu.platform.exception.BaseRuntimeException;
import com.huayu.platform.exception.ServiceRuntimeException;
import com.huayu.web.platform.utils.SessionHelper;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class AuthCheckFilter extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	private String excludedPatter = "^(index|result|search|pindex).*|.*(login|regist|findPwd|resetPwd)$" ;
	
	private final static Logger logger = LoggerFactory.getLogger( AuthCheckFilter.class.getCanonicalName());
	
	@Resource
	private SystemUserService systemUserService;	
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext() ;		
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		
//		request.setAttribute("exports", ExpertHelper.getData());
		HttpSession session =  request.getSession();
		String userName = (String) session.getAttribute(SessionHelper.SESSION_USERNAME);
		
		try{
	
			boolean isCorrect = false; 
			if (StringUtils.isNotBlank(userName)) {
				String password = (String) session.getAttribute(SessionHelper.SESSION_PASSWORD);
				isCorrect = isCorrectAccount(userName, password, request);
				if (isCorrect) {
					return invocation.invoke();
				}
		
			}
			
			if (urlCheck(request.getServletPath())) {
				return invocation.invoke();
			}
			
	
			
		}catch(ActionRuntimeException ex){
			handleRuntimeException(invocation, ex, "Action : ");
			return Action.SUCCESS; 
		}catch (ServiceRuntimeException ex) {
			handleRuntimeException(invocation, ex, "Service : ");
			return Action.SUCCESS; 
		}
		
//		Map<String, Object> session = invocation.getInvocationContext().getSession();
//		
//		String userName =  (String) session.get(SessionHelper.SESSION_USERNAME);
//		String password = (String) session.get(SessionHelper.SESSION_PASSWORD);
		session.setMaxInactiveInterval(10*60*1000);
		
		return Action.LOGIN;
	}
	
	private void handleRuntimeException(ActionInvocation invocation , BaseRuntimeException ex , String prefixed){
		ValueStack stack = invocation.getStack();
		stack.setValue("status", ex.getCode());
		stack.setValue("statusInfo", prefixed  + ex.getMessage());
		logger.warn("Exception code({}) , msg :/n {}" , ex.getCode() , ex.getStackTrace());
	}
	
	private boolean urlCheck(String url){
		Pattern pathPatten = Pattern.compile(excludedPatter);
		Matcher matcher = pathPatten.matcher(url.substring(1));
		if(matcher != null && matcher.find()){
			return true;
		}	
		return false;
	}
	
	private boolean isCorrectAccount(String userName , String password , HttpServletRequest request){
		boolean  isCorrect = false ;
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			return isCorrect ;
		}
		
		SystemUser user = systemUserService.userLogin(userName, password);	
		logger.info("user information , name {}" , user.getPetName());
		if( null != user ){
			isCorrect = true ;			
			request.setAttribute("uname", user.getPetName());
			request.setAttribute("uid", user.getId());
			request.setAttribute("urole", user.getRole());
		}		
		return isCorrect ;
	}
	
	public static void main(String[] args) {
		AuthCheckFilter filter = new AuthCheckFilter();
		System.out.println(filter.urlCheck("/index"));
	}
}

