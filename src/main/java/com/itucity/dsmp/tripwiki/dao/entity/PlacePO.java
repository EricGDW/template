package com.itucity.dsmp.tripwiki.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 行程的地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */

@Entity
@Table(name = "tw_destination", catalog = "dsmp")
public class PlacePO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer placeId;
	
	private String name;				//地点名称
	
	private String content;			//地点相关信息(地址、简介、)
		
	private String score;				//景点评分
	
	private Integer shareCount;		//景点分享数
	
	private Integer favouriteCount;	//景点赞数
	
	private Integer collectCount;		//景点收藏数
	
	private Integer commentCount;		//景点评论数
	
	private Integer hateCount;			//景点踩数
	
	private Integer coverId;			//地点封面图片
	
	private String cityCode;  			//地点所在城市
	
	private String address;			//地点
	
	private Float latitude;			//纬度
	
	private Float longitude;			//经度
	
	private String timeStart;			//开始时间
	
	private String timeEnd;			//结束时间
	
	private String createdTime;		//创建时间
	
	private String	modifyTime;			//修改时间
	
	private Float price;
	
	private String tips;
	
	private String scene;
	
	private String contact;
	
	private String timeDesc;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "place_id", unique = true, nullable = false)
	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	@Column(name = "destination_name", length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "contents", length = 1024)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "score", length = 8)
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Column(name = "share_count", length = 8)
	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	@Column(name = "collect_count", length = 8)
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	@Column(name = "comment_count", length = 8)
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}


	@Column(name = "favourite_count", length = 8)
	public Integer getFavouriteCount() {
		return favouriteCount;
	}

	public void setFavouriteCount(Integer favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

	@Column(name = "hate_count", length = 8)
	public Integer getHateCount() {
		return hateCount;
	}

	public void setHateCount(Integer hateCount) {
		this.hateCount = hateCount;
	}

	@Column(name = "cover_id", length = 11)
	public Integer getCoverId() {
		return coverId;
	}

	public void setCoverId(Integer coverId) {
		this.coverId = coverId;
	}

	@Column(name = "city_code", length = 20)
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "create_time", length = 20)
	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "modify_time", length = 20)
	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "price")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "tips", length = 255)
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	@Column(name = "scene", length = 255)
	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	@Column(name = "contact", length = 255)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "time_description", length = 255)
	public String getTimeDesc() {
		return timeDesc;
	}

	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}

	@Column(name = "time_start", length = 20)
	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	
	@Column(name = "time_end", length = 8)
	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}


	@Column(name = "address", length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "latitude")
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude")
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
}
