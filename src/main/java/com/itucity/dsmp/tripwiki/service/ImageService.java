package com.itucity.dsmp.tripwiki.service;

import com.itucity.dsmp.tripwiki.dto.ImageVO;


public interface ImageService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	ImageVO getImageById(Integer id);

	
	/**
	 * 
	 * @param image
	 * @return
	 */
	Integer addImage(ImageVO image);
	
	
}
