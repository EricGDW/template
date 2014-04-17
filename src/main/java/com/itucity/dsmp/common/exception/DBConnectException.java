package com.itucity.dsmp.common.exception;


/**
 * <p>数据库连接异常</p>
 * <p><b>最后修改时间：</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2010-12-17下午02:30:14</p>
 * @version 1.0
 */
public class DBConnectException extends SystemRuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2625995573560181123L;

	public DBConnectException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
}
