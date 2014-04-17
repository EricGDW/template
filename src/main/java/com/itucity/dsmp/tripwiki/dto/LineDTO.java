package com.itucity.dsmp.tripwiki.dto;

import java.util.List;

public class LineDTO {

	private LineVO line;
	private ImageVO cover;
	private List<PlaceVO> places;
	private List<TagVO> tags;

	public LineVO getLine() {
		return line;
	}

	public void setLine(LineVO line) {
		this.line = line;
	}

	public ImageVO getCover() {
		return cover;
	}

	public void setCover(ImageVO cover) {
		this.cover = cover;
	}

	public List<PlaceVO> getPlaces() {
		return places;
	}

	public void setPlaces(List<PlaceVO> places) {
		this.places = places;
	}

	public List<TagVO> getTags() {
		return tags;
	}

	public void setTags(List<TagVO> tags) {
		this.tags = tags;
	}

}
