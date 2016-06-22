package com.huayu.platform.exception;


/**
 * 
 * @author xzh
 *
 */
public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private int code = 2000;
	
	public BaseRuntimeException(String msg) {
		super(msg);
	}
	
	public BaseRuntimeException(String msg, ExceptionCode code) {
		super(msg);
		this.code = code.getCode();
	}
	
	public BaseRuntimeException(String msg, Throwable paramThrowable) {
		super(msg, paramThrowable);
	}

	public BaseRuntimeException(Throwable paramThrowable) {
		super(paramThrowable);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
