package com.itucity.dsmp.tripwiki.service.model;

public class CommentVO {

	private Integer commentId;
	
	private String content;				//评论内容
	
	private Integer targetId;				//评论对象的ID,外键
	
	private String sort;					//评论对象类型(可以为景点评论，攻略评论等)
	
	private String ctime;					//评论的时间
	
	private Integer parentId;				//父评论
	
	private String remarks;				//备注
	
	private Integer uid;					//评论的用户

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
}
