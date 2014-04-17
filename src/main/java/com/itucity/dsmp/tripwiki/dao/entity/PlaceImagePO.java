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
 * PlaceImagePO generated by hbm2java
 */
@Entity
@Table(name = "tw_place_image", catalog = "dsmp")
public class PlaceImagePO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2703090376847565783L;
	private PlaceImagePOId id;

	public PlaceImagePO() {
	}

	public PlaceImagePO(PlaceImagePOId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "imageId", column = @Column(name = "image_id", nullable = false)),
			@AttributeOverride(name = "placeId", column = @Column(name = "place_id", nullable = false)) })
	public PlaceImagePOId getId() {
		return this.id;
	}

	public void setId(PlaceImagePOId id) {
		this.id = id;
	}

}
