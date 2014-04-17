package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/15/2014
 */
@Entity
@Table(name = "base_url")
public class UrlPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2425397694774326468L;

	private Integer id;					// ID，数据库生成
	
	private String content;					// URL内容
	
	private String remarks;				// 备注
	
	private List<GroupPO> groups;      	// URL所属的组或角色
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "url_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "content", length = 1024, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ManyToMany
	@JoinTable(name="base_group_url", joinColumns={ @JoinColumn(name="url_id")}, 
		    inverseJoinColumns={ @JoinColumn(name = "group_id") })
	public List<GroupPO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupPO> groups) {
		this.groups = groups;
	}
}

