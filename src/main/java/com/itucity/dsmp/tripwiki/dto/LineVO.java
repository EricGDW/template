package com.itucity.dsmp.tripwiki.dto;

/**
 * 对应攻略
 * 
 * @author Eric
 * @version 0.1
 * 
 *          3/24/2014
 */
public class LineVO {

	private Integer lineId;
	private String title;
	private String description;
	private Integer coverId;
	private String bestTime;

	public LineVO() {
	}

	public LineVO(Integer lineId, String title, String description,
			Integer coverId, String bestTime) {
		super();
		this.lineId = lineId;
		this.title = title;
		this.description = description;
		this.coverId = coverId;
		this.bestTime = bestTime;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCoverId() {
		return coverId;
	}

	public void setCoverId(Integer coverId) {
		this.coverId = coverId;
	}

	public String getBestTime() {
		return bestTime;
	}

	public void setBestTime(String bestTime) {
		this.bestTime = bestTime;
	}

}
