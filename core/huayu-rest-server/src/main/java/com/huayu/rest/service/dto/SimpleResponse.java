package com.huayu.rest.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wangmingzhe
 * 
 */
@XmlRootElement
public class SimpleResponse extends BaseDto {
	
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
