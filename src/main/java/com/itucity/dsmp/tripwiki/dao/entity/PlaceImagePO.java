package com.itucity.dsmp.tripwiki.dao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tw_place_image", catalog = "dsmp")
public class PlaceImagePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer PlaceId;
	
	private Integer imageId;
	
	public PlaceImagePO() {
	}
	
	public PlaceImagePO(Integer placeId, Integer imageId) {
		super();
		PlaceId = placeId;
		this.imageId = imageId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "place_id", length = 11)
	public Integer getPlaceId() {
		return PlaceId;
	}

	public void setPlaceId(Integer placeId) {
		PlaceId = placeId;
	}

	@Column(name = "image_id", length = 11)
	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

}
