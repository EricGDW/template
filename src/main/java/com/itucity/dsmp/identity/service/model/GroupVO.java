package com.itucity.dsmp.identity.service.model;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/15/2014
 */
public class GroupVO {

	private Integer id;

	private String name;			//组织名称

	private String type;			//组织类型

	private Integer parentId;		//父组织

	private String remarks;			//备注
	
	public GroupVO() {
		super();
	}
	
	public GroupVO(Integer id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
