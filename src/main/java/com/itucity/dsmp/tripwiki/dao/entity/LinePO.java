package com.itucity.dsmp.tripwiki.dao.entity;
// default package
// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * LinePO generated by hbm2java
 */
@Entity
@Table(name = "tw_line", catalog = "dsmp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LinePO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1710718226404871363L;
	private Integer lineId;
	private String title;
	private String description;
	private Integer coverId;
	private String bestTime;

	public LinePO() {
	}

	public LinePO(String title, String description, Integer coverId,
			String bestTime) {
		this.title = title;
		this.description = description;
		this.coverId = coverId;
		this.bestTime = bestTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "line_id", unique = true, nullable = false)
	public Integer getLineId() {
		return this.lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "cover_id")
	public Integer getCoverId() {
		return this.coverId;
	}

	public void setCoverId(Integer coverId) {
		this.coverId = coverId;
	}

	@Column(name = "best_time", length = 20)
	public String getBestTime() {
		return this.bestTime;
	}

	public void setBestTime(String bestTime) {
		this.bestTime = bestTime;
	}

}
