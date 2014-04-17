package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Entity
@Table(name = "bs_access_token")
public class AccessTokenPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8853060453129589650L;

	private Integer id;					//数据库生成
	
	private String tokenKey;  			//token_key
	
    private byte[] token;  				//token_secret

	private String authenticationId; 	//access_token
    
    private byte[] authentication; 		//access_secret
    
	private String refreshToken;  		//refresh_token
    
    private Date expiration;			//expiration_time
    
	private AccessClientPO Client;		//access_client
	
	private UserPO user;					//user
    
    public AccessTokenPO(String tokenKey, byte[] token, String authenticationId,
			byte[] authentication, String refreshToken, Date expiration,
			AccessClientPO client, UserPO user) {
		super();
		this.tokenKey = tokenKey;
		this.token = token;
		this.authenticationId = authenticationId;
		this.authentication = authentication;
		this.refreshToken = refreshToken;
		this.expiration = expiration;
		Client = client;
		this.user = user;
	}

    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "token_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="client_id", nullable = true)
    public AccessClientPO getClient() {
		return Client;
	}

	public void setClient(AccessClientPO client) {
		Client = client;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = true)
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	@Column(name = "token_key", length = 16, nullable = false)
	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	@Column(name = "token", length = 16, nullable = false)
	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	@Column(name = "authentication_id", length = 16)
	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	@Column(name = "authentication", length = 16)
	public byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}

	@Column(name = "refresh_token", length = 16)
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiration", length = 19)
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}	
	
}
