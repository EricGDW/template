package com.itucity.dsmp.common.exception;

/**
 * 
 *
 */
public class InvalidArgumentsException extends SystemRuntimeException {

	private static final long serialVersionUID = -7753752821433137920L;

	public InvalidArgumentsException(String errorCode) {
		super(errorCode);
	}
	
	
	public InvalidArgumentsException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	

}
