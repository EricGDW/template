package com.itucity.dsmp.tripwiki.dto;

import java.util.Date;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 *          3/24/2014
 */
public class PlaceVO implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4523791733587552109L;
	private Integer placeId;
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private String title;//地点名称
	private String address;//景点地址
	private String areaCode;//地点所在城市
	private String contact;
	private String content;//地点相关信息(地址、简介、)
	private Float longitude;//经度
	private Float latitude;//纬度
	private Float price;
	private String tips;
	private String timeStart;//开始时间
	private String timeEnd;//结束时间
	private String timeDesc;
	private String scene;
	private String location;
	private Integer duration;
	
	private String coverUrl;		//封面图片URL
	
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	
	
	public Integer getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getTimeDesc() {
		return timeDesc;
	}
	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
}
