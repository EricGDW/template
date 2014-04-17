package com.itucity.dsmp.tripwiki.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.itucity.dsmp.identity.dao.entity.UserPO;

/**
 * 评论
 * 
 * @author Eric
 * @version 0.1
 * 
 * 4/5/2014
 */

@Entity
@Table(name = "tw_comment", catalog = "dsmp")
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer commentId;
	
	private String content;				//评论内容
	
	private Integer targetId;				//评论对象的ID,外键
	
	private String sort;					//评论对象类型(可以为景点评论，攻略评论等)
	
	private String ctime;					//评论的时间
	
	private Integer parentId;				//父评论
	
	private String remarks;				//备注
	
	private Integer uid;					//评论的用户
		
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	@Column(name = "content", length = 255)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "target_id", length = 11)
	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	@Column(name = "ctime", length = 30)
	public String getCtime() {
		return ctime;
	}
	
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	@Column(name = "sort", length = 20)
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "parent_id", length = 11)
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "uid", length = 11)
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	

}
