package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.identity.dao.entity.UrlPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
public interface ResourceDao {
	
	/**
	 * 
	 * @return
	 */
	List<UrlPO> getAllUrl();
	

	/**
	 * 
	 * @param id
	 * @return
	 */
	UrlPO getUrlById(Integer id);
}
