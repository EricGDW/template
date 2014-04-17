package com.itucity.dsmp.identity.dao.entity;
// default package
// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserGroupPOId generated by hbm2java
 */
@Embeddable
public class UserRolePOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4545778369190828751L;
	private Integer roleId;
	private Integer uid;

	public UserRolePOId() {
	}

	public UserRolePOId(Integer roleId, Integer uid) {
		this.roleId = roleId;
		this.uid = uid;
	}

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "UserRolePOId [roleId=" + roleId + ", uid=" + uid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRolePOId other = (UserRolePOId) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}
