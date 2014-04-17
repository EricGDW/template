package com.itucity.dsmp.tripwiki.dto;

public class TagVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7049784118392659559L;

	private Integer tagId;
	private String name;// 标签名称
	private String codeName;// 标签名称映射
	private Integer coverId;// 封面ID
	private String description;// 标签的概念描述
	private Integer parentId;// 父级标签ID
	private String tagType; // 类型
	private Integer weight;// 权重
	private Integer coverSmall;// Tag小封面
	private Integer starred;// 是否系统加星

	public TagVO(Integer tagId, String name, String codeName, Integer coverId,
			String description, Integer parentId, String tagType,
			Integer weight, Integer coverSmall, Integer starred) {
		super();
		this.tagId = tagId;
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

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public Integer getStarred() {
		return starred;
	}

	public void setStarred(Integer starred) {
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

	public Integer getCoverSmall() {
		return coverSmall;
	}

	public void setCoverSmall(Integer coverSmall) {
		this.coverSmall = coverSmall;
	}

}
