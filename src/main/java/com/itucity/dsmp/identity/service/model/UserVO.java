package com.itucity.dsmp.identity.service.model;

import java.io.Serializable;

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

	private String no;					// 员工工号
	
	private String sex;					// 性别

	private String nickname;			// 昵称
	
	private String language;			// 语言
	
	private String city;				// 城市
	
	private String province;			// 省份
	
	private String country;				// 国家
	
	private String headImgUrl;			// 头像URL
	
	private String type;				// 类型

	private String email;				// 电子邮件

	private Boolean isValid;			// 是否有效Y/N

	private String remarks;				// 备注

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
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
