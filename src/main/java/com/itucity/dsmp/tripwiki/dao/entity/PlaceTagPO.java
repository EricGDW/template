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
 * PlaceTagPO generated by hbm2java
 */
@Entity
@Table(name = "tw_place_tag", catalog = "dsmp")
public class PlaceTagPO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7322088291037485609L;
	private PlaceTagPOId id;

	public PlaceTagPO() {
	}

	public PlaceTagPO(PlaceTagPOId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "placeId", column = @Column(name = "place_id", nullable = false)),
			@AttributeOverride(name = "tagId", column = @Column(name = "tag_id", nullable = false)) })
	public PlaceTagPOId getId() {
		return this.id;
	}

	public void setId(PlaceTagPOId id) {
		this.id = id;
	}

}
