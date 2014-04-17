package com.itucity.dsmp.identity.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleResourcePOId implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2690347048081684941L;
	private Integer roleId;
	private Integer resourceId;
	
	public RoleResourcePOId(){
	}
	
	public RoleResourcePOId(Integer roleId, Integer resourceId) {
		super();
		this.roleId = roleId;
		this.resourceId = resourceId;
	}
	
	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "resource_id", nullable = false)
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public String toString() {
		return "RoleResourcePOId [roleId=" + roleId + ", resourceId="
				+ resourceId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resourceId == null) ? 0 : resourceId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		RoleResourcePOId other = (RoleResourcePOId) obj;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
	
}
