package com.itucity.dsmp.common.base.impl;

/**
 * 存储过程参数类
 */
public class ProcedureParam {

	private Boolean isInParam;
	private int sqlType;
	private Object value;
	
	private ProcedureParam(Boolean isInParam, int sqlType, Object value) {
		this.isInParam=isInParam;
		this.sqlType=sqlType;
		this.value=value;
	}
	
	/**
	 * 创建In参数
	 * @param value 参数值
	 * @return
	 */
	public static ProcedureParam newInParam(Object value) {
		return new ProcedureParam(true, 0, value);
	}
	
	/**
	 * 创建Out参数(调用存储过程后, out参数值存放在value中)
	 * @param sqlType 声明对应数据库的参数类型(例如: oracle.jdbc.OracleTypes.CURSOR)
	 * @return
	 */
	public static ProcedureParam newOutParam(int sqlType) {
		return new ProcedureParam(false, sqlType, null);
	}

	public Boolean getIsInParam() {
		return isInParam;
	}

	public void setIsInParam(Boolean isInParam) {
		this.isInParam = isInParam;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}
