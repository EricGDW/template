package com.itucity.dsmp.identity.dao.entity;
// default package
// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserGroupPO generated by hbm2java
 */
@Entity
@Table(name = "bs_user_role")
public class UserRolePO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1789217681113509974L;
	private UserRolePOId id;

	public UserRolePO() {
	}

	public UserRolePO(UserRolePOId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "uid", column = @Column(name = "uid", nullable = false)) })
	public UserRolePOId getId() {
		return this.id;
	}

	public void setId(UserRolePOId id) {
		this.id = id;
	}

}
