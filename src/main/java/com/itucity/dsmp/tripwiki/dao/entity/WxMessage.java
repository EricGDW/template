package com.itucity.dsmp.tripwiki.dao.entity;

// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WxMessage generated by hbm2java
 */
@Entity
@Table(name = "wx_message", catalog = "dsmp")
public class WxMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425437191862711577L;
	private Integer id;
	private Integer msgType;
	private String toUserName;
	private String fromUserName;
	private String content;
	private Date createTime;
	private String picUrl;
	private String mediaId;
	private String format;
	private String thumbMediaId;
	private Float latitude;
	private Float longitude;
	private Integer scale;
	private String description;
	private String title;
	private String label;
	private String openId;

	public WxMessage() {
	}

	public WxMessage(Integer msgType, String toUserName, String fromUserName,
			String content, Date createTime, String picUrl, String mediaId,
			String format, String thumbMediaId, Float latitude,
			Float longitude, Integer scale, String description, String title,
			String label, String openId) {
		this.msgType = msgType;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.content = content;
		this.createTime = createTime;
		this.picUrl = picUrl;
		this.mediaId = mediaId;
		this.format = format;
		this.thumbMediaId = thumbMediaId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.scale = scale;
		this.description = description;
		this.title = title;
		this.label = label;
		this.openId = openId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "msg_type")
	public Integer getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Column(name = "to_user_name", length = 30)
	public String getToUserName() {
		return this.toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@Column(name = "from_user_name", length = 30)
	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@Lob
	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "pic_url", length = 1024)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "media_id", length = 20)
	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Column(name = "format", length = 20)
	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(name = "thumb_media_id", length = 20)
	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Column(name = "latitude", precision = 12, scale = 0)
	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 12, scale = 0)
	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Column(name = "scale")
	public Integer getScale() {
		return this.scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "label", length = 20)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "open_id")
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
