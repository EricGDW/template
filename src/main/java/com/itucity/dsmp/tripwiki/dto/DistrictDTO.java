package com.itucity.dsmp.tripwiki.dto;

import java.util.List;

public class DistrictDTO {

	private String district_name;
	private List<Object> neighborhoods;

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public List<Object> getNeighborhoods() {
		return neighborhoods;
	}

	public void setNeighborhoods(List<Object> neighborhoods) {
		this.neighborhoods = neighborhoods;
	}

}
