package com.itucity.dsmp.tripwiki.dao.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 历史查询记录信息
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/28/2014
 */
@Entity
@Table(name = "tw_searchInfo", catalog = "dsmp")
public class SearchInfoPO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer searchId;
	
	private String conditions;			//查询条件
	
	private String ctime;				//查询时间
	
	private String address;			//查询地点
	
	private String weather;			//查询地点
	
	private String type;				//查询类型
	
	private String remarks;			//查询备注
	
	private Integer uid;				//查询用户

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "search_id", unique = true, nullable = false)
	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	@Column(name = "conditions", length = 255)
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	@Column(name = "ctime", length = 30)
	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	@Column(name = "address", length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "weather", length = 255)
	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	@Column(name = "type", length = 30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "uid", length = 11)
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


	
	
}
