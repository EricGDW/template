package com.itucity.dsmp.identity.dao;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.identity.dao.entity.AccessClientPO;

public interface AccessClientDao extends IDao {
	
	/**
	 * 根据应用名查询
	 * 
	 * @param appName
	 * @return
	 */
	 AccessClientPO findByAppName(String appName);
	 
	 
	 /**
	 * 根据appKey查询
	 * 
	 * @param appKey
	 * @return
	 */
	 AccessClientPO findByAppKey(String appKey);

}
