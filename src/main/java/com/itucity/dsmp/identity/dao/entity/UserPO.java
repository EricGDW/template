package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/12/2014
 */
@Entity
@Table(name = "base_user")
public class UserPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6742292851069750319L;

	private Integer uid;				// 用户ID，数据库生成

	private String username;			// 登录名

	private String password;			// 密码
	
	private String email;				// 电子邮件

	private String type;				// 类型
	
	private UserDetailPO userDetail; 	// 用户详细信息

	private Boolean isValid;			// 是否有效Y/N
	
	private List<GroupPO> groups;      // 用户的组或角色

	public UserPO(){
		
	}
	
	public UserPO(String username, String password){
		this.username = username;
		this.password = password;
	}

	public UserPO(String username, String password, String email, String type,
			UserDetailPO userDetail, Boolean isValid, List<GroupPO> groups) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.userDetail = userDetail;
		this.isValid = isValid;
		this.groups = groups;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "user_name", length = 255, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "user_password", length = 255, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_type", length = 255)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "user_email", length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne
	@JoinColumn(name="user_detail", nullable = true)
	public UserDetailPO getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetailPO userDetail) {
		this.userDetail = userDetail;
	}
	
	@Column(name = "is_valid")
	@Type(type="yes_no")
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	@ManyToMany
	@JoinTable(name="base_user_group", joinColumns={ @JoinColumn(name="uid")}, 
		    inverseJoinColumns={ @JoinColumn(name = "gid") })
	public List<GroupPO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupPO> groups) {
		this.groups = groups;
	}

}
