package com.huayu.platform.exception;

/**
 * 
 * @author xzh
 *
 */
public class ServiceRuntimeException extends BaseRuntimeException {
	public ServiceRuntimeException(String msg) {
		super(msg);
	}

	public ServiceRuntimeException(String msg, ExceptionCode code) {
		super(msg , code);
	}
	
	public ServiceRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceRuntimeException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;
}
