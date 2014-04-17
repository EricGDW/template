package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.identity.dao.entity.RolePO;
import com.itucity.dsmp.identity.dao.entity.UrlPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
public interface ResourceDao {
	
	
	List<RolePO> getResourceRoleByType(Integer resourceId, String type);
	
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
