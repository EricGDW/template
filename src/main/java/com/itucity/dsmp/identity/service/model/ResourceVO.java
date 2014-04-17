package com.itucity.dsmp.identity.service.model;

import java.util.List;

import com.itucity.dsmp.identity.dao.entity.GroupPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/15/2014
 */
public class ResourceVO {

	private Integer id;  //资源ID
	
	private String type; //资源类型
	
	private String content; //资源内容
	
	private List<GroupPO> owner; //资源拥有者

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<GroupPO> getOwner() {
		return owner;
	}

	public void setOwner(List<GroupPO> owner) {
		this.owner = owner;
	}
	
	

}
