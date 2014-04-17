package com.itucity.dsmp.tripwiki.dao;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;

/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/30/2014
 */
public interface TagDao extends IDao {

	/**
	 * 
	 * @param name
	 * @return
	 */
	TagPO findByName(String name);
	
}
