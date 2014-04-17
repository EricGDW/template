package com.itucity.dsmp.tripwiki.service.model;

public class ImageVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -232700990661708994L;
	private Integer imageId;// 图片ID
	private Integer uid;// User ID
	private Integer destinationId; // 景点ID
	private String title;// 图片标题
	private String name;// 图片名
	private String description; // 图片描述
	private String type; // 图片类型：相册（album）、封面（cover）、头像（avatar）
	private String size; // 图片大小
	private String raw; // 原图地址
	private Integer rawHeight; // 原图高度
	private Integer rawWidth;// 原图宽度
	private String large; // 大图
	private String medium; // 中图
	private String mini; // 小图
	private String small; // 小图
	private String square; // 方图
	private String hash;// 图片Hash值
	private String extension;// 图片扩展名
	private String createTime;// 创建时间
	private Integer isPrivate;// 是否私有,1为私有，0为开放
	private Integer isDel;// 是否删除，1为删除，0为开放
	
	
	public ImageVO() {
		super();
	}
	

	public Integer getImageId() {
		return imageId;
	}


	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}


	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public Integer getRawHeight() {
		return rawHeight;
	}
	public void setRawHeight(Integer rawHeight) {
		this.rawHeight = rawHeight;
	}
	public Integer getRawWidth() {
		return rawWidth;
	}
	public void setRawWidth(Integer rawWidth) {
		this.rawWidth = rawWidth;
	}
	public String getLarge() {
		return large;
	}
	public void setLarge(String large) {
		this.large = large;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getMini() {
		return mini;
	}
	public void setMini(String mini) {
		this.mini = mini;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
