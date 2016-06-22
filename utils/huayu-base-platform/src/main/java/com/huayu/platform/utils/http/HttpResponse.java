package com.huayu.platform.utils.http;

import java.util.List;
import java.util.Map;

import com.huayu.platform.utils.JsonUtils;

/**
 * 
 * @author xzh
 *
 */
public class HttpResponse {
	private int code;
	private String msg;
	private String result;
	private boolean success;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public <T> T toBean(Class<T> c){
		return JsonUtils.toBean(result, c);
	}
	
	public <T> List<T> toList(Class<T> c){
		return JsonUtils.toList(result, c);		
	}
	
	public <T> Map<String, T> toMap(Class<T> c){
		return JsonUtils.toMap(result, c);		
	}
}
