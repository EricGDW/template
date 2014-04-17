package com.itucity.dsmp.identity.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/12/2014
 */
@Entity
@Table(name = "base_group")
public class GroupPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9011181641153718508L;

	private Integer id;

	private String name;			//组织名称

	private String type;			//组织类型

	private GroupPO parentGroup;	//父组织

	private String remarks;			//备注
	
	private List<UserPO> users;		//组的用户
	
	private List<UrlPO> urls;		//组的url
	
	public GroupPO(){
		
	}
	
	public GroupPO(String name){
		this.name = name;
	}
	
	public GroupPO(String name, String type, GroupPO parentGroup, String remarks,
			List<UserPO> users) {
		super();
		this.name = name;
		this.type = type;
		this.parentGroup = parentGroup;
		this.remarks = remarks;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "group_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToMany(mappedBy="groups")
	public List<UserPO> getUsers() {
		return users;
	}

	public void setUsers(List<UserPO> users) {
		this.users = users;
	}
	
	@Column(name = "group_name", length = 255, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@OneToOne
	@JoinColumn(name="parent_group", nullable = true)
	public GroupPO getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(GroupPO parentGroup) {
		this.parentGroup = parentGroup;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ManyToMany(mappedBy="groups")
	public List<UrlPO> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlPO> urls) {
		this.urls = urls;
	}

}
