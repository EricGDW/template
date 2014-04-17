package com.itucity.dsmp.tripwiki.service.model;

import java.util.List;

/**
 * 对应攻略
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
public class LineVO {

	private Integer lineId;
	
	private String name;			//线路名
	
	private String type;			//线路分类
	
	private String description;	//线路描述
	
	private Integer CoverId;		//线路封面
	
	private String bestTime;		//最佳时段
	
	private Integer share;			//攻略分享数
	
	private Integer agree;			//攻略赞数
	
	private Integer disagree;		//攻略踩数
	
	private String score;			//评分
	
	private List<String> destinationsName; 
									//攻略的景点
	
	private List<Integer> destinationList; 
									//攻略的景点
	
	private List<Integer> tagList; 
									//攻略的标签
	
	public LineVO() {
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Integer getAgree() {
		return agree;
	}

	public void setAgree(Integer agree) {
		this.agree = agree;
	}

	public Integer getDisagree() {
		return disagree;
	}

	public void setDisagree(Integer disagree) {
		this.disagree = disagree;
	}

	public Integer getCoverId() {
		return CoverId;
	}

	public void setCoverId(Integer coverId) {
		CoverId = coverId;
	}

	public String getBestTime() {
		return bestTime;
	}

	public void setBestTime(String bestTime) {
		this.bestTime = bestTime;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public List<String> getDestinationsName() {
		return destinationsName;
	}

	public void setDestinationsName(List<String> destinationsName) {
		this.destinationsName = destinationsName;
	}

	public List<Integer> getDestinationList() {
		return destinationList;
	}

	public void setDestinationList(List<Integer> destinationList) {
		this.destinationList = destinationList;
	}

	public List<Integer> getTagList() {
		return tagList;
	}

	public void setTagList(List<Integer> tagList) {
		this.tagList = tagList;
	}
	
}
