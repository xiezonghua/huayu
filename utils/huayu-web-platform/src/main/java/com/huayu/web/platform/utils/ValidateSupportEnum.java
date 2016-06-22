package com.huayu.web.platform.utils;

public enum ValidateSupportEnum {
	empty, email, phone;
	
	private String pattern;
	
	public String getPattern(){
		return pattern ;
	}
	
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
	
}
