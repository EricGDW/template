package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/12/2014
 */
@Entity
@Table(name = "base_user_detail")
public class UserDetailPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7565614919510179510L;

	private Integer id; 				// 用户详细ID，数据库生成
	
	private String idCard;				// 工号
	
	private String realname;			// 真实姓名
	
	private String sex;					// 性别
	
	private Date birthday;				// 生日
	
	private String nickname;			// 昵称
	
	private String language;			// 语言
	
	private String city;				// 城市
	
	private String province;			// 省份
	
	private String country;				// 国家
	
	private String headImgUrl;			// 头像URL
	
	private Blob signaturePic;			// 电子签名
	
	private String encryptKey;			// 加密key
	
	private Date registerDate;			// 注册时间
	
	private Date lastLoginTime;			// 最后登录时间
	
	private String lastLoginIP;			// 最后登录IP

	private String remarks;				// 备注
	
	public UserDetailPO(){
		
	}

	public UserDetailPO(String idCard, String realname, String sex,
			Date birthday, String nickname, Blob signaturePic,
			String encryptKey, Date registerDate, Date lastLoginTime,
			String lastLoginIP, String remarks) {
		super();
		this.idCard = idCard;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.nickname = nickname;
		this.signaturePic = signaturePic;
		this.encryptKey = encryptKey;
		this.registerDate = registerDate;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIP = lastLoginIP;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_detail_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "idcard", length = 255)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "realname", length = 20)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "sex", length = 8)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday", length = 19)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "nickname", length = 20)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	@Column(name = "language", length = 20)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "city", length = 255)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", length = 255)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "country", length = 255)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "hea_image_url", length = 1024)
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	@Lob
	@Column(name = "signature_pic", columnDefinition = "BLOB")
	public Blob getSignaturePic() {
		return signaturePic;
	}

	public void setSignaturePic(Blob signaturePic) {
		this.signaturePic = signaturePic;
	}

	@Column(name = "encrypt_key", length = 255)
	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", length = 19)
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time", length = 19)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip", length = 15)
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
