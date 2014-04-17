package com.itucity.dsmp.tripwiki.dao;

import com.itucity.dsmp.tripwiki.dao.entity.TagPO;

/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/30/2014
 */
public interface TagDao {
	
	TagPO findById(Integer tagId);
	
	Integer save(TagPO tag);
	
	Boolean update(TagPO tag);
	
	Boolean delete(TagPO tag);
	
	Boolean deleteById(Integer tagId);
	

	/**
	 * 
	 * @param name
	 * @return
	 */
	TagPO findByName(String name);
	
}
