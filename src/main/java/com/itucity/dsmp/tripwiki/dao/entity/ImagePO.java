package com.itucity.dsmp.tripwiki.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * 地点相关的图片
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/21/2014
 */

@Entity
@Table(name = "tw_image", catalog = "dsmp")
public class ImagePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer imageId;
	
	private String description;		//图片描述
	
	private String type;				//图片类型
	
	private String raw;				//源图片
	
	private Integer rawHeight;			//图片高度
	
	private Integer rawWidth;			//图片寬度
	
	private String large;				//大图片
	
	private String medium;				//中图片
	
	private String mini;				//MINI图片
	
	private String small;				//小图片
	
	private String square;				//正方行图片
	
	private String title;				// 图片标题
	
	private String hash;				// 图片Hash值
	
	private String extension;			// 图片扩展名
	
	private String createTime;			// 创建时间
	
	private Integer isPrivate;			// 是否私有,1为私有，0为开放
	
	private Integer isDel;				// 是否删除，1为删除，0为开放

	private PlacePO destination;	//图片属于的地点
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "image_id", unique = true, nullable = false)
	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	
	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", length = 24)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "raw", length = 255)
	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	@Column(name = "raw_height")
	public Integer getRawHeight() {
		return rawHeight;
	}

	public void setRawHeight(Integer rawHeight) {
		this.rawHeight = rawHeight;
	}

	@Column(name = "raw_width")
	public Integer getRawWidth() {
		return rawWidth;
	}

	public void setRawWidth(Integer rawWidth) {
		this.rawWidth = rawWidth;
	}

	@Column(name = "large", length = 255)
	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	@Column(name = "medium", length = 255)
	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	@Column(name = "mini", length = 255)
	public String getMini() {
		return mini;
	}

	public void setMini(String mini) {
		this.mini = mini;
	}

	@Column(name = "small", length = 255)
	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	@Column(name = "square", length = 255)
	public String getSquare() {
		return square;
	}

	public void setSquare(String square) {
		this.square = square;
	}
	
	
	@Column(name = "title", length = 20)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "hash", length = 32)
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Column(name = "extension", length = 8)
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Column(name = "create_time", length = 11)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "is_private")
	public Integer getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}

	@Column(name = "is_del")
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@ManyToOne
	@JoinColumn(name="destination_id")
	public PlacePO getDestination() {
		return destination;
	}

	public void setDestination(PlacePO destination) {
		this.destination = destination;
	}
	
}
