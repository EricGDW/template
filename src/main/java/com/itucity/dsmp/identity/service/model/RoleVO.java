package com.itucity.dsmp.identity.service.model;

public class RoleVO {
	private Integer roleId;
	private String roleName;
	private String type;
	private Integer parentId;
	private String remarks;
	public RoleVO() {
		super();
	}
	public RoleVO(Integer roleId, String roleName, String type,
			Integer parentId, String remarks) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.type = type;
		this.parentId = parentId;
		this.remarks = remarks;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "RoleVO [roleId=" + roleId + ", roleName=" + roleName
				+ ", type=" + type + ", parentId=" + parentId + ", remarks="
				+ remarks + "]";
	}
	
}
