package com.itucity.dsmp.tripwiki.dao.entity;
// default package
// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LineSocialCountPO generated by hbm2java
 */
@Entity
@Table(name = "tw_line_social_count", catalog = "dsmp")
public class LineSocialCountPO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281172390487121911L;
	private Integer lineId;
	private Integer favoriteCount;
	private Integer shareCount;
	private Integer collectCount;
	private Integer commentId;
	private Integer hateCount;
	private Integer onroadCount;
	private Float score;
	private Float weight;
	private Integer viewCount;

	public LineSocialCountPO() {
	}

	public LineSocialCountPO(Integer lineId) {
		this.lineId = lineId;
	}

	public LineSocialCountPO(Integer lineId, Integer favoriteCount,
			Integer shareCount, Integer collectCount, Integer commentId,
			Integer hateCount, Integer onroadCount, Float score, Float weight,
			Integer viewCount) {
		this.lineId = lineId;
		this.favoriteCount = favoriteCount;
		this.shareCount = shareCount;
		this.collectCount = collectCount;
		this.commentId = commentId;
		this.hateCount = hateCount;
		this.onroadCount = onroadCount;
		this.score = score;
		this.weight = weight;
		this.viewCount = viewCount;
	}

	@Id
	@Column(name = "line_id", unique = true, nullable = false)
	public Integer getLineId() {
		return this.lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@Column(name = "favorite_count")
	public Integer getFavoriteCount() {
		return this.favoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	@Column(name = "share_count")
	public Integer getShareCount() {
		return this.shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	@Column(name = "collect_count")
	public Integer getCollectCount() {
		return this.collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	@Column(name = "comment_id")
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@Column(name = "hate_count")
	public Integer getHateCount() {
		return this.hateCount;
	}

	public void setHateCount(Integer hateCount) {
		this.hateCount = hateCount;
	}

	@Column(name = "onroad_count")
	public Integer getOnroadCount() {
		return this.onroadCount;
	}

	public void setOnroadCount(Integer onroadCount) {
		this.onroadCount = onroadCount;
	}

	@Column(name = "score", precision = 12, scale = 0)
	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Column(name = "weight", precision = 12, scale = 0)
	public Float getWeight() {
		return this.weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	@Column(name = "view_count")
	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

}
