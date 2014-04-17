package com.itucity.dsmp.identity.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bs_role_resource")
public class RoleResourcePO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6708334683920708710L;
	private RoleResourcePOId id;
	
	public RoleResourcePO() {
	}

	public RoleResourcePO(RoleResourcePOId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "resourceId", column = @Column(name = "resource_id", nullable = false)) })
	public RoleResourcePOId getId() {
		return this.id;
	}

	public void setId(RoleResourcePOId id) {
		this.id = id;
	}
}
