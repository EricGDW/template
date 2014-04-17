package com.itucity.dsmp.identity.service.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/15/2014
 */
public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 906268957399141095L;

	private Integer id; 				//用户ID

	private String username;			// 用户名
	
	private String password;			// 密码

	private String idcard;				// 身份证号
	
	private Integer sex;				// 性别

	private String nickname;			// 昵称
	
	private String realname;			// 姓名
	
	private String language;			// 语言
	
	private String city;				// 城市
	
	private String province;			// 省份
	
	private String country;			// 国家
	
	private Integer headImgId;			// 头像URL
	
	private Date registerTime;			// 注册时间
	
	private String type;				// 类型

	private String email;				// 电子邮件

	private Boolean isActive;			// 是否有效Y/N

	private String remarks;			// 备注

	public UserVO(){
		
	}
	
	public UserVO(Integer id, String username, String email){
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getHeadImgId() {
		return headImgId;
	}

	public void setHeadImgId(Integer headImgId) {
		this.headImgId = headImgId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
}
