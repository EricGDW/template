package com.itucity.dsmp.tripwiki.service.model;

public class TagVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7049784118392659559L;
	
	private Integer tagId;
	
	private String name; // 标签名称
	
	private String codeName;// 标签名称映射
	
	private Float weight; // 权重
	
	private String tagType; // 类型
	
	private Boolean starred; // 是否系统加星
	
	private Integer coverId;// 封面ID
	
	private Integer parentId; // 父级标签ID
	
	private String description;// 标签的概念描述

	public TagVO() {
	}
	

	public Integer getTagId() {
		return tagId;
	}


	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public Boolean getStarred() {
		return starred;
	}

	public void setStarred(Boolean starred) {
		this.starred = starred;
	}

	public Integer getCoverId() {
		return coverId;
	}

	public void setCoverId(Integer coverId) {
		this.coverId = coverId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
