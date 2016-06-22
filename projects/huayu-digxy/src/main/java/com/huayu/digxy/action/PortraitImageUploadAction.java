package com.huayu.digxy.action;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.digxy.bo.SystemUser;
import com.huayu.digxy.service.SystemUserService;

@Namespace("/upload")
public class PortraitImageUploadAction extends ImageUploadAction {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(PortraitImageUploadAction.class.getCanonicalName());

	private Long busId;
	
	@Resource(name="systemUserService")
	private SystemUserService service;
	
	@Override
	public String getStorePath() {
		return "/images/portrait";
	}

	public String getStoreFileName() {
		return getUserId() + "." + FilenameUtils.getExtension(this.getMyFileFileName().get(0));
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}
	
	@Action(value="/imageindex" , results={@Result(name=SUCCESS , type="velocity"  , location="/vm/myImage.vm") })
	public String imageIndex(){
		return SUCCESS;
	}
	
	@Action(value="/portrait" , results={@Result(name=SUCCESS , type="redirect"  , location="../user/index") })
	public String execute() throws Exception {
		super.execute();
		logger.info("portrait uploader success");
		SystemUser obj = new SystemUser();
		obj.setId(getUserId());
		obj.setImgSrc(getStoreFileName());
		service.updateByPrimaryKeySelective(obj);
		
		return SUCCESS;
	}
		
	
	
}
