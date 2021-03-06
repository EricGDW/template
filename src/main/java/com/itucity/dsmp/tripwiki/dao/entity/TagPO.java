package com.itucity.dsmp.tripwiki.dao.entity;
// default package
// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * TagPO generated by hbm2java
 */
@Entity
@Table(name = "tw_tag", catalog = "dsmp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagPO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 710060924985825888L;
	private Integer tagId;
	private String name;
	private String codeName;
	private Integer coverId;
	private String description;
	private Integer parentId;
	private String tagType;
	private Integer weight;
	private Integer coverSmall;
	private Integer starred;

	public TagPO() {
	}

	public TagPO(String name, String codeName, Integer coverId,
			String description, Integer parentId, String tagType,
			Integer weight, Integer coverSmall, Integer starred) {
		this.name = name;
		this.codeName = codeName;
		this.coverId = coverId;
		this.description = description;
		this.parentId = parentId;
		this.tagType = tagType;
		this.weight = weight;
		this.coverSmall = coverSmall;
		this.starred = starred;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tag_id", unique = true, nullable = false)
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code_name", length = 60)
	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name = "cover_id")
	public Integer getCoverId() {
		return this.coverId;
	}

	public void setCoverId(Integer coverId) {
		this.coverId = coverId;
	}

	@Lob
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "tag_type", length = 20)
	public String getTagType() {
		return this.tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	@Column(name = "weight")
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "cover_small")
	public Integer getCoverSmall() {
		return this.coverSmall;
	}

	public void setCoverSmall(Integer coverSmall) {
		this.coverSmall = coverSmall;
	}

	@Column(name = "starred")
	public Integer getStarred() {
		return this.starred;
	}

	public void setStarred(Integer starred) {
		this.starred = starred;
	}

}
