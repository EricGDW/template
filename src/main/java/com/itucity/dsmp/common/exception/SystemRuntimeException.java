package com.itucity.dsmp.common.exception;

public class SystemRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String debugMsg;
	
	/**
	 * 获取错误码
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 获取debug错误信息
	 * @return
	 */
	public String getDebugMsg() {
		return debugMsg;
	}
	
	public SystemRuntimeException(String errorCode, String message, String debugMsg) {
		super(message);
		this.errorCode=errorCode;
		if(debugMsg!=null) this.debugMsg=debugMsg;
	}

	public SystemRuntimeException() {
		super();
	}
	
	public SystemRuntimeException(String message) {
		super(message);
	}
	
	public SystemRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SystemRuntimeException(Throwable cause) {
		super(cause);
	}

}
