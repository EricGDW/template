package com.itucity.dsmp.tripwiki.service.model;


/**
 * 搜索信息
 * @author Eric
 * @version 0.1
 * 
 * 3/29/2014
 */
public class SearchInfoVO {

	private Integer searchId;
	
	private String condition;			//查询条件
	
	private String ctime;				//查询时间
	
	private String address;			//查询地点
	
	private String	weather;			//查询类型
	
	private String remarks;			//查询备注
	
	public SearchInfoVO() {
	}

	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
