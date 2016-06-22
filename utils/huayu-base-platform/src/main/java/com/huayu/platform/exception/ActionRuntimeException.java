package com.huayu.platform.exception;

/**
 * 
 * @author xzh
 *
 */
public class ActionRuntimeException extends BaseRuntimeException {

	public ActionRuntimeException(String msg) {
		super(msg);
	}

	public ActionRuntimeException(String msg, ExceptionCode code) {
		super(msg , code);
	}
	
	public ActionRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionRuntimeException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
