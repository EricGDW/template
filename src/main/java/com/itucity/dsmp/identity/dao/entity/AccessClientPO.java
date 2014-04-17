package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Entity
@Table(name = "bs_access_client")
public class AccessClientPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2845250558203753688L;

	private Integer clientId;
	
	private String appName;
	
	private Date postTime;
	
	private String appKey;
	
	private String appSecret;
	
	private String scope;
	
	private String state;    //全局唯一标示
	
	private String grantType; 
	
	private String authorityCode; 
	
	private String redirectUrl;
	
	private Boolean isValid;
	
	private UserPO owner;
	
	public AccessClientPO(String appKey, String appSecret) {
		super();
		this.appKey = appKey;
		this.appSecret = appSecret;
	}

	public AccessClientPO(String appKey,
			String appSecret, String scope, String grantType,
			String authorityCode, String redirectUrl) {
		super();
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.scope = scope;
		this.grantType = grantType;
		this.authorityCode = authorityCode;
		this.redirectUrl = redirectUrl;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "client_id", unique = true, nullable = false)
	public Integer getClientId() {
		return clientId;
	}
	
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@Column(name = "app_name", length = 30, nullable = false)
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@OneToOne
	@JoinColumn(name="app_owner", nullable = true)
	public UserPO getOwner() {
		return owner;
	}

	public void setOwner(UserPO owner) {
		this.owner = owner;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_time", length = 19)
	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@Column(name = "app_secret", length = 255, nullable = false)
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	@Column(name = "app_key", length = 255, nullable = false)
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	@Column(name = "scope", length = 255)
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Column(name = "grant_type", length = 255)
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	@Column(name = "authority_code", length = 255)
	public String getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	
	@Column(name = "state", length = 255)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "redirect_url", length = 1024)
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Column(name = "is_valid")
	@Type(type="yes_no")
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	 
	
}
