package com.itucity.dsmp.identity.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itucity.dsmp.identity.dao.entity.UserDetailPO;

public class SecurityUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8242940190960961504L;
	
	private String username;
	private String password;	
	private boolean userEnabled;
	private Collection<GrantedAuthority> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	//额外增加的属性
	private int userId;
	
	private UserDetailPO userDetail;
	
	public SecurityUserDetails(int userId,String username, String password,
			boolean userEnabled, Collection<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.userEnabled = userEnabled;		
		this.authorities = authorities;
		
		//这里先初始都为true，如果需要深度控制，可完善
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		
		//
		this.userId = userId;
		
	}

	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	
	public String getPassword() {
		return this.password;
	}

	
	public String getUsername() {
		return this.username;
	}

	
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	
	public boolean isEnabled() {		
		return this.userEnabled;
	}

	public int getUserId() {
		return userId;
	}
	
	public UserDetailPO getUserDetail() {
		return userDetail;
	}


	public void setUserDetail(UserDetailPO userDetail) {
		this.userDetail = userDetail;
	}
}
