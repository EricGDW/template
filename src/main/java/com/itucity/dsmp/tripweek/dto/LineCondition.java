package com.itucity.dsmp.tripweek.dto;

import com.itucity.dsmp.common.page.PageSortCondition;

public class LineCondition {

	private String city;
	private Float latitude;
	private Float longitude;
	private String who;
	private String when;
	private String type;
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

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PageSortCondition getPageSortCondition() {
		return pageSortCondition;
	}

	public void setPageSortCondition(PageSortCondition pageSortCondition) {
		this.pageSortCondition = pageSortCondition;
	}

}
