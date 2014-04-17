package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "bs_role")
public class RolePO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1160026879179121417L;
	private Integer roleId;
	private String roleName;
	private String type;
	private Integer parentId;
	private String remarks;
	
	public RolePO() {
		super();
	}

	public RolePO(Integer roleId, String roleName, String type,
			Integer parentId, String remarks) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.type = type;
		this.parentId = parentId;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", length = 30)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "RolePO [roleId=" + roleId + ", roleName=" + roleName
				+ ", type=" + type + ", parentId=" + parentId + ", remarks="
				+ remarks + "]";
	}
	
}
