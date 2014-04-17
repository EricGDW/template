package com.itucity.dsmp.tripwiki.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 行程的线路，对应需求的攻略
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */

@Entity
@Table(name = "tw_travelline", catalog = "dsmp")
public class LinePO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer lineId;
	
	private String name;				//线路名
	
	private String type;				//线路分类
	
	private String description;		//线路描述
	
	private Integer coverId;			//线路封面
	
	private String bestTime;			//最佳时段
	
	private String score;				//评分
	
	private Integer shareCount;		//攻略分享数
	
	private Integer favouriteCount;	//攻略赞数
	
	private Integer hateCount;			//攻略踩数
	
	private Integer commentCount;		//攻略评论数
	
	private Integer collectCount;		//攻略收藏数
	
	private String destinations;		//攻略的景点列表，按先后顺序(考虑存景点id)
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "line_id", unique = true, nullable = false)
	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@Column(name = "line_name", length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "share_count", length = 11)
	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	@Column(name = "favourite_count", length = 11)
	public Integer getFavouriteCount() {
		return favouriteCount;
	}

	public void setFavouriteCount(Integer favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

	@Column(name = "hate_count", length = 11)
	public Integer getHateCount() {
		return hateCount;
	}

	public void setHateCount(Integer hateCount) {
		this.hateCount = hateCount;
	}

	@Column(name = "comment_count", length = 11)
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "collect_count", length = 11)
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	@Column(name = "cover_id", length = 255)
	public Integer getCoverId() {
		return coverId;
	}

	public void setCOverId(Integer coverId) {
		this.coverId = coverId;
	}

	@Column(name = "best_time", length = 20)
	public String getBestTime() {
		return bestTime;
	}

	public void setBestTime(String bestTime) {
		this.bestTime = bestTime;
	}

	public String getScore() {
		return score;
	}

	@Column(name = "score", length = 8)
	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "destinations", length = 255)
	public String getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}
	
}
