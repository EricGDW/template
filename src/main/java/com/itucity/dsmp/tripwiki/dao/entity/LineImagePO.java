package com.itucity.dsmp.tripwiki.dao.entity;
// default package
// Generated 2014-4-13 11:00:07 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LineImagePO generated by hbm2java
 */
@Entity
@Table(name = "tw_line_image", catalog = "dsmp")
public class LineImagePO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6406566548276603235L;
	private LineImagePOId id;

	public LineImagePO() {
	}

	public LineImagePO(LineImagePOId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "lineId", column = @Column(name = "line_id", nullable = false)),
			@AttributeOverride(name = "imageId", column = @Column(name = "image_id", nullable = false)) })
	public LineImagePOId getId() {
		return this.id;
	}

	public void setId(LineImagePOId id) {
		this.id = id;
	}

}