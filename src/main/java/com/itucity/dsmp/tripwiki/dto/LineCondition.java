package com.itucity.dsmp.tripwiki.dto;

import com.itucity.dsmp.common.page.PageSortCondition;

public class LineCondition {

	private String city;
	private Float latitude;
	private Float longitude;
	private Integer who;
	private Integer when;
	private Integer type;
	private PageSortCondition pageSortCondition = new PageSortCondition(1,10, null);

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Integer getWho() {
		return who;
	}

	public void setWho(Integer who) {
		this.who = who;
	}

	public Integer getWhen() {
		return when;
	}

	public void setWhen(Integer when) {
		this.when = when;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public PageSortCondition getPageSortCondition() {
		return pageSortCondition;
	}

	public void setPageSortCondition(PageSortCondition pageSortCondition) {
		this.pageSortCondition = pageSortCondition;
	}

}
