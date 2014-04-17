package com.itucity.dsmp.tripwiki.service;

import com.itucity.dsmp.tripwiki.service.model.TagVO;


public interface TagService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	TagVO getById(Integer id);
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	TagVO getByName(String name);
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	Integer addTag(TagVO tag);
	
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	Boolean updateTag(TagVO tag);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean deleteTag(Integer id);
}
