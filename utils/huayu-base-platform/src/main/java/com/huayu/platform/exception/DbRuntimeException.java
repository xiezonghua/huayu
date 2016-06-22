package com.huayu.platform.exception;

/**
 * 
 * @author xzh
 *
 */
public class DbRuntimeException extends BaseRuntimeException {
	public DbRuntimeException(String msg) {
		super(msg);
	}

	public DbRuntimeException(String msg, ExceptionCode code) {
		super(msg , code);
	}
	
	public DbRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbRuntimeException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;
}
