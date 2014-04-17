package com.itucity.dsmp.tripweek.dto;

import java.util.HashMap;
import java.util.Map;

import com.itucity.dsmp.common.Constants;

public class DataReturnDTO extends HashMap<String, Object> implements
		Map<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object data;
	private ErrorInfoDTO errorInfo = new ErrorInfoDTO();

	public Object getData() {
		return data;
	}

	public ErrorInfoDTO getErrorInfo() {
		return errorInfo;
	}

	public void setData(Object data) {
		this.data = data;
		if ("".equals(errorInfo.getErrorCode()) || errorInfo.getErrorCode()==null) {
			super.put("data", data);
			super.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
		}
	}

	public void setErrorInfo(ErrorInfoDTO errorInfo) {
		if (!"".equals(errorInfo.getErrorCode())) {
			super.put("error_msg", errorInfo);
			super.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
		} 
	}

	public Object setErrorInfo(String errorCode, String errorMessage) {
		if (!errorCode.equals(null)) {
			errorInfo.setErrorCode(errorCode);
			errorInfo.setErrorMessage(errorMessage);
			super.put("error_msg", errorInfo);
			super.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			return errorInfo;
		} else {
			return null;
		}
	}

}

class ErrorInfoDTO {
	private String errorCode;
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}