package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.tripwiki.service.model.ImageVO;


public interface ImageService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	ImageVO getImageById(Integer id);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	List<ImageVO> getImageByType(String type);
	
	/**
	 * 
	 * @param description
	 * @return
	 */
	List<ImageVO> getImageByDescription(String description);

	
	/**
	 * 
	 * @param image
	 * @return
	 */
	Integer addImage(ImageVO image);
	
	
}
