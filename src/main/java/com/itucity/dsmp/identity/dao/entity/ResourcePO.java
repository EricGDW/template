package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "bs_resource")
public class ResourcePO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8806318834887954801L;
	private Integer id;
	private Integer resourceId;
	private String type;
	private String remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "resource_id", nullable = false)
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	@Column(name = "type", length = 20)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "ResourcePO [id=" + id + ", resourceId=" + resourceId
				+ ", type=" + type + ", remarks=" + remarks + "]";
	}
	
}
