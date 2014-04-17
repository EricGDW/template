package com.itucity.dsmp.identity.service.model;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/15/2014
 */
public class GroupVO {

	private Integer groupId;
	private String groupName;
	private String type;
	private Integer parentId;
	private String remarks;
	
	public GroupVO(){
		
	}
	
	public GroupVO(Integer groupId, String groupName, String type,
			Integer parentId, String remarks) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.type = type;
		this.parentId = parentId;
		this.remarks = remarks;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	
}
