package com.itucity.dsmp.identity.service;

import com.itucity.dsmp.identity.service.model.AccessClientVO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/16/2014
 */
public interface AccessClientService {
	
	/**
	 * 按appName查询app相关信息
	 * @param appName
	 * @return
	 */
	AccessClientVO getByAppName(String appName);
	
	/**
	 * 按appKey查询app相关信息
	 * @param appKey
	 * @return
	 */
	AccessClientVO getByAppKey(String appKey);

	/**
	 * 按全局唯一标示获取app
	 * @param state
	 * @return
	 */
	AccessClientVO getByState(String state);

	void editAccessClient(AccessClientVO vo);
}
